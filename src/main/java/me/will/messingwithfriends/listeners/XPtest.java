package me.will.messingwithfriends.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class XPtest implements Listener {

    @EventHandler
    public void onXPtest(ExpBottleEvent e){

        e.setShowEffect(false);
    }
}
