package me.will.messingwithfriends.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoTramplePlayer implements Listener {

    @EventHandler
    public void OnTramplePlay(PlayerInteractEvent e){

        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND){
            e.setCancelled(true);
        }
    }
}
