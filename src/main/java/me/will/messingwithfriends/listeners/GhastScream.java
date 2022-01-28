package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.plugin.Plugin;


public class GhastScream implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnMobTame(EntityTameEvent e){
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float randomChance = (int)(Math.random() * 100 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 25;
        if (!(chanceInt >= randomChance)){
            return;
        }

        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = (Player) e.getOwner();
        p.playSound(p.getLocation(), Sound.ENTITY_GHAST_AMBIENT, 1, 1);

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
            a.sendMessage(ChatColor.GREEN + "Event: A Ghastly Pet \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player triggered is " + p.getName());
            a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
            a.sendMessage(ChatColor.GREEN +"--------------------------");
        }

    }
}
