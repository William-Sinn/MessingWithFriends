package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.*;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;


public class CircleOfLife implements Listener {

    @EventHandler
    public void OnFarmAnimalDeath(EntityDeathEvent e){
        if (!(e.getEntity().getType() == EntityType.PIG||
            e.getEntity().getType() == EntityType.CHICKEN||
            e.getEntity().getType() == EntityType.COW||
            e.getEntity().getType() == EntityType.SHEEP||
            e.getEntity().getType() == EntityType.RABBIT
        )){
            return;
        }
        Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");


        float randomChance = (int)(Math.random() * 10 + 1);
        randomChance = randomChance/probMod;

        int chanceInt = 1;
        if (!(chanceInt >= randomChance)){
            return;
        }

        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getEntity().getKiller();
        if (p == null){
            return;
        }

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
        Ageable entity = (Ageable)
                Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(),
                e.getEntityType());
        entity.setBaby();

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        if (a != null){
            a.sendMessage(ChatColor.GREEN + "Event: Reincarnation \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player effected is " + p.getName());
            a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
            a.sendMessage(ChatColor.GREEN +"--------------------------");
        }

    }
}
