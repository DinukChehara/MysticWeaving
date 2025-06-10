package me.tomqnto.mysticWeaving.menus.api;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (event.getClickedInventory().getHolder() instanceof Menu menu){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            menu.click(player, event.getSlot());
        }
    }

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent event){
        if (event.getDestination().getHolder() instanceof Menu)
            event.setCancelled(true);
        if (event.getSource().getHolder() instanceof Menu)
            event.setCancelled(true);
    }

}
