package me.will.messingwithfriends.commands;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GetProbMod implements CommandExecutor {
    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        Player a = Bukkit.getPlayer("GlitchedMatrix");

        if (p.hasPermission("MessingWithFriends.god")) {
                if (a != null) {
                    float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
                    a.sendMessage(ChatColor.GREEN + "Prob. Mod. is now " + probMod);
                }
            }
        return true;
    }
}
