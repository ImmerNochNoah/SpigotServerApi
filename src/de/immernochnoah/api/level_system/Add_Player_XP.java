package de.immernochnoah.api.level_system;

import de.immernochnoah.api.ServerAPI;
import de.immernochnoah.api.file_system.File_Manager;
import de.immernochnoah.api.mysql.chach.HashMaps;
import org.bukkit.entity.Player;

public class Add_Player_XP {

    public void addPlayerXP(Player p, int i) {
        File_Manager fm = new File_Manager();
        String uuid = p.getUniqueId().toString();
        int xp = ServerAPI.getPlayerXP(uuid) + i;
        HashMaps.player_xp.replace(uuid, xp);

        Check_Level_Up clup = new Check_Level_Up();
        clup.checkPlayerLevelUp(p);
    }
}
