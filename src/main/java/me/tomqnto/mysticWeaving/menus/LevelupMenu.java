package me.tomqnto.mysticWeaving.menus;

import me.tomqnto.mysticWeaving.MysticWeaving;
import me.tomqnto.mysticWeaving.Utils;
import me.tomqnto.mysticWeaving.managers.MysticItems;
import me.tomqnto.mysticWeaving.managers.PlayerData;
import me.tomqnto.mysticWeaving.menus.api.SimpleMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LevelupMenu extends SimpleMenu {

    private final Player player;
    private int amount;

    public LevelupMenu(Player player) {
        super(Rows.SIX, Component.text("Level up!"));
        this.player = player;
        this.setUsePlaceholders(false);
        this.amount = 0;
    }

    @Override
    public void onSetup() {
        List<Integer> stackList = new ArrayList<>();
        int paid = PlayerData.getLevelUpgradePaid(player);
        int cost = PlayerData.getNextLevelupCost(player);

        ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        border.getItemMeta().itemName(Component.space());

        for (int x=0; x<9; x++){
            setItem(x, border);
        }
        // mystic dust icon
        ItemStack mysticItem = MysticItems.getFakeMysticDust(1);
        ItemMeta mysticMeta = mysticItem.getItemMeta();
        mysticMeta.setLore(List.of("§d|", "§d| Paid: " + paid + "/" + cost , "§d| Require: " + amount + "/" + (cost-paid)));
        mysticItem.setItemMeta(mysticMeta);


        setItem(4, mysticItem);

        // confirm button
        ItemStack confirm = new ItemStack(Material.LIME_SHULKER_BOX);
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.itemName(Component.text("Confirm").color(NamedTextColor.GREEN));
        confirm.setItemMeta(confirmMeta);

        setItem(8, confirm);

        if (amount>0){
            int tempAmount = amount;
            while (tempAmount != 0) {
                if (tempAmount >= 64) {
                    stackList.add(64);
                    tempAmount -= 64;
                }
                if (tempAmount < 64) {
                    stackList.add(tempAmount);
                    tempAmount -= tempAmount;
                }
            }
        }

        int slot = 9;
        for (int stack : stackList){
            setItem(slot, MysticItems.getMysticDust(stack));
            slot++;
        }
    }

    @EventHandler
    public void onItemPickUp(PlayerAttemptPickupItemEvent event){
        player.getOpenInventory();
        if (player.getOpenInventory().getTopInventory().getHolder(false) instanceof LevelupMenu)
            event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!(player.getOpenInventory().getTopInventory().getHolder(false) instanceof LevelupMenu))
            return;
        ItemStack item = event.getClickedInventory().getItem(event.getSlot());
        int slot = event.getSlot();

        if (item!=null && Utils.isMysticDust(item)) {
            if(event.getClickedInventory() == player.getInventory()) {

                int giveAmount = item.getAmount();
                int price = PlayerData.getNextLevelupCost(player) - (amount + PlayerData.getLevelUpgradePaid(player));
                int change = giveAmount - price;
                int pay = change < 0 ? giveAmount : giveAmount - change;
                change = Math.max(change, 0);

                if (pay == 0) {
                    event.setCancelled(true);
                } else{
                    amount += pay;
                    player.getInventory().getItem(event.getSlot()).setAmount(change);
                    event.setCancelled(true);
                    player.sendRichMessage("PAY = " + pay + " Change = " + change + " AMOUNt = " + amount);

                }

            } else if (event.getClickedInventory().getHolder(false) instanceof LevelupMenu) {
                Utils.give(player, item);
                amount -= item.getAmount();
                event.getClickedInventory().setItem(event.getSlot(), null);
                event.setCancelled(true);
            }

        } else
            event.setCancelled(true);

        update();

        if (slot==8 && event.getClickedInventory().getHolder(false) instanceof LevelupMenu){
            PlayerData.addLevelUpgradePaid(player, amount);
            player.closeInventory();
            if (PlayerData.getLevelUpgradePaid(player)>=PlayerData.getNextLevelupCost(player))
                player.performCommand("levelup");
            player.performCommand("mysticgui");
            amount = 0;
        }
    }


}
