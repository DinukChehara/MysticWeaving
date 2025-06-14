package me.tomqnto.mysticWeaving.menus.api;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (event.getClickedInventory().getHolder(false) instanceof Menu menu){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            menu.click(player, event.getSlot());
        }
        if (event.isShiftClick())
            event.setCancelled(true);
    }

}
