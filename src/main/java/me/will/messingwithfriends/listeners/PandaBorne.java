package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class PandaBorne implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnEat(PlayerItemConsumeEvent e){
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float randomChance = (int)(Math.random() * 200 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 1;
        if (!(chanceInt >= randomChance)){
            return;
        }

        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getPlayer();

        int randomPanda = (int)(Math.random() * list.size());

        Player t = list.get(randomPanda);

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
            boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
            a.sendMessage(ChatColor.GREEN + "Event: Pandamonium \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player triggered is " + p.getName());
            a.sendMessage(ChatColor.YELLOW + "Player effected is " + t.getName());
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
            Location playerLoc = t.getLocation();
            Bukkit.getScheduler().runTaskLater(plugin, ()->{
            t.getWorld().spawnEntity(playerLoc, EntityType.PANDA);
            }, 40L);

        }, 100L);

    }
}
