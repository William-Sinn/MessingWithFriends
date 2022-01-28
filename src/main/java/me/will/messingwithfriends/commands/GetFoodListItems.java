package me.will.messingwithfriends.commands;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GetFoodListItems implements CommandExecutor {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if (player.hasPermission("MessingWithFriends.changeFood")) {
            if (strings.length == 1) {
                plugin.getConfig().getStringList("FoodList").set(1, strings[0]);
                plugin.getConfig().set("Food", strings[0]);
                player.sendMessage("FoodList, index 1 is now " + strings[0]);
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
