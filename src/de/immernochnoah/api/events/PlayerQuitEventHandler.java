package de.immernochnoah.api.events;

import de.immernochnoah.api.mysql.chach.Garbage_Collector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventHandler implements Listener {

    @EventHandler
    public void handlePlayerQuitEvent(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        Garbage_Collector gc = new Garbage_Collector();
        gc.syncPlayerDataToDatabase(p);
    }
}
