package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class GroundChange implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnBlockBreaking(BlockBreakEvent e){
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float randomChance = (int)(Math.random() * 400 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 1;
        if (!(chanceInt >= randomChance)){
            return;
        }
        Material brokenBlock = e.getBlock().getType();
        Player p = e.getPlayer();
        Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
        Material b2 = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
        Player a = Bukkit.getPlayer("GlitchedMatrix");

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
            boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
            a.sendMessage(ChatColor.GREEN + "Event: What was that? \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player affected is " + p.getName());
            a.sendMessage(ChatColor.GREEN + "AutoTP is set to "
                    + ChatColor.DARK_AQUA + ChatColor.ITALIC + AutoTP);
            a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
            a.sendMessage(ChatColor.GREEN +"--------------------------");
        }

        Bukkit.getScheduler().runTaskLater(plugin, ()->{

            if (plugin.getConfig().getBoolean("AutoTP") && a != p) {
                assert a != null;
                plugin.getConfig().set("Location", a.getLocation());
                a.setGameMode(GameMode.SPECTATOR);
                a.teleport(p.getLocation());
            }
            b.setType(brokenBlock);
            Bukkit.getScheduler().runTaskLater(plugin, ()->{
               b.setType(b2);
            }, (long) (10L * probMod));

        }, 100L);

    }
}
