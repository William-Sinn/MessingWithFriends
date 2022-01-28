package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;



public class TPBack implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnFoodConsume(PlayerItemConsumeEvent e){
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float chanceFire = (int)(Math.random() * 200 + 1);
        chanceFire = chanceFire/probMod;
        int chanceInt = 1;
        if (!(chanceInt >= chanceFire)){
            return;
        }

        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getPlayer();
        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);

        int randomChance = (int)(Math.random() * 3 + 1);
        int numTimes;
        String event;
        if (randomChance == 1) {
            event = "Chewing Rubber Bands";
            numTimes = (int) (7 * probMod);
        }else if (randomChance == 2){
            event = "Fasting Faster";
            numTimes = (int) (3 * probMod);
        }else {
            event = "Fasting";
            e.setCancelled(true);
            if (a != null){
                boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
                a.sendMessage(ChatColor.GREEN + "Event: " + event + " \n--------------------------");
                a.sendMessage(ChatColor.YELLOW + "Player affected is " + p.getName());
                a.sendMessage(ChatColor.GREEN + "AutoTP is set to "
                        + ChatColor.DARK_AQUA + ChatColor.ITALIC + AutoTP);
                a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
                a.sendMessage(ChatColor.GREEN +"--------------------------");
            }
            return;
        }
        if (a != null){
            boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");
            a.sendMessage(ChatColor.GREEN + "Event: " + event + " \n--------------------------");
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


            int randLength = (int) (Math.random() * numTimes + 1);
            assert a!= null;
            a.sendMessage(ChatColor.GREEN + "Will Loop " + ChatColor.BLUE + ChatColor.ITALIC +
                    randLength + ChatColor.GREEN + " times");

            for (int i =  0; i != randLength; i++) {
                int randTime = (int)(Math.random() * 500 + 1);
                Location playerLocation = p.getLocation();
                a.sendMessage(ChatColor.GREEN + "Length: " + ChatColor.BLUE + randTime/20 + "s");

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    if (randomChance == 1) {
                        p.teleport(playerLocation);

                    }else{
                        p.setVelocity(p.getVelocity().multiply(3 * probMod));
                    }
                    a.sendMessage(ChatColor.GREEN + "Loop Triggered");
                }, randTime);
            }
        }, 100L);
    }
}
