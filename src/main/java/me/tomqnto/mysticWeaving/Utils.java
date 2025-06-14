package me.tomqnto.mysticWeaving;

import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Utils{

    public static void give(Player player, ItemStack item){

        int add = item.getAmount();
        int beforeAdd = 0;

        for (ItemStack itemStack : player.getInventory().getContents()){
            if (itemStack!=null && itemStack.isSimilar(item))
                beforeAdd += itemStack.getAmount();
        }
        int afterAdd = 0;
        player.give(item);
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.isSimilar(item))
                afterAdd += itemStack.getAmount();
        }

        int dropAmount = add - (afterAdd - beforeAdd);
        item.setAmount(dropAmount);
        player.dropItem(item);
    }

    public static boolean isMysticDust(ItemStack item){
        if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(MysticWeaving.plugin, "mystic_dust")))
            return true;
        return false;
    }

}
