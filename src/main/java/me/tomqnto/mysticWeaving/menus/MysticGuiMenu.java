package me.tomqnto.mysticWeaving.menus;

import me.tomqnto.mysticWeaving.MysticWeaving;
import me.tomqnto.mysticWeaving.managers.MysticItems;
import me.tomqnto.mysticWeaving.managers.PlayerData;
import me.tomqnto.mysticWeaving.menus.api.SimpleMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MysticGuiMenu extends SimpleMenu {

    private final Player player;

    public MysticGuiMenu(Player player) {
        super(Rows.SIX, Component.text("Mystic Gui").color(NamedTextColor.DARK_PURPLE));
        this.player = player;
        this.setUsePlaceholders(false);

    }

    @Override
    public void onSetup() {

        int level = PlayerData.getLevel(player);
        String name = player.getName();
        int levelupCost = PlayerData.getNextLevelupCost(player);
        int energyUpgradeCost = PlayerData.getEnergyUpgradeCost(player);
        int regenAmountUpgradeCost = PlayerData.getEnergyRegenAmountUpgradeCost(player);
        int levelupPaid = PlayerData.getLevelUpgradePaid(player);
        int energylevelPaid = PlayerData.getEnergyLevelUpgradePaid(player);
        int energyregenPaid = PlayerData.getEnergyRegenUpgradePaid(player);

        // levelup item
        ItemStack levelupItem = new ItemStack(Material.PURPLE_DYE);
        ItemMeta levelMeta = levelupItem.getItemMeta();
        levelMeta.setLore(List.of("§d+1 level",  "§d|", "§d| Mystic Level: " + PlayerData.getLevel(player) + " -> " + (PlayerData.getLevel(player) + 1) , "§d|", "§d| Require: §dMystic Dust " + levelupPaid + "/" + levelupCost, "§d|","§d§l| Click to level up!"));
        levelMeta.itemName(Component.text("Level up!").color(NamedTextColor.DARK_PURPLE));

        if (levelupPaid<levelupCost){
            List<String> lore = levelMeta.getLore();
            lore.set(lore.size()-1, "§d§l| Not enough Mystic Dust");
            levelMeta.setLore(lore);
        }
        levelupItem.setItemMeta(levelMeta);

        // profile item
        ItemStack profileItem = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta profileMeta = profileItem.getItemMeta();

        profileMeta.itemName(MiniMessage.miniMessage().deserialize("<dark_purple>" + name));
        profileMeta.setLore(List.of("§d|", "§d| Mystic Level: " + level, "§d| Mystic Energy Capacity: " + PlayerData.getMaxEnergy(player), "§d| Mystic Dust: " ));

        profileItem.setItemMeta(profileMeta);


        // Energy item
        ItemStack energyItem = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta energyMeta = energyItem.getItemMeta();

        energyMeta.itemName(Component.text("Mystic Energy Capacity Upgrade").color(NamedTextColor.DARK_PURPLE));

        if (energylevelPaid >= energyUpgradeCost){

            energyMeta.setLore(List.of("§d+25 Energy capacity", "§d|", "§d| Mystic Energy Capacity: " + PlayerData.getMaxEnergy(player) + " -> " + (PlayerData.getMaxEnergy(player) + 25) , "§d|", "§d| Cost: Mystic Dust x" + energyUpgradeCost, "§d| You have: Mystic Dust x","§d|", "§d|§l CLICK"));


        } else{
            energyMeta.itemName(Component.text("Mystic Energy Capacity Upgrade").color(NamedTextColor.GRAY));

            energyMeta.setLore(List.of("§7+25 Energy capacity", "§7|", "§7| Mystic Energy Capacity: " + PlayerData.getMaxEnergy(player) + " -> " + (PlayerData.getMaxEnergy(player) + 25) , "§7|", "§7| Cost: §dMystic Dust §7x" + energyUpgradeCost, "§7| You have: §dMystic Dust §7x", "§7|", "§7| You do not have enough §dMystic Dust§7 to", "§7| upgrade your Mystic Energy Capacity"));
        }

        energyItem.setItemMeta(energyMeta);

        // Energy regen amount upgrade item

        ItemStack regenItem = new ItemStack(Material.ECHO_SHARD);
        ItemMeta regenMeta = regenItem.getItemMeta();
        
        regenMeta.itemName(Component.text("Energy Regen Amount Upgrade").color(NamedTextColor.DARK_PURPLE));

        if (energyregenPaid >= regenAmountUpgradeCost){

            regenMeta.setLore(List.of("§d+1 Energy Regen Amount", "§d|", "§d| Mystic Energy Regen Amount: +" + PlayerData.getEnergyRegenAmount(player) + " -> +" + (PlayerData.getEnergyRegenAmount(player) + 1) , "§d|", "§d| Cost: Mystic Dust x" + regenAmountUpgradeCost, "§d| You have: Mystic Dust x","§d|", "§d|§l CLICK"));


        } else{
            regenMeta.itemName(Component.text("Mystic Energy Capacity Upgrade").color(NamedTextColor.GRAY));

            regenMeta.setLore(List.of("§7+1 Energy Regen Amount", "§7|", "§7| Mystic Energy Regen Amount: +" + PlayerData.getEnergyRegenAmount(player) + " -> +" + (PlayerData.getEnergyRegenAmount(player) + 1) , "§7|", "§7| Cost: §dMystic Dust §7x" + energyUpgradeCost, "§7| You have: §dMystic Dust §7x", "§7|", "§7| You do not have enough §dMystic Dust§7 to", "§7| upgrade your Mystic Energy Regen Amount"));
        }

        regenItem.setItemMeta(regenMeta);

        // Get Dust item

        ItemStack getDustItem = MysticItems.getMysticDust(1);
        ItemMeta getDustMeta = getDustItem.getItemMeta();

        getDustMeta.itemName(Component.text("Get Mystic Dust").color(NamedTextColor.LIGHT_PURPLE));
        getDustMeta.setLore(List.of("§d|", "§d| Click to get Mystic Dust!"));

        getDustItem.setItemMeta(getDustMeta);

        // adding items to the menu

        setItem(10,levelupItem);
        setItem(13, energyItem);
        setItem(16, regenItem);
        setItem(45, profileItem);


    }

    @EventHandler(priority = EventPriority.LOW)
    public void onItemDeposit(InventoryClickEvent event){

        if (!(event.getClickedInventory().getHolder(false) instanceof MysticGuiMenu))
            return;

        if (event.getSlot()==10 && (PlayerData.getLevelUpgradePaid(player)<PlayerData.getNextLevelupCost(player))) {
            LevelupMenu menu = new LevelupMenu(player);
            menu.open(player);
        } else
            player.performCommand("levelup");
    }

}
