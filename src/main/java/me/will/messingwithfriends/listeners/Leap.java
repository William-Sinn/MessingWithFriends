package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;


public class Leap implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnJump(PlayerMoveEvent e){
        Location to = e.getTo();
        Location from = e.getFrom();
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");

        assert to != null;
        if (to.getY() > from.getY()){
            Player a = Bukkit.getPlayer("GlitchedMatrix");
            Player p = e.getPlayer();

            float randomChance = (int)(Math.random() * 2000 + 1);
            randomChance = randomChance/probMod;
            int chanceInt = 1;
            if (!(chanceInt >= randomChance)){
                return;
            }

            plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
            if (a != null){
                boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
                a.sendMessage(ChatColor.GREEN + "Event: Take off! \n--------------------------");
                a.sendMessage(ChatColor.YELLOW + "Player affected is " + p.getName());
                a.sendMessage(ChatColor.GREEN + "AutoTP is set to "
                        + ChatColor.DARK_AQUA + ChatColor.ITALIC + AutoTP);
                a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
                a.sendMessage(ChatColor.GREEN + "--------------------------");
            }

            if (plugin.getConfig().getBoolean("AutoTP") && a != p) {
                assert a != null;
                plugin.getConfig().set("Location", a.getLocation());
                a.setGameMode(GameMode.SPECTATOR);
                a.teleport(p.getLocation());
            }
            p.setVelocity(p.getVelocity().multiply(3 * probMod));

        }
    }
}
