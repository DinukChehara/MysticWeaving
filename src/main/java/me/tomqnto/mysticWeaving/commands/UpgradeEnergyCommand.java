package me.tomqnto.mysticWeaving.commands;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UpgradeEnergyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (!(sender instanceof Player player)){
            sender.sendRichMessage("<red>A player is required to run this command!");
            return true;
        }

        int dust = PlayerData.getMysticDust(player);
        int currentLevel = PlayerData.getMaxEnergy(player);
        int upgradeCost = PlayerData.getEnergyUpgradeCost(player);

        if (upgradeCost>dust){
            player.sendRichMessage("<gray>You do not have enough <light_purple>Mystic Dust<gray> to upgrade your <light_purple>Mystic Energy Level");
            return true;
        }

        PlayerData.upgradeEnergyLevel(player);
        player.sendRichMessage("<light_purple>Upgraded your Mystic Energy Level! | " + currentLevel + " -> " + PlayerData.getMaxEnergy(player));

        return true;
    }
}
