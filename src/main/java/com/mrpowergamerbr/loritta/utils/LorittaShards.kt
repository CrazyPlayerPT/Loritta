package com.mrpowergamerbr.loritta.utils

import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.entities.impl.GameImpl
import net.dv8tion.jda.core.managers.Presence

/**
 * Guarda todos os shards da Loritta
 */
class LorittaShards {
    var shards: MutableList<JDA> = ArrayList<JDA>();

    fun getGuildById(id: String): Guild? {
        for (shard in shards) {
            var guild = shard.getGuildById(id);
            if (guild != null) { return guild; }
        }
        return null;
    }

    fun getGuilds(): List<Guild> {
        // Pegar todas as guilds em todos os shards
        var guilds = ArrayList<Guild>();

        for (shard in shards) {
            guilds.addAll(shard.guilds);
        }
        return guilds;
    }

    fun getUsers(): List<User> {
        // Pegar todas os users em todos os shards
        var users = ArrayList<User>();

        for (shard in shards) {
            users.addAll(shard.users);
        }

        var nonDuplicates = users.distinctBy { it.id }

        return nonDuplicates;
    }

    fun getUserById(id: String?): User? {
        for (shard in shards) {
            var user = shard.getUserById(id);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    fun getMutualGuilds(user: User): List<Guild> {
        // Pegar todas as mutual guilds em todos os shards
        var guilds = ArrayList<Guild>();

        for (shard in shards) {
            guilds.addAll(shard.getMutualGuilds(user));
        }
        return guilds;
    }

    fun getPresence(): Presence {
        // Pegar primeira shard e retornar a presença dela
        return shards[0].presence;
    }

    /**
     * Atualiza a presença do bot em todas as shards
     */
    fun setGame(game: GameImpl) {
        for (shard in shards) {
            shard.presence.game = game
        }
    }
}