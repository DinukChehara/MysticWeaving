package me.tomqnto.mysticWeaving.menus;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import me.tomqnto.mysticWeaving.menus.api.SimpleMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MysticGuiMenu extends SimpleMenu {

    private final Player player;

    public MysticGuiMenu(Player player) {
        super(Rows.THREE, Component.text("Mystic Gui").color(NamedTextColor.DARK_PURPLE));
        this.player = player;
    }

    @Override
    public void onSetup() {

        int playerDustAmount = PlayerData.getMysticDust(player);
        int levelupCost = PlayerData.getNextLevelupCost(player);
        int level = PlayerData.getLevel(player);
        String name = player.getName();
        int energyUpgradeCost = PlayerData.getEnergyUpgradeCost(player);

        // levelup item
        ItemStack levelupItem = new ItemStack(Material.PURPLE_DYE);
        ItemMeta levelMeta = levelupItem.getItemMeta();

        if (playerDustAmount >= levelupCost){
           levelMeta.itemName(Component.text("Level up!").color(NamedTextColor.DARK_PURPLE));

           levelMeta.setLore(List.of("§d+1 level",  "§d|", "§d| Mystic Level: " + PlayerData.getLevel(player) + " -> " + (PlayerData.getLevel(player) + 1) , "§d|", "§d| Cost: §dMystic Dust §dx" + levelupCost, "§d| You have: Mystic Dust x"+ playerDustAmount + "§d|", "§d|","§d|§l CLICK"));


        } else{
            levelupItem.setType(Material.GRAY_DYE);
            levelMeta.itemName(Component.text("Level up!").color(NamedTextColor.GRAY));

            levelMeta.setLore(List.of("§7+1 level",  "§7|", "§7| Mystic Level: " + PlayerData.getLevel(player) + " -> " + (PlayerData.getLevel(player) + 1), "§7|", "§7| Cost: §dMystic Dust §7x" + levelupCost, "§7| You have: §dMystic Dust §7x"+ playerDustAmount, "§7|", "§7| You do not have enough §dMystic Dust§7 to level up"));
        }

        levelupItem.setItemMeta(levelMeta);

        // profile item
        ItemStack profileItem = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta profileMeta = profileItem.getItemMeta();

        profileMeta.itemName(MiniMessage.miniMessage().deserialize("<dark_purple>" + name));
        profileMeta.setLore(List.of("§d|", "§d| Mystic Level: " + level, "§d| Mystic Energy Capacity: " + PlayerData.getMaxEnergy(player), "§d| Mystic Dust: " + playerDustAmount));

        profileItem.setItemMeta(profileMeta);


        // Energy item
        ItemStack energyItem = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta energyMeta = energyItem.getItemMeta();

        energyMeta.itemName(Component.text("Mystic Energy Capacity Upgrade").color(NamedTextColor.DARK_PURPLE));

        if (playerDustAmount >= energyUpgradeCost){

            energyMeta.setLore(List.of("§d+25 Energy capacity", "§d|", "§d| Mystic Energy Capacity: " + PlayerData.getMaxEnergy(player) + " -> " + (PlayerData.getMaxEnergy(player) + 25) , "§d|", "§d| Cost: Mystic Dust x" + energyUpgradeCost, "§d| You have: Mystic Dust x"+ playerDustAmount,"§d|", "§d|§l CLICK"));


        } else{
            energyMeta.itemName(Component.text("Mystic Energy Capacity Upgrade").color(NamedTextColor.GRAY));

            energyMeta.setLore(List.of("§7+25 Energy capacity", "§7|", "§7| Mystic Energy Capacity: " + PlayerData.getMaxEnergy(player) + " -> " + (PlayerData.getMaxEnergy(player) + 25) , "§7|", "§7| Cost: §dMystic Dust §7x" + energyUpgradeCost, "§7| You have: §dMystic Dust §7x"+ playerDustAmount, "§7|", "§7| You do not have enough §dMystic Dust§7 to", "§7| upgrade your Mystic Energy Capacity"));
        }

        energyItem.setItemMeta(energyMeta);

        setItem(13,levelupItem, player -> {player.performCommand("levelup"); player.closeInventory(); player.performCommand("mysticgui");});
        setItem(11, energyItem, player1 -> {player.performCommand("upgradeEnergy"); player.closeInventory(); player.performCommand("mysticgui");});
        setItem(18, profileItem);
    }

}
