package me.tomqnto.mysticWeaving.commands;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UpgradeEnergyRegenAmountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (!(sender instanceof Player player)){
            sender.sendRichMessage("<red>A player is required to run this command!");
            return true;
        }

        int paid = PlayerData.getEnergyRegenUpgradePaid(player);
        int cost = PlayerData.getEnergyRegenAmountUpgradeCost(player);
        int currentAmount = PlayerData.getEnergyRegenAmount(player);

        if (paid>=cost){
            PlayerData.upgradeEnergyRegenAmount(player);
            player.sendRichMessage("<light_purple>Upgraded your Energy Regen Amount | +" + currentAmount + " -> +" + (currentAmount + 1));
        } else{
            player.sendRichMessage("<gray>You do not have enough <light_purple>Mystic Dust<gray> to upgrade your <light_purple>Energy Regen Amount");
        }
        return true;
    }
}
