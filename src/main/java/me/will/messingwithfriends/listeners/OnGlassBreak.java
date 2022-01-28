package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class OnGlassBreak implements Listener {

    @EventHandler
    public void OnGlassPlace(BlockPlaceEvent e){

        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getPlayer();
        Block b =  e.getBlock();
        if (!(b.getType() == Material.GLASS ||
            b.getType() == Material.GLASS_PANE ||
            b.getType() == Material.TINTED_GLASS ||
            b.getType() == Material.BLACK_STAINED_GLASS ||
            b.getType() == Material.BLACK_STAINED_GLASS_PANE ||
            b.getType() == Material.BLUE_STAINED_GLASS ||
            b.getType() == Material.BLUE_STAINED_GLASS_PANE ||
            b.getType() == Material.RED_STAINED_GLASS ||
            b.getType() == Material.RED_STAINED_GLASS_PANE ||
            b.getType() == Material.WHITE_STAINED_GLASS ||
            b.getType() == Material.WHITE_STAINED_GLASS_PANE
        )){
            return;
        }

        Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float randomChance = (int)(Math.random() * 50 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 1;
        if (!(chanceInt >= randomChance)){
            return;
        }

        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        item.addEnchantment(Enchantment.SILK_TOUCH, 1);
        b.breakNaturally(item);
        p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
            a.sendMessage(ChatColor.GREEN + "Event: What a Pane \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player triggered is " + p.getName());
            a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
            a.sendMessage(ChatColor.GREEN +"--------------------------");
        }

    }
}
