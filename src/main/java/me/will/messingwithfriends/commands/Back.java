package me.will.messingwithfriends.commands;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Locale;
import java.util.Objects;

public class Back implements CommandExecutor {
    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = Bukkit.getPlayer("GlitchedMatrix");

        assert p != null;
        p.teleport(Objects.requireNonNull(plugin.getConfig().getLocation("Location")));
        p.setGameMode(GameMode.SURVIVAL);
        p.sendMessage(commandSender.getName() + " tried to TP back");

        return true;
    }
}
