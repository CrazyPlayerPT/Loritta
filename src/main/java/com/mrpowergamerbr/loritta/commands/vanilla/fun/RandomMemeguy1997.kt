package com.mrpowergamerbr.loritta.commands.vanilla.`fun`

import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.LorittaUtils
import com.mrpowergamerbr.loritta.utils.LorittaUtilsKotlin

class RandomMemeguy1997 : CommandBase() {
	override fun getLabel(): String {
		return "randommemeguy1997"
	}

	override fun getAliases(): List<String> {
		return listOf("randommemeguy", "randomeme", "randomemeguy", "randomemeguy1997")
	}

	override fun getDescription(): String {
		return "Pega uma postagem aleatória do Memeguy1997"
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.FUN
	}

	override fun needsToUploadFiles(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		val source = if (Loritta.random.nextBoolean()) "página" else "grupo";

		val post = if (source == "página") LorittaUtilsKotlin.getRandomPostFromPage("memeguy1997") else LorittaUtilsKotlin.getRandomPostFromGroup("380626148947201")

		if (post != null) {
			context.sendFile(post.image, "memeguy1997.png", "<:memeguy1997:331617335909548042> **|** " + context.getAsMention(true) + "(Fonte: *$source do Memeguy1997*) `${post.description}`")
		} else {
			context.sendMessage(LorittaUtils.ERROR + " **|** " + context.getAsMention(true) + "Não consegui encontrar nenhum meme na página do Memeguy1997...")
		}
	}
}