package me.tomqnto.mysticWeaving.menus.api;

import me.tomqnto.mysticWeaving.MysticWeaving;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.function.Consumer;

public abstract class SimpleMenu implements Menu, Listener {

    protected static final ItemStack PLACEHOLDER_ITEM;

    static {
        PLACEHOLDER_ITEM = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        final ItemMeta meta = PLACEHOLDER_ITEM.getItemMeta();
        meta.displayName(Component.space());
        PLACEHOLDER_ITEM.setItemMeta(meta);
    }
    private boolean usePlaceholders;
    private final Inventory inventory;
    private HashMap<Integer, Consumer<Player>> actionsMap = new HashMap<>();
    private HashMap<Integer, ItemStack> itemsMap = new HashMap<>();

    public SimpleMenu(Rows rows, Component title) {
        this.usePlaceholders = true;
        this.inventory = Bukkit.createInventory(this, rows.getSize(), title);
        MysticWeaving.plugin.getServer().getPluginManager().registerEvents(this, MysticWeaving.plugin);
    }

    @Override
    public void click(Player player, int slot) {
        Consumer<Player> action = this.actionsMap.get(slot);

        if (action!=null)
            action.accept(player);
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        setItem(slot, item, player -> {});
    }

    @Override
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        this.actionsMap.put(slot, action);
        this.itemsMap.put(slot, item);
        getInventory().setItem(slot, item);
    }

    public void setUsePlaceholders(boolean usePlaceholders) {
        this.usePlaceholders = usePlaceholders;
    }

    @Override
    public void setPlaceholders() {
        for (int i = 0; i < getInventory().getSize(); i++) {
            if (getInventory().getItem(i) == null)
                getInventory().setItem(i, PLACEHOLDER_ITEM);
        }
    }

    @Override
    public boolean usePlaceholders() {
        return usePlaceholders;
    }

    @Override
    public void update() {
        inventory.clear();
        itemsMap.clear();
        actionsMap.clear();
        onSetup();
    }

    public abstract void onSetup();

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public enum Rows{
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

        private final int size;

        Rows(int rows) {
            this.size = rows*9;
        }

        public int getSize() {
            return size;
        }
    }

    public HashMap<Integer, ItemStack> getItemsMap(){
        return this.itemsMap;
    }

    public HashMap<Integer, Consumer<Player>> getActionsMap(){
        return this.actionsMap;
    }
}
