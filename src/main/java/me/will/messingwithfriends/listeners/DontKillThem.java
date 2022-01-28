package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;


public class DontKillThem implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void OnHitPet(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player)){
            return;
        }
        if (!(e.getEntity() instanceof Tameable)){
            return;
        }
        if (!(((Tameable) e.getEntity()).isTamed())){
            return;
        }
        if (!(e.getEntity().isDead())){
            return;
        }
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");


        float randomChance = (int)(Math.random() * 5 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 1;
        if (!(chanceInt >= randomChance)){
            return;
        }

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = (Player) e.getDamager();
        Tameable pet = (Tameable) e.getEntity();
        Player owner = (Player) pet.getOwner();

        if (a != null){
            a.sendMessage(ChatColor.GREEN + "Event: Don't Kill Them \n--------------------------");
            a.sendMessage(ChatColor.YELLOW + "Player triggered is " + p.getName());
            assert owner != null;
            a.sendMessage(ChatColor.YELLOW + "Pet's owner is " + owner.getName());
            a.sendMessage(ChatColor.YELLOW + "Pet is " + pet.getName());
            a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));
            a.sendMessage(ChatColor.GREEN +"--------------------------");
        }
        p.damage(4 * probMod);
        p.playSound(p.getLocation(), Sound.ENTITY_WOLF_GROWL, 1, 1);
        Bukkit.getScheduler().runTaskLater(plugin, ()->{
            for (int i = 0; i != 6; i++) {
                Wolf w = (Wolf) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.WOLF);
                w.setAngry(true);
                w.setTarget(p);
            }
        }, 100L);
    }

}
