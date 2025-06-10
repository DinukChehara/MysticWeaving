package me.tomqnto.mysticWeaving.commands;

import me.tomqnto.mysticWeaving.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (args.length<1){
            sender.sendRichMessage("<red>You must specify a player");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player==null){
            sender.sendRichMessage("<red>Could not find " + args[0]);
            return true;
        }

        PlayerData.setDefaults(player);
        sender.sendRichMessage("<green>Successfully reset " + args[0] + "'s data");

        return true;
    }
}
