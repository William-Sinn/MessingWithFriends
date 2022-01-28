package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class GoToNetherSick implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnNetherTP(PlayerChangedWorldEvent e){

        Player p = e.getPlayer();
        World worldTo = p.getWorld();

        if (worldTo.getName().equals("world_nether")){
            float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
            float randomChance = (int)(Math.random() * 100 + 1);
            randomChance = randomChance/probMod;
            int chanceInt = 33;
            if (!(chanceInt >= randomChance)){
                return;
            }
            Player a = Bukkit.getPlayer("GlitchedMatrix");

            plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
            if (a != null){
                boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
                a.sendMessage(ChatColor.GREEN + "Event: Feeling Under the Nether \n--------------------------");
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
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (250 * probMod),
                            (int) (1 * probMod), false, false, false));
                }, 1L);
            }, 1L);
        }
    }
}
