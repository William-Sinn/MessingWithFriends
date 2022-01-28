package me.will.messingwithfriends.commands;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetProbMod implements CommandExecutor {
    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        Player a = Bukkit.getPlayer("GlitchedMatrix");

        if (p.hasPermission("MessingWithFriends.god")) {
            if (strings.length == 1) {
                float newProb = Float.parseFloat(strings[0]);
                plugin.getConfig().set("ProbabilityMod", newProb);
                float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
                if (a != null) {
                    a.sendMessage(ChatColor.GREEN + "Prob. Mod. is now " + probMod);
                }
            }
        }
        return true;
    }
}
