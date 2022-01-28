package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.plugin.Plugin;


public class DontMakeAHoe implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void onHoeCraft(CraftItemEvent e){
        if (!(e.getRecipe().getResult().getType() == Material.WOODEN_HOE ||
                e.getRecipe().getResult().getType() == Material.STONE_HOE ||
                e.getRecipe().getResult().getType() == Material.IRON_HOE ||
                e.getRecipe().getResult().getType() == Material.GOLDEN_HOE ||
                e.getRecipe().getResult().getType() == Material.DIAMOND_HOE ||
                e.getRecipe().getResult().getType() == Material.NETHERITE_HOE)) {
            return;
        }
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float randomChance = (int)(Math.random() * 100 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 80;
        if (!(chanceInt >= randomChance)){
            return;
        }

        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = (Player) e.getWhoClicked();

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
            boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
            a.sendMessage(ChatColor.GREEN + "Event: Was that an Enderman? \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player triggered is " + p.getName());
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
            Bukkit.getScheduler().runTaskLater(plugin, ()->{
                p.teleport(p.getLocation().add(0,2 * probMod,0));
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_AMBIENT, 1, 1);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            }, 40L);
        }, 100L);

    }
}
