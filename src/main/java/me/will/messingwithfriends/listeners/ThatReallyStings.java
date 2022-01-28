package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;


public class ThatReallyStings implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnBeeDeath(EntityDeathEvent e){

        Entity deadEntity = e.getEntity();
        if (deadEntity.getType() == EntityType.BEE){
            Player a = Bukkit.getPlayer("GlitchedMatrix");
            Player p = e.getEntity().getKiller();

            if (p == null){
                return;
            }
            float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");

            float randomChance = (int)(Math.random() * 100 + 1);
            randomChance = randomChance/probMod;
            int chanceInt = 50;
            if (!(chanceInt >= randomChance)){
                return;
            }

            plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
            if (a != null){
                boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
                a.sendMessage(ChatColor.GREEN + "Event: That Really Stings \n--------------------------");
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

                Bukkit.getScheduler().runTaskLater(plugin, ()->{
                    p.playSound(p.getLocation(), Sound.ENTITY_BEE_LOOP_AGGRESSIVE, 1, 1);
                    p.getWorld().createExplosion(p.getLocation(), 0.1F * probMod, false, false, null);
                }, 40L);

            }, 100L);

        }
    }
}
