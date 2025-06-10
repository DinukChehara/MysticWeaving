package me.tomqnto.mysticWeaving.tasks;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBar extends BukkitRunnable {

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()){

            PlayerData.ifNoDataSetDefaults(player);

            int level = PlayerData.getLevel(player);
            int dust = PlayerData.getMysticDust(player);
            int energy = PlayerData.getEnergy(player);
            int maxenergy = PlayerData.getMaxEnergy(player);

            player.sendActionBar(MiniMessage.miniMessage().deserialize("<light_purple>Mystic Level: " + level + "<darK_gray> | <light_purple>Mystic Dust: " + dust + " <darK_gray>| <light_purple>Mystic Energy: " + energy + "/" + maxenergy));

        }

    }
}
