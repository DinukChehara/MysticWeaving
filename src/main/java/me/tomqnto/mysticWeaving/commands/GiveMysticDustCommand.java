package me.tomqnto.mysticWeaving.commands;

import me.tomqnto.mysticWeaving.Utils;
import me.tomqnto.mysticWeaving.managers.MysticItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveMysticDustCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)){
            sender.sendRichMessage("<red>A player is required to run this command");
            return true;
        }

        if (args.length==0){
            player.sendRichMessage("<red>Amount must be specified!");
            return true;
        }

        int amount;

        try {
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendRichMessage("<red>Invalid amount!");
            return true;
        }

        ItemStack dust = MysticItems.getMysticDust(amount);

        Utils.give(player, dust);
        
        return true;
    }
}
