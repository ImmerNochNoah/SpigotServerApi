package de.immernochnoah.api.commands;

import de.immernochnoah.api.ServerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Coins_Cmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        String uuid = p.getUniqueId().toString();
        p.sendMessage(ServerAPI.getPrefix() + " §7Coins§8: §6"+ ServerAPI.getPlayerCoins(uuid));
        return false;
    }
}
