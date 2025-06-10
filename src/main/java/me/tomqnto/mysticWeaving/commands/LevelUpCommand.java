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

        int dust = PlayerData.getMysticDust(player);
        int cost = PlayerData.getNextLevelupCost(player);
        int level = PlayerData.getLevel(player);

        if (dust>=cost){
            PlayerData.addMysticDust(player,-1 * cost);
            PlayerData.levelup(player);
            player.sendRichMessage("<light_purple>You levelled up | " + level + " -> " + PlayerData.getLevel(player));
            return true;
        } else{
            player.sendRichMessage("<gray>You do not have enough <light_purple>Mystic Dust<gray> to level up");
            return true;
        }
    }
}
