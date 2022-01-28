package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;


public class GoldPigSound implements Listener {

    @EventHandler
    public void OnMineGold(BlockBreakEvent e){
        if (!(e.getBlock().getType() == Material.GOLD_ORE)){
            return;
        }
        Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");

        float randomChance = (int)(Math.random() * 10 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 1;
        if (!(chanceInt >= randomChance)){
            return;
        }

        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getPlayer();
        p.playSound(p.getLocation(), Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 1);
        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);

        if (a != null){
            a.sendMessage(ChatColor.GREEN + "Event: Gold Digger \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player effected is " + p.getName());
            a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
            a.sendMessage(ChatColor.GREEN +"--------------------------");
        }

    }
}
