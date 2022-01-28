package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.plugin.Plugin;



public class SilverPickaxe implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnPickaxeBreak(PlayerItemBreakEvent e){
        if (!(e.getBrokenItem().getType() == Material.WOODEN_PICKAXE ||
                e.getBrokenItem().getType() == Material.STONE_PICKAXE ||
                e.getBrokenItem().getType() == Material.IRON_PICKAXE ||
                e.getBrokenItem().getType() == Material.GOLDEN_PICKAXE ||
                e.getBrokenItem().getType() == Material.DIAMOND_PICKAXE ||
                e.getBrokenItem().getType() == Material.NETHERITE_PICKAXE)) {
            return;
        }

        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float chanceFire = (int)(Math.random() * 100 + 1);
        chanceFire = chanceFire/probMod;
        int chanceInt = 10;
        if (!(chanceInt >= chanceFire)){
            return;
        }
        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getPlayer();

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
                boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
                a.sendMessage(ChatColor.GREEN + "Event: Silverfishing \n--------------------------");
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

            float randLength = (int) (Math.random() * 15 + 1);
            randLength = randLength * probMod;

            assert a!= null;
            a.sendMessage(ChatColor.GREEN + "Will Loop " + ChatColor.BLUE + ChatColor.ITALIC +
                    randLength + ChatColor.GREEN + " times");

            for (int i =  0; i < randLength; i++) {
                int randTime = (int)(Math.random() * 1600 + 1);
                Bukkit.getScheduler().runTaskLater(plugin, () ->
                        p.getWorld().spawnEntity(p.getLocation().add(0, 2, 0), EntityType.SILVERFISH),
                        (long) (randTime * probMod));
            }
        }, 100L);
    }
}
