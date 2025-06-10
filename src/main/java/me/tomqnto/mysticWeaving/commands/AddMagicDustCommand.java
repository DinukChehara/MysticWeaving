package me.tomqnto.mysticWeaving.commands;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddMagicDustCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (args.length>2){
            sender.sendRichMessage("<red>A player and exp amount must be specified!");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        PlayerData.addMysticDust(player, Integer.parseInt(args[1]));
        sender.sendRichMessage("<green>Added " + args[1] + " Magic Exp to " + args[0]);

        return true;
    }
}
