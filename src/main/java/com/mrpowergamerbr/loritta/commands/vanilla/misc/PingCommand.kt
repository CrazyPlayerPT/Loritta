package com.mrpowergamerbr.loritta.commands.vanilla.misc

import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.locale.BaseLocale
import com.mrpowergamerbr.loritta.utils.msgFormat
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.react.GenericMessageReactionEvent

class PingCommand : CommandBase() {
    override fun getLabel(): String {
        return "ping"
    }

    override fun getDescription(locale: BaseLocale): String {
        return locale.PING_DESCRIPTION.msgFormat()
    }

    override fun run(context: CommandContext) {
        context.sendMessage(context.getAsMention(true) + "🏓 **Pong!** " + context.event.jda.ping + "ms (\uD83C\uDF0D Shard ${context.event.jda.shardInfo.shardId}/${Loritta.config.shards - 1})")
    }

    override fun onCommandReactionFeedback(context: CommandContext, e: GenericMessageReactionEvent, msg: Message) {
        msg.editMessage(context.getAsMention(true) + "kk eae men").complete();
    }
}