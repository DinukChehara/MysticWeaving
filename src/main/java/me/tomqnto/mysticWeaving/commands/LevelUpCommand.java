package me.tomqnto.mysticWeaving.commands;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LevelUpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (!(sender instanceof Player player)){
            sender.sendRichMessage("<red>A player is required to run this command!");
            return true;
        }

        int paid = PlayerData.getLevelUpgradePaid(player);
        int cost = PlayerData.getNextLevelupCost(player);
        int level = PlayerData.getLevel(player);

        if (paid>=cost){
            PlayerData.levelup(player);
            player.sendRichMessage("<light_purple>You leveled up | " + level + " -> " + PlayerData.getLevel(player));
            if (paid>cost){
                paid-=cost;
                PlayerData.setLevelUpgradePaid(player, paid);
            }
        } else{
            player.sendRichMessage("<gray>You do not have enough <light_purple>Mystic Dust<gray> to level up");
        }
        return true;
    }
}
