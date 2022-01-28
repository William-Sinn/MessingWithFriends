package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;


public class BlockDisplace implements Listener {

    @EventHandler
    public void OnBuild(BlockPlaceEvent e){
        Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float randomChance = (int)(Math.random() * 200 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 1;
        if (!(chanceInt >= randomChance)){
            return;
        }

        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getPlayer();
        Block b =  e.getBlock();
        e.setCancelled(true);

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
            a.sendMessage(ChatColor.GREEN + "Event: Misplaced \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player triggered is " + p.getName());
            a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
            a.sendMessage(ChatColor.GREEN +"--------------------------");
        }

        int randFace = (int)(Math.random() * 5 + 1);

        if (randFace == 1) {
            b.getRelative(BlockFace.UP).setType(b.getType());
        }else if (randFace == 2) {
            b.getRelative(BlockFace.EAST).setType(b.getType());
        }else if (randFace == 3) {
            b.getRelative(BlockFace.WEST).setType(b.getType());
        }else if (randFace == 4) {
            b.getRelative(BlockFace.NORTH).setType(b.getType());
        }else if (randFace == 5) {
            b.getRelative(BlockFace.SOUTH).setType(b.getType());
        }


    }
}
