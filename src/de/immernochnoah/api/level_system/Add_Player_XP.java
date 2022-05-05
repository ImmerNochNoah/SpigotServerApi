package de.immernochnoah.api.level_system;

import de.immernochnoah.api.ServerAPI;
import de.immernochnoah.api.file_system.File_Manager;
import de.immernochnoah.api.mysql.chach.HashMaps;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Add_Player_XP {

    public void addPlayerXP(Player p, int i) {
        File_Manager fm = new File_Manager();
        String uuid = p.getUniqueId().toString();
        int xp = ServerAPI.getPlayerXP(uuid) + i;
        int xp_required = ServerAPI.getPlayerNextLevelXP(uuid);
        int level = ServerAPI.getPlayerLevel(uuid);

        HashMaps.player_xp.replace(uuid, xp);

        if (xp >= xp_required) {
            level++;
            xp_required = Integer.parseInt(fm.getConfigText("LEVEL SYSTEM", "Next Level XP")) + xp_required;
            HashMaps.player_next_level_xp.replace(uuid, xp_required);
            HashMaps.player_level.replace(uuid, level);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F,1.0F);
            p.sendMessage(fm.getConfigText("PREFIX", "Server Prefix") + " " + String.format(fm.getConfigText("LEVEL SYSTEM", "Next Level Text"), ServerAPI.getPlayerLevel(uuid)));
        }
    }
}
