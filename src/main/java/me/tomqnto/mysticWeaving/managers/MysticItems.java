package me.tomqnto.mysticWeaving.managers;

import me.tomqnto.mysticWeaving.MysticWeaving;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class MysticItems {
    
    public static ItemStack getMysticDust(int amount){

        String name = "§d§ke§r§d Mystic Dust §ke";
        String id = "mystic_dust";

        ItemStack item = new ItemStack(Material.REDSTONE);
        item.setAmount(amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setItemName(name);
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(MysticWeaving.plugin, id), PersistentDataType.STRING, id);
        itemMeta.setEnchantmentGlintOverride(true);
        item.setItemMeta(itemMeta);

        return item;
    }
    
}
