package de.immernochnoah.api.level_system;

import de.immernochnoah.api.ServerAPI;
import de.immernochnoah.api.file_system.File_Manager;
import de.immernochnoah.api.mysql.chach.HashMaps;
import org.bukkit.entity.Player;

public class Check_Level_Up {

    public void checkPlayerLevelUp(Player p) {
        File_Manager fm = new File_Manager();
        String uuid = p.getUniqueId().toString();
        int xp = ServerAPI.getPlayerXP(uuid);
        int xp_required = ServerAPI.getPlayerNextLevelXP(uuid);
        int level = ServerAPI.getPlayerLevel(uuid);
        if (xp >= xp_required) {
            level++;
            double double_xp = xp_required * Double.parseDouble(fm.getConfigText("LEVEL SYSTEM", "Next XP Multiplier"));
            xp_required = (int) double_xp;
            HashMaps.player_next_level_xp.replace(uuid, xp_required);
            HashMaps.player_level.replace(uuid, level);
            p.sendMessage(fm.getConfigText("PREFIX", "Server Prefix") + " " + String.format(fm.getConfigText("LEVEL SYSTEM", "Next Level Text"), ServerAPI.getPlayerLevel(uuid)));
            checkPlayerLevelUp(p);
        }
    }
}
