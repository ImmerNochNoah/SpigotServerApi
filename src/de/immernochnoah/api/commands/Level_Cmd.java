package de.immernochnoah.api.commands;

import de.immernochnoah.api.ServerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Level_Cmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        String uuid = p.getUniqueId().toString();
        p.sendMessage(ServerAPI.getPrefix() + " §7Level§8: §6"+ ServerAPI.getPlayerLevel(uuid) + " §7XP§8: §a" + ServerAPI.getPlayerXP(uuid) + " §7/ §3" + ServerAPI.getPlayerNextLevelXP(uuid));
        return false;
    }
}
