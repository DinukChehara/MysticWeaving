package me.tomqnto.mysticWeaving.menus;

import me.tomqnto.mysticWeaving.menus.api.PagedMenu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Test extends PagedMenu {
    public Test() {
        super(Rows.FIVE, Component.space());
    }
    @Override
    public void onSetup() {
        final ItemStack[] items = new ItemStack[100];
        int count = 0;
        for (Material material : Material.values()) {
            try{
                final ItemStack item = new ItemStack(material);
                final ItemMeta meta = item.getItemMeta();
                item.setItemMeta(meta);
                items[count] = item;
                count++;
            }catch (Exception e){

            }
        }

        addAll(items);
    }
}
