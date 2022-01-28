package me.will.messingwithfriends.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class NoTrample implements Listener {

    @EventHandler
    public void OnTrample(EntityInteractEvent e){

        if (e.getBlock().getType() == Material.FARMLAND && !(e.getEntity() instanceof Player)){
            e.setCancelled(true);
            }
        }
    }
