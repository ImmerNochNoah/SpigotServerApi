package de.immernochnoah.api;

import de.immernochnoah.api.commands.Coins_Cmd;
import de.immernochnoah.api.commands.Level_Cmd;
import de.immernochnoah.api.commands.Reload_Cmd;
import de.immernochnoah.api.events.PlayerJoinEventHandler;
import de.immernochnoah.api.events.PlayerQuitEventHandler;
import de.immernochnoah.api.file_system.Config_File;
import de.immernochnoah.api.file_system.File_Manager;
import de.immernochnoah.api.level_system.Add_Player_XP;
import de.immernochnoah.api.mysql.MysqlConnection;
import de.immernochnoah.api.mysql.chach.Garbage_Collector;
import de.immernochnoah.api.mysql.chach.HashMaps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerAPI extends JavaPlugin {

    private static Plugin instance;
    public static MysqlConnection mysql;

    @Override
    public void onEnable() {
        instance = this;

        Config_File config_file = new Config_File();
        config_file.createConfig();

        File_Manager fm = new File_Manager();
        mysql = new MysqlConnection((fm.getConfigText("MYSQL", "Host")), (fm.getConfigText("MYSQL", "Port")), (fm.getConfigText("MYSQL", "Database")),  (fm.getConfigText("MYSQL", "User")), (fm.getConfigText("MYSQL", "Password")));
        mysql.update("CREATE TABLE IF NOT EXISTS server_api (name varchar(30), uuid varchar(200), coins int(200), level int(200), level_xp int(200), next_level_xp int(200))");

        getCommand("level").setExecutor(new Level_Cmd());
        getCommand("coins").setExecutor(new Coins_Cmd());
        getCommand("api-reload").setExecutor(new Reload_Cmd());

        Bukkit.getPluginManager().registerEvents(new PlayerJoinEventHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitEventHandler(), this);
    }

    @Override
    public void onDisable() {
        mysql.close();
        Garbage_Collector gc = new Garbage_Collector();
        gc.clearAllData();
    }

    public static int getPlayerCoins(String uuid) {
        return HashMaps.player_coins.get(uuid);
    }

    public static void removeCoins(String uuid, int i) {
        Garbage_Collector gc = new Garbage_Collector();
        gc.removeCoins(uuid, i);
    }

    public static void addCoins(String uuid, int i) {
        Garbage_Collector gc = new Garbage_Collector();
        gc.addCoins(uuid, i);
    }

    public static void setCoins(String uuid, int i) {
        Garbage_Collector gc = new Garbage_Collector();
        gc.setCoins(uuid, i);
    }

    public static int getPlayerLevel(String uuid) {
        return HashMaps.player_level.get(uuid);
    }

    public static int getPlayerXP(String uuid) {
        return HashMaps.player_xp.get(uuid);
    }

    public static int getPlayerNextLevelXP(String uuid) {
        return HashMaps.player_next_level_xp.get(uuid);
    }

    public static void addPlayerXP(Player p, int i) {
        Add_Player_XP apxp = new Add_Player_XP();
        apxp.addPlayerXP(p, i);
    }

    public static String getPrefix() {
        File_Manager fmr = new File_Manager();
        return fmr.getConfigText("PREFIX", "Server Prefix");
    }


    public static Plugin getInstance() {
        return instance;
    }
}
