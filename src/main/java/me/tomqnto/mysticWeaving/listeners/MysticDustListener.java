package me.tomqnto.mysticWeaving.listeners;

import me.tomqnto.mysticWeaving.MysticWeaving;
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
        if (event.getItemInHand().getPersistentDataContainer().has(new NamespacedKey(MysticWeaving.plugin, "mystic_dust")))
            event.setCancelled(true);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getAction().isRightClick() && event.getPlayer().isSneaking() && event.getPlayer().getItemInHand().getPersistentDataContainer().has(new NamespacedKey(MysticWeaving.plugin, "mystic_dust"))){
            Player player = event.getPlayer();
            PlayerData.addMysticDust(player, player.getItemInHand().getAmount());
            player.setItemInHand(null);
            event.setCancelled(true);
        }

    }

}

