package com.mrpowergamerbr.loritta.commands;

import com.mrpowergamerbr.loritta.commands.vanilla.administration.*;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.AvatarCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.BotInfoCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.EmojiCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.ServerInfoCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.*;
import com.mrpowergamerbr.loritta.commands.vanilla.images.*;
import com.mrpowergamerbr.loritta.commands.vanilla.magic.*;
import com.mrpowergamerbr.loritta.commands.vanilla.minecraft.*;
import com.mrpowergamerbr.loritta.commands.vanilla.misc.*;
import com.mrpowergamerbr.loritta.commands.vanilla.music.*;
import com.mrpowergamerbr.loritta.commands.vanilla.pokemon.PokedexCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.social.*;
import com.mrpowergamerbr.loritta.commands.vanilla.undertale.UndertaleBattleCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.undertale.UndertaleBoxCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.utils.*;
import com.mrpowergamerbr.loritta.userdata.ServerConfig;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class CommandManager {
	public List<CommandBase> commandMap = new ArrayList<CommandBase>();
	public Map<String, Class<?>> defaultCmdOptions = new HashMap<String, Class<?>>();

	public CommandManager(boolean isMusicOnly) {
		// Todos os comandos devem ser registrados em uma instância global, mas em uma instância de
		// somente música apenas os comandos de música deverão ser registrados

		if (!isMusicOnly) {
			// =======[ DIVERSÃO ]======
			commandMap.add(new RollCommand());
			commandMap.add(new FaustaoCommand());
			commandMap.add(new CaraCoroaCommand());
			commandMap.add(new PedraPapelTesouraCommand());
			commandMap.add(new VaporondaCommand());
			commandMap.add(new QualidadeCommand());
			commandMap.add(new VaporQualidadeCommand());
			commandMap.add(new TristeRealidadeCommand());
			commandMap.add(new TretaNewsCommand());
			commandMap.add(new MagicBallCommand());
			commandMap.add(new FraseToscaCommand());
			commandMap.add(new SAMCommand());
			commandMap.add(new NyanCatCommand());
			commandMap.add(new WikiaCommand());
			commandMap.add(new PrimeirasPalavrasCommand());
			commandMap.add(new DrakeCommand());
			commandMap.add(new InverterCommand());
			commandMap.add(new ReceitasCommand());
			commandMap.add(new ClapifyCommand());
			commandMap.add(new SpinnerCommand());
			commandMap.add(new LavaCommand());
			commandMap.add(new LavaReversoCommand());
			commandMap.add(new ShipCommand());
			commandMap.add(new AvaliarWaifuCommand());
			commandMap.add(new RazoesCommand());
			commandMap.add(new QuadroCommand());
			commandMap.add(new DeusCommand());
			commandMap.add(new PerfeitoCommand());
			commandMap.add(new TrumpCommand());
			commandMap.add(new CepoCommand());
			commandMap.add(new DeusesCommand());
			commandMap.add(new GangueCommand());
			commandMap.add(new RandomSAMCommand());
			commandMap.add(new RandomMemeguy1997());
			commandMap.add(new AmigosCommand());
			commandMap.add(new DiscordiaCommand());
			commandMap.add(new AmizadeCommand());
			commandMap.add(new PretoEBrancoCommand());
			commandMap.add(new PerdaoCommand());
			commandMap.add(new RipVidaCommand());
			commandMap.add(new AtaCommand());
			commandMap.add(new JoojCommand());
			commandMap.add(new OjjoCommand());
			commandMap.add(new HojeCommand());
			commandMap.add(new SimsimiCommand());
			commandMap.add(new AkinatorCommand());

			// =======[ IMAGENS ]======
			commandMap.add(new GetOverHereCommand());

			// =======[ MISC ]======
			commandMap.add(new AjudaCommand());
			commandMap.add(new PingCommand());
			commandMap.add(new AngelCommand());
			commandMap.add(new QuoteCommand());
			commandMap.add(new SayCommand());
			commandMap.add(new EscolherCommand());
			commandMap.add(new HelloWorldCommand());
			commandMap.add(new LanguageCommand());

			// =======[ SOCIAL ]======
			commandMap.add(new PerfilCommand());
			commandMap.add(new BackgroundCommand());
			commandMap.add(new SobreMimCommand());
			commandMap.add(new DiscriminatorCommand());
			commandMap.add(new RepCommand());
			commandMap.add(new RankCommand());

			// =======[ UTILS ]=======
			commandMap.add(new TranslateCommand());
			commandMap.add(new EncurtarCommand());
			commandMap.add(new WikipediaCommand());
			commandMap.add(new MoneyCommand());
			commandMap.add(new HexCommand());
			commandMap.add(new LembrarCommand());
			commandMap.add(new RgbCommand());
			commandMap.add(new YoutubeMp3Command());
			commandMap.add(new DicioCommand());
			commandMap.add(new TempoCommand());
			commandMap.add(new AminoCommand());
			commandMap.add(new PackageInfoCommand());
			commandMap.add(new IsUpCommand());
			commandMap.add(new KnowYourMemeCommand());
			commandMap.add(new BIRLCommand());
			commandMap.add(new Md5Command());
			commandMap.add(new AnagramaCommand());
			commandMap.add(new CalculadoraCommand());
			commandMap.add(new MorseCommand());
			commandMap.add(new OCRCommand());

			// =======[ DISCORD ]=======
			commandMap.add(new BotInfoCommand());
			commandMap.add(new AvatarCommand());
			commandMap.add(new EmojiCommand());
			commandMap.add(new ServerInfoCommand());
			commandMap.add(new InviteCommand());

			// =======[ MINECRAFT ]========
			commandMap.add(new OfflineUUIDCommand());
			commandMap.add(new McAvatarCommand());
			commandMap.add(new McQueryCommand());
			commandMap.add(new McUUIDCommand());
			commandMap.add(new McStatusCommand());
			commandMap.add(new McHeadCommand());
			commandMap.add(new McBodyCommand());
			commandMap.add(new McSignCommand());
			commandMap.add(new SpigotMcCommand());

			// =======[ UNDERTALE ]========
			commandMap.add(new UndertaleBoxCommand());
			commandMap.add(new UndertaleBattleCommand());

			// =======[ POKÉMON ]========
			commandMap.add(new PokedexCommand());

			// =======[ ADMIN ]========
			commandMap.add(new LimparCommand());
			commandMap.add(new RoleIdCommand());
			commandMap.add(new HackBanCommand());
			commandMap.add(new SoftBanCommand());
			commandMap.add(new MuteCommand());

			// =======[ MAGIC ]========
			commandMap.add(new ReloadCommand());
			commandMap.add(new EvalCommand());
			commandMap.add(new NashornTestCommand());
			commandMap.add(new ServerInvitesCommand());
			commandMap.add(new LorittaBanCommand());
			commandMap.add(new LorittaUnbanCommand());
		}
		// =======[ MÚSICA ]========
		commandMap.add(new TocarCommand());
		commandMap.add(new MusicInfoCommand());
		commandMap.add(new VolumeCommand());
		commandMap.add(new PlaylistCommand());
		commandMap.add(new PularCommand());
		commandMap.add(new PausarCommand());
		commandMap.add(new ResumirCommand());
		commandMap.add(new SeekCommand());
		commandMap.add(new YouTubeCommand());

		for (CommandBase cmdBase : this.getCommandMap()) {
			defaultCmdOptions.put(cmdBase.getClass().getSimpleName(), CommandOptions.class);
		}

		// Custom Options
		defaultCmdOptions.put(TristeRealidadeCommand.class.getSimpleName(), TristeRealidadeCommand.TristeRealidadeCommandOptions.class);
	}

	public List<CommandBase> getCommandsDisabledIn(ServerConfig conf) {
		List<CommandBase> commands = new ArrayList<CommandBase>();

		if (conf.debugOptions().enableAllModules()) {
			return commandMap;
		}

		for (CommandBase cmd : commandMap) {
			if (conf.disabledCommands().contains(cmd.getClass().getSimpleName())) {
				commands.add(cmd);
			}
		}

		return commands;
	}
}
