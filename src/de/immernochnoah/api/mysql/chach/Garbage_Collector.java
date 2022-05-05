package de.immernochnoah.api.mysql.chach;

import de.immernochnoah.api.ServerAPI;
import de.immernochnoah.api.mysql.SqlSync;
import org.bukkit.entity.Player;

import static de.immernochnoah.api.mysql.chach.HashMaps.*;

public class Garbage_Collector {

    public void removeCoins(String uuid, int i) {
        int coins = player_coins.get(uuid) - i;
        player_coins.replace(uuid, coins);
    }

    public void addCoins(String uuid, int i) {
        int coins = player_coins.get(uuid) + i;
        player_coins.replace(uuid, coins);
    }

    public void clearAllData() {
        player_coins.clear();
        player_level.clear();
        player_xp.clear();
        player_next_level_xp.clear();
    }

    public void getPlayerDataToHash(String uuid) {
        SqlSync sql = new SqlSync();
        player_coins.put(uuid, sql.getMysqlData("coins", uuid));
        player_level.put(uuid, sql.getMysqlData("level", uuid));
        player_xp.put(uuid, sql.getMysqlData("level_xp", uuid));
        player_next_level_xp.put(uuid, sql.getMysqlData("next_level_xp", uuid));
    }

    public void syncPlayerDataToDatabase(Player p) {
        SqlSync sql = new SqlSync();
        sql.updatePlayerMysqlData("coins", p.getName(), p.getUniqueId().toString(), String.valueOf(ServerAPI.getPlayerCoins(p.getUniqueId().toString())));
        sql.updatePlayerMysqlData("level", p.getName(), p.getUniqueId().toString(), String.valueOf(ServerAPI.getPlayerLevel(p.getUniqueId().toString())));
        sql.updatePlayerMysqlData("level_xp", p.getName(), p.getUniqueId().toString(), String.valueOf(ServerAPI.getPlayerXP(p.getUniqueId().toString())));
        sql.updatePlayerMysqlData("next_level_xp", p.getName(), p.getUniqueId().toString(), String.valueOf(ServerAPI.getPlayerNextLevelXP(p.getUniqueId().toString())));
        removePlayerFromChach(p.getUniqueId().toString());
    }

    public void removePlayerFromChach(String uuid) {
        player_coins.remove(uuid);
        player_level.remove(uuid);
        player_xp.remove(uuid);
        player_next_level_xp.remove(uuid);
    }
}
