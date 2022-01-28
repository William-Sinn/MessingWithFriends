package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class IAmInvincible implements Listener {

    @EventHandler
    public void onMyDeath(EntityDamageEvent e){
        if( e.getEntity() instanceof Player){
            Player a = Bukkit.getPlayer("GlitchedMatrix");
            Player p = (Player) e.getEntity();
            if (a == p){
                if(p.getHealth() - e.getFinalDamage() < 1){
                    e.setCancelled(true);
                }
            }else{
                Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);
                float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
                float randomChance = (int)(Math.random() * 100 + 1);
                randomChance = randomChance/probMod;
                int chanceInt = 20;
                if (!(chanceInt >= randomChance)){
                    return;
                }
                plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
                if(p.getHealth() - e.getFinalDamage() < 1) {
                    if (a != null) {
                        boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
                        a.sendMessage(ChatColor.GREEN + "Event: Not Today! \n--------------------------");
                        a.sendMessage(ChatColor.YELLOW + "Player affected is " + p.getName());
                        a.sendMessage(ChatColor.GREEN + "AutoTP is set to "
                                + ChatColor.DARK_AQUA + ChatColor.ITALIC + AutoTP);
                        a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
                        a.sendMessage(ChatColor.GREEN + "--------------------------");
                    }

                    if (plugin.getConfig().getBoolean("AutoTP")) {
                        assert a != null;
                        plugin.getConfig().set("Location", a.getLocation());
                        a.setGameMode(GameMode.SPECTATOR);
                        a.teleport(p.getLocation());
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
}
