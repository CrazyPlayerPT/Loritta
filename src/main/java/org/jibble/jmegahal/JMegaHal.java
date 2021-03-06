/* 
Copyright Paul James Mutton, 2001-2004, http://www.jibble.org/

This file is part of JMegaHal.

This software is dual-licensed, allowing you to choose between the GNU
General Public License (GPL) and the www.jibble.org Commercial License.
Since the GPL may be too restrictive for use in a proprietary application,
a commercial license is also provided. Full license information can be
found at http://www.jibble.org/licenses/

$Author: pjm2 $
$Id: JMegaHal.java,v 1.4 2004/02/01 13:24:06 pjm2 Exp $

 */

package org.jibble.jmegahal;

import com.google.common.collect.ImmutableMap;
import com.mrpowergamerbr.loritta.Loritta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JMegaHal implements Serializable {

	// These are valid chars for words. Anything else is treated as punctuation.
	public static final String WORD_CHARS = "abcdefghijklmnopqrstuvwxyz" +
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
			"0123456789";
	public static final String END_CHARS = ".!?";

	public static final Map<String, String> ANTONYMS = ImmutableMap.<String, String>builder()
			.put("EU", "VOCÊ")
			.build();
	/**
	 * Construct an instance of JMegaHal with an empty brain.
	 */
	public JMegaHal() {

	}

	/**
	 * Adds an entire documents to the 'brain'.  Useful for feeding in
	 * stray theses, but be careful not to put too much in, or you may
	 * run out of memory!
	 */
	public void addDocument(String uri) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(uri).openStream()));
		StringBuffer buffer = new StringBuffer();
		int ch = 0;
		while ((ch = reader.read()) != -1) {
			buffer.append((char) ch);
			if (END_CHARS.indexOf((char) ch) >= 0) {
				String sentence = buffer.toString();
				sentence = sentence.replace('\r', ' ');
				sentence = sentence.replace('\n', ' ');
				add(sentence);
				buffer = new StringBuffer();
			}
		}
		add(buffer.toString());
		reader.close();
	}

	/**
	 * Adds a new sentence to the 'brain'
	 */
	public void add(String sentence) {
		if (words.size() >= 100000) {
			// Limpar para não dar OOM
			words.clear();
			quads.clear();
			next.clear();
			previous.clear();
		}
		sentence = sentence.trim();
		ArrayList<String> parts = new ArrayList<String>();
		char[] chars = sentence.toCharArray();
		int i = 0;
		boolean punctuation = false;
		StringBuffer buffer = new StringBuffer();
		while (i < chars.length) {
			char ch = chars[i];
			if ((WORD_CHARS.indexOf(ch) >= 0) == punctuation) {
				punctuation = !punctuation;
				String token = buffer.toString();
				if (token.length() > 0) {
					parts.add(token);
				}
				buffer = new StringBuffer();
				//i++;
				continue;
			}
			buffer.append(ch);
			i++;
		}
		String lastToken = buffer.toString();
		if (lastToken.length() > 0) {
			parts.add(lastToken);
		}

		if (parts.size() >= 4) {
			for (i = 0; i < parts.size() - 3; i++) {
				//System.out.println("\"" + parts.get(i) + "\"");
				Quad quad = new Quad((String) parts.get(i), (String) parts.get(i + 1), (String) parts.get(i + 2), (String) parts.get(i + 3));
				if (quads.containsKey(quad)) {
					quad = (Quad) quads.get(quad);
				}
				else {
					quads.put(quad, quad);
				}

				if (i == 0) {
					quad.setCanStart(true);
				}
				//else if (i == parts.size() - 4) {
				if (i == parts.size() - 4) {
					quad.setCanEnd(true);
				}

				for (int n = 0; n < 4; n++) {
					String token = (String) parts.get(i + n);
					if (!words.containsKey(token)) {
						words.put(token, new HashSet<Quad>(1));
					}
					HashSet<Quad> set = (HashSet<Quad>) words.get(token);
					set.add(quad);
				}

				if (i > 0) {
					String previousToken = (String) parts.get(i - 1);
					if (!previous.containsKey(quad)) {
						previous.put(quad, new HashSet<String>(1));
					}
					HashSet<String> set = (HashSet<String>) previous.get(quad);
					set.add(previousToken);
				}

				if (i < parts.size() - 4) {
					String nextToken = (String) parts.get(i + 4);
					if (!next.containsKey(quad)) {
						next.put(quad, new HashSet<String>(1));
					}
					HashSet<String> set = (HashSet<String>) next.get(quad);
					set.add(nextToken);
				}

			}
		}
		else {
			// Didn't learn anything.
		}

	}

	/**
	 * Generate a Loritta.getRandom()om sentence from the brain.
	 */
	public String getSentence() {
		return getSentence(null);
	}

	/**
	 * Generate a sentence that includes (if possible) the specified word.
	 */
	public String getSentence(String word) {
		LinkedList<String> parts = new LinkedList<String>();

		Quad[] quads;
		if (word != null && words.containsKey(word)) {
			quads = (Quad[]) ((HashSet<?>) words.get(word)).toArray(new Quad[0]);
		}
		else {
			quads = (Quad[]) this.quads.keySet().toArray(new Quad[0]);
		}

		if (quads.length == 0) {
			return "";
		}

		Quad middleQuad = quads[Loritta.getRandom().nextInt(quads.length)];
		Quad quad = middleQuad;

		for (int i = 0; i < 4; i++) {
			parts.add(quad.getToken(i));
		}

		while (quad.canEnd() == false) {
			String[] nextTokens = (String[]) ((HashSet<?>) next.get(quad)).toArray(new String[0]);
			String nextToken = nextTokens[Loritta.getRandom().nextInt(nextTokens.length)];
			quad = (Quad) this.quads.get(new Quad(quad.getToken(1), quad.getToken(2), quad.getToken(3), nextToken));
			parts.add(nextToken);
		}

		quad = middleQuad;
		while (quad.canStart() == false) {
			String[] previousTokens = (String[]) ((HashSet<?>) previous.get(quad)).toArray(new String[0]);
			String previousToken = previousTokens[Loritta.getRandom().nextInt(previousTokens.length)];
			quad = (Quad) this.quads.get(new Quad(previousToken, quad.getToken(0), quad.getToken(1), quad.getToken(2)));
			parts.addFirst(previousToken);
		}

		StringBuffer sentence = new StringBuffer();
		Iterator<String> it = parts.iterator();
		while (it.hasNext()) {
			String token = (String) it.next();
			sentence.append(token);
		}

		return sentence.toString();
	}

	// This maps a single word to a HashSet of all the Quads it is in.
	private ConcurrentHashMap<String,  HashSet<Quad>> words = new ConcurrentHashMap<String, HashSet<Quad>>();

	// A self-referential HashMap of Quads.
	private ConcurrentHashMap<Quad, Quad> quads = new ConcurrentHashMap<Quad, Quad>();

	// This maps a Quad onto a Set of Strings that may come next.
	private ConcurrentHashMap<Quad, HashSet<String>> next = new ConcurrentHashMap<Quad, HashSet<String>>();

	// This maps a Quad onto a Set of Strings that may come before it.
	private ConcurrentHashMap<Quad, HashSet<String>> previous = new ConcurrentHashMap<Quad, HashSet<String>>();

}