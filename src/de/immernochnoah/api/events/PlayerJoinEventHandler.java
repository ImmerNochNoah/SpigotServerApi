package de.immernochnoah.api.events;

import de.immernochnoah.api.ServerAPI;
import de.immernochnoah.api.mysql.chach.Garbage_Collector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventHandler implements Listener {

    @EventHandler
    public void handlePlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        Bukkit.getScheduler().scheduleSyncDelayedTask(ServerAPI.getInstance(), new Runnable() {
            @Override
            public void run() {
                Garbage_Collector gc = new Garbage_Collector();
                gc.getPlayerDataToHash(p.getUniqueId().toString());
            }
        },  5);
    }
}
