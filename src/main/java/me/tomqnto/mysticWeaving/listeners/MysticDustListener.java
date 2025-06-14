package me.tomqnto.mysticWeaving.listeners;

import me.tomqnto.mysticWeaving.MysticWeaving;
import me.tomqnto.mysticWeaving.Utils;
import me.tomqnto.mysticWeaving.managers.PlayerData;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class MysticDustListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if (Utils.isMysticDust(event.getItemInHand()))
            event.setCancelled(true);
    }

}

