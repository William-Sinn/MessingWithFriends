package me.will.messingwithfriends.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodTestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (p.hasPermission("MessingWithFriends.god")) {
            if (p.isInvulnerable()) {
                p.setInvulnerable(false);
                p.sendMessage(ChatColor.DARK_PURPLE + "god mode off");

            } else {
                p.setInvulnerable(true);
                p.sendMessage(ChatColor.LIGHT_PURPLE + "god mode on");

            }
            return true;
        }
        return true;
    }
}
