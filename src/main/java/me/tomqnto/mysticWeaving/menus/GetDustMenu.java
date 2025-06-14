//package me.tomqnto.mysticWeaving.menus;
//
//import me.tomqnto.mysticWeaving.Utils;
//import me.tomqnto.mysticWeaving.managers.MysticItems;
//import me.tomqnto.mysticWeaving.managers.PlayerData;
//import me.tomqnto.mysticWeaving.menus.api.SimpleMenu;
//import net.kyori.adventure.text.Component;
//import net.kyori.adventure.text.format.NamedTextColor;
//import org.bukkit.Material;
//import org.bukkit.Sound;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//
//import javax.inject.Named;
//import java.util.List;
//
//public class GetDustMenu extends SimpleMenu {
//
//    private final Player player;
//
//    public GetDustMenu(Player player) {
//        super(Rows.FOUR, Component.text("Get Mystic Dust").color(NamedTextColor.LIGHT_PURPLE));
//        this.player = player;
//        this.setUsePlaceholders(false);
//    }
//
//    @Override
//    public void onSetup() {
//        // mystic dust item
//        ItemStack mysticDust = MysticItems.getMysticDust(1);
//        ItemMeta dustMeta = mysticDust.getItemMeta();
//        dustMeta.setLore(List.of("§d|", "§d| You have: Mystic Dust x" + PlayerData.getMysticDust(player)));
//        mysticDust.setItemMeta(dustMeta);
//
//        setItem(13, mysticDust);
//
//        // - 32 button
//        ItemStack remove1 = new ItemStack(Material.RED_STAINED_GLASS_PANE, 32);
//        ItemMeta meta1 = remove1.getItemMeta();
//        meta1.itemName(Component.text("-32 Mystic Dust").color(NamedTextColor.RED));
//        remove1.setItemMeta(meta1);
//
//        setItem(9, remove1, player -> getInventory().getItem(13).setAmount(Math.max(1, this.getInventory().getItem(13).getAmount() - 32)));
//
//
//        // -1 button
//        ItemStack remove2 = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
//        ItemMeta meta2 = remove2.getItemMeta();
//        meta2.itemName(Component.text("-1 Mystic Dust").color(NamedTextColor.RED));
//        remove2.setItemMeta(meta2);
//
//        setItem(11, remove2, player -> getInventory().getItem(13).setAmount(Math.max(1, this.getInventory().getItem(13).getAmount() - 1)));
//
//        // +32 button
//        ItemStack add1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 32);
//        ItemMeta addmeta1 = add1.getItemMeta();
//        addmeta1.itemName(Component.text("+32 Mystic Dust").color(NamedTextColor.GREEN));
//        add1.setItemMeta(addmeta1);
//
//        setItem(17, add1, player -> {
//            if (Math.min(64, Math.max(1, this.getInventory().getItem(13).getAmount() + 32)) > PlayerData.getMysticDust(player)){
//                player.sendRichMessage("<gray>You do not have enough <light_purple>Mystic Dust");
//                if (PlayerData.getMysticDust(player)!=0)
//                    getInventory().getItem(13).setAmount(PlayerData.getMysticDust(player));
//                else
//                    getInventory().getItem(13).setAmount(1);
//            } else
//                getInventory().getItem(13).setAmount(Math.min(64, Math.max(1, this.getInventory().getItem(13).getAmount() + 32)));
//        });
//
//        // +1 button
//        ItemStack add2 = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
//        ItemMeta addmeta2 = add2.getItemMeta();
//        addmeta2.itemName(Component.text("+1 Mystic Dust").color(NamedTextColor.GREEN));
//        add2.setItemMeta(addmeta2);
//
//        setItem(15, add2, player -> {
//            if (Math.min(64, Math.max(1, this.getInventory().getItem(13).getAmount() + 1)) > PlayerData.getMysticDust(player)){
//                player.sendRichMessage("<gray>You do not have enough <light_purple>Mystic Dust");
//                if (PlayerData.getMysticDust(player)!=0)
//                    getInventory().getItem(13).setAmount(PlayerData.getMysticDust(player));
//                else
//                    getInventory().getItem(13).setAmount(1);
//            } else
//                getInventory().getItem(13).setAmount(Math.min(64, Math.max(1, this.getInventory().getItem(13).getAmount() + 1)));
//        });
//
//        // back button
//        ItemStack back = new ItemStack(Material.RED_SHULKER_BOX);
//        ItemMeta backMeta = back.getItemMeta();
//        backMeta.itemName(Component.text("Back").color(NamedTextColor.RED));
//        back.setItemMeta(backMeta);
//
//        setItem(30
//                , back, player -> player.performCommand("mysticgui"));
//
//        // confirm button
//        ItemStack confirm = new ItemStack(Material.LIME_SHULKER_BOX);
//        ItemMeta confirmMeta = confirm.getItemMeta();
//       confirmMeta.itemName(Component.text("Confirm").color(NamedTextColor.GREEN));
//
//        confirm.setItemMeta(confirmMeta);
//
//        setItem(32, confirm, player -> {
//
//            int playerAmount = PlayerData.getMysticDust(player);
//            int buyAmount = getInventory().getItem(13).getAmount();
//
//            if (buyAmount>playerAmount){
//                player.sendRichMessage("<gray>You do not have enough <light_purple>Mystic Dust<gray> | You need " + (buyAmount-playerAmount) + " more <light_purple>Mystic Dust");
//                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
//            } else {
//                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
//                Utils.give(player, MysticItems.getMysticDust(buyAmount));
//                PlayerData.addMysticDust(player, -1 * buyAmount);
//            }
//
//            update();
//
//            dustMeta.setLore(List.of("§d|", "§d| You have: Mystic Dust x" + PlayerData.getMysticDust(player)));
//            mysticDust.setItemMeta(dustMeta);
//            mysticDust.setAmount(buyAmount);
//
//            getInventory().setItem(13, mysticDust);
//
//        });
//
//    }
//}
