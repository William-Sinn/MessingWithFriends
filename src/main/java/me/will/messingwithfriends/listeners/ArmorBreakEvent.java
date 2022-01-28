package me.will.messingwithfriends.listeners;

import me.will.messingwithfriends.MessingWithFriends;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class ArmorBreakEvent implements Listener {

    Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent e) {
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        float randomChance = (int)(Math.random() * 100 + 1);
        randomChance = randomChance/probMod;
        int chanceInt = 50;

        if (!(chanceInt >= randomChance)){
            return;
        }

        plugin.getConfig().set("ProbabilityMod", probMod + 0.1);
        Player a = Bukkit.getPlayer("GlitchedMatrix");
        Player p = e.getPlayer();
        ItemStack brokenItem = e.getBrokenItem();
        String brokenItemName = brokenItem.getType().getEquipmentSlot().toString();
        boolean AutoTP = plugin.getConfig().getBoolean("AutoTP");

        if (!Objects.equals(brokenItemName, "HAND") &&
                !Objects.equals(brokenItemName, "OFF_HAND")){

            if (a != null) {
                a.sendMessage(ChatColor.GREEN + "Event: That Doesn't go There \n--------------------------");
                a.sendMessage(ChatColor.DARK_RED +  "The Armour that broke is " + brokenItem.getType().name());
                a.sendMessage(ChatColor.YELLOW + "Player affected is " + p.getName());
                a.sendMessage(ChatColor.GREEN + "AutoTP is set to "
                        + ChatColor.DARK_AQUA + ChatColor.ITALIC + AutoTP);
                a.sendMessage(ChatColor.YELLOW + "Prob. Mod. is now " + (probMod + 0.1));


            }

            Bukkit.getScheduler().runTaskLater(plugin, ()->{

                ItemStack mainHandItem = p.getInventory().getItemInMainHand();

                if (a != null) {
                    a.sendMessage(ChatColor.RED + "Main hand item is " + mainHandItem.getType().name()
                            + ChatColor.GREEN +"--------------------------");

                    if (plugin.getConfig().getBoolean("AutoTP") && a != p) {
                        plugin.getConfig().set("Location", a.getLocation());

                        a.setGameMode(GameMode.SPECTATOR);
                        a.teleport(p.getLocation());
                        }
                    }

                Bukkit.getScheduler().runTaskLater(plugin, ()->{

                if (Objects.equals(brokenItemName, "HEAD")) {
                    p.getInventory().setHelmet(mainHandItem);
                }

                if (Objects.equals(brokenItemName, "CHEST")) {
                    p.getInventory().setChestplate(mainHandItem);
                }

                if (Objects.equals(brokenItemName, "LEGS")) {
                    p.getInventory().setLeggings(mainHandItem);
                }

                if (Objects.equals(brokenItemName, "FEET")) {
                    p.getInventory().setBoots(mainHandItem);
                }

                p.getInventory().setItemInMainHand(null);

                }, 40L);

            }, 100L);
        }



    }
}
