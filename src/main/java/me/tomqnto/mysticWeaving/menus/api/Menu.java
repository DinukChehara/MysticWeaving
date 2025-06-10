package me.tomqnto.mysticWeaving.menus.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface Menu extends InventoryHolder {

    void click(Player player, int slot);

    void setItem(int slot, ItemStack item);

    void setItem(int slot, ItemStack item, Consumer<Player> action);

    boolean usePlaceholders();

    void setPlaceholders();

    void onSetup();

    void update();

    default void open(Player player){
        if (usePlaceholders())
            setPlaceholders();

        onSetup();
        player.openInventory(getInventory());
    }

}
