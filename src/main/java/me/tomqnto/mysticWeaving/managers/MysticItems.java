package me.tomqnto.mysticWeaving.managers;

import me.tomqnto.mysticWeaving.MysticWeaving;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class MysticItems {
    
    public static ItemStack getMysticDust(int amount){

        String name = "§d§ke§r§d Mystic Dust §ke";
        String id = "mystic_dust";

        ItemStack item = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.itemName(LegacyComponentSerializer.legacySection().deserialize(name));
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(MysticWeaving.plugin, id), PersistentDataType.STRING, id);
        itemMeta.setEnchantmentGlintOverride(true);
        itemMeta.setLore(List.of("§8Mystic currency"));
        item.setItemMeta(itemMeta);
        item.setAmount(amount);

        return item;
    }

    public static ItemStack getFakeMysticDust(int amount){
        ItemStack dust = getMysticDust(1);
        ItemMeta meta = dust.getItemMeta();
        meta.getPersistentDataContainer().remove(new NamespacedKey(MysticWeaving.plugin, "mystic_dust"));
        dust.setItemMeta(meta);
        return dust;
    }
    
}
