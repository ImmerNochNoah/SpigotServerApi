package de.immernochnoah.api.commands;

import de.immernochnoah.api.ServerAPI;
import de.immernochnoah.api.file_system.File_Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload_Cmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        File_Manager fm = new File_Manager();
        if (!p.hasPermission(fm.getConfigText("PERMISSIONS", "Reload"))) {
            p.sendMessage(ServerAPI.getPrefix() + " " + fm.getConfigText("MESSAGES", "No Permission"));
        } else {
            fm.reloadFile();
            p.sendMessage(ServerAPI.getPrefix() + " " + fm.getConfigText("MESSAGES", "Reload"));
        }
        return false;
    }
}
