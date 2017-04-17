package com.mrpowergamerbr.loritta.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mrpowergamerbr.loritta.commands.vanilla.administration.LimparCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.administration.RoleIdCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.AvatarCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.BotInfoCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.EmojiCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.discord.ServerInfoCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.CaraCoroaCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.FaustaoCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.FraseToscaCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.MagicBallCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.NyanCatCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.PedraPapelTesouraCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.QualidadeCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.RollCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.SAMCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.TranslateCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.TretaNewsCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.TristeRealidadeCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.VaporQualidadeCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.VaporondaCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.fun.YouTubeCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.magic.ChangeGameCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.magic.ReloadCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.minecraft.OfflineUUIDCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.misc.AjudaCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.misc.AngelCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.misc.EncurtarCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.misc.PackageInfoCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.misc.PingCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.misc.WikipediaCommand;
import com.mrpowergamerbr.loritta.commands.vanilla.undertale.UndertaleBoxCommand;
import com.mrpowergamerbr.loritta.userdata.ServerConfig;

import lombok.Getter;

@Getter
public class CommandManager {
	private List<CommandBase> commandMap = new ArrayList<CommandBase>();
	private Map<String, Class<?>> defaultCmdOptions = new HashMap<String, Class<?>>();
	
	public CommandManager() {
		commandMap.add(new BotInfoCommand());
		commandMap.add(new AjudaCommand());
		commandMap.add(new RollCommand());
		commandMap.add(new FaustaoCommand());
		commandMap.add(new CaraCoroaCommand());
		commandMap.add(new PedraPapelTesouraCommand());
		commandMap.add(new PingCommand());
		commandMap.add(new VaporondaCommand());
		commandMap.add(new QualidadeCommand());
		commandMap.add(new VaporQualidadeCommand());
		commandMap.add(new TristeRealidadeCommand());
		commandMap.add(new AngelCommand());
		commandMap.add(new TretaNewsCommand());
		commandMap.add(new MagicBallCommand());
		commandMap.add(new FraseToscaCommand());
		commandMap.add(new PackageInfoCommand());
		commandMap.add(new YouTubeCommand());
		commandMap.add(new TranslateCommand());
		commandMap.add(new EncurtarCommand());
		commandMap.add(new SAMCommand());
		commandMap.add(new NyanCatCommand());
		commandMap.add(new WikipediaCommand());
		
		// =======[ DISCORD ]=======
		commandMap.add(new AvatarCommand());
		commandMap.add(new EmojiCommand());
		commandMap.add(new ServerInfoCommand());
		
		// =======[ MINECRAFT ]========
		commandMap.add(new OfflineUUIDCommand());
		
		// =======[ UNDERTALE ]========
		commandMap.add(new UndertaleBoxCommand());
		
		// =======[ ADMIN ]========
		commandMap.add(new LimparCommand());
		commandMap.add(new RoleIdCommand());
		
		// =======[ MAGIC ]========
		commandMap.add(new ReloadCommand());
		commandMap.add(new ChangeGameCommand());
		
		for (CommandBase cmdBase : this.getCommandMap()) {
			defaultCmdOptions.put(cmdBase.getClass().getSimpleName(), CommandOptions.class);
		}
		
		// Custom Options
		defaultCmdOptions.put(TristeRealidadeCommand.class.getSimpleName(), TristeRealidadeCommand.TristeRealidadeCommandOptions.class);
		defaultCmdOptions.put(YouTubeCommand.class.getSimpleName(), YouTubeCommand.YouTubeCommandOptions.class);
	}
	
	public List<CommandBase> getCommandsAvailableFor(ServerConfig conf) {
		List<CommandBase> commands = new ArrayList<CommandBase>();
		
		for (CommandBase cmd : commandMap) {
			if (conf.debugOptions().enableAllModules() || conf.modules().contains(cmd.getClass().getSimpleName())) {
				commands.add(cmd);
			}
		}
		
		return commands;
	}
}