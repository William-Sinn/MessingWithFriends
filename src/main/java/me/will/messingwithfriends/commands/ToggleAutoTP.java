package me.will.messingwithfriends.commands;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ToggleAutoTP implements CommandExecutor {
    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        Player a = Bukkit.getPlayer("GlitchedMatrix");

        if (p.hasPermission("MessingWithFriends.god")) {
            boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");

            if (AutoTP){
                plugin.getConfig().set("AutoTP", false);
            }else{
                plugin.getConfig().set("AutoTP", true);
            }
            if (a != null) {
                a.sendMessage(ChatColor.GREEN + "AutoTP is now set to "
                        + ChatColor.DARK_AQUA + ChatColor.ITALIC + plugin.getConfig().getBoolean("AutoTP"));
            }
            return true;
        }
        return true;
    }
}
