package me.tomqnto.mysticWeaving.listeners;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        PlayerData.ifNoDataSetDefaults(event.getPlayer());
    }

}
