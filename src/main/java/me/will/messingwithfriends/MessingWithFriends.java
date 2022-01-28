package me.will.messingwithfriends;

import me.will.messingwithfriends.commands.*;
import me.will.messingwithfriends.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MessingWithFriends extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The fun has begun....");

        // getServer().getPluginManager().registerEvents(new XPtest(),this);
        // getServer().getPluginManager().registerEvents(new FloatOnCoalBreak(), this);
        // getServer().getPluginManager().registerEvents(new ArmorBreakEvent(), this);
        // getServer().getPluginManager().registerEvents(new TreeFightsBack(), this);
        // getServer().getPluginManager().registerEvents(new IAmInvincible(), this);
        // getServer().getPluginManager().registerEvents(new ThatReallyStings(), this);
        // getServer().getPluginManager().registerEvents(new GoToNetherSick(), this);
        // getServer().getPluginManager().registerEvents(new TPBack(), this);
        // getServer().getPluginManager().registerEvents(new Revenge(), this);
        // getServer().getPluginManager().registerEvents(new Leap(), this);
        // getServer().getPluginManager().registerEvents(new PandaBorne(), this);
        // getServer().getPluginManager().registerEvents(new GroundChange(), this);
        // getServer().getPluginManager().registerEvents(new BlockDisplace(), this);
        // getServer().getPluginManager().registerEvents(new DontHitThem(), this);
        // getServer().getPluginManager().registerEvents(new DontKillThem(), this);
        // getServer().getPluginManager().registerEvents(new DontMakeAHoe(), this);
        // getServer().getPluginManager().registerEvents(new SilverPickaxe(), this);
        // getServer().getPluginManager().registerEvents(new OnGlassBreak(), this);
        // getServer().getPluginManager().registerEvents(new GhastScream(), this);
        // getServer().getPluginManager().registerEvents(new RottenFleshEaten(), this);
        // getServer().getPluginManager().registerEvents(new GoldPigSound(), this);
        // getServer().getPluginManager().registerEvents(new CircleOfLife(), this);
        // getServer().getPluginManager().registerEvents(new VeggieTales(), this);
        getServer().getPluginManager().registerEvents(new NoTrample(), this);
        getServer().getPluginManager().registerEvents(new NoTramplePlayer(), this);
        getServer().getPluginManager().registerEvents(this,this);
        Objects.requireNonNull(getCommand("god")).setExecutor(new GodTestCommand());
        Objects.requireNonNull(getCommand("changeFood")).setExecutor(new GetFoodListItems());
        Objects.requireNonNull(getCommand("toggleTP")).setExecutor(new ToggleAutoTP());
        Objects.requireNonNull(getCommand("backTP")).setExecutor(new Back());
        Objects.requireNonNull(getCommand("setProbMod")).setExecutor(new SetProbMod());
        Objects.requireNonNull(getCommand("getProbMod")).setExecutor(new GetProbMod());
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("die")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                p.setHealth(0.0);
                p.sendMessage(ChatColor.RED + "you die now");
            }
        }if (command.getName().equalsIgnoreCase("print")){
            Player p = (Player) sender;
            String listItem = getConfig().getStringList("FoodList").get(1);
            String food = getConfig().getString("Food");
            p.sendMessage(food + " is the value in the config\n" + listItem + " is here too");
        }
        return true;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        System.out.println("A player has joined the game");
        String message = "Hey, " + event.getPlayer().getName() + " this message was going to be cooler but its not, sorry";

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Plugin plugin = MessingWithFriends.getPlugin(MessingWithFriends.class);
        float probMod = (float) plugin.getConfig().getDouble("ProbabilityMod");
        System.out.println("The fun has....ended?");
        System.out.println("Prob. Mod. was " + probMod + " at server shut down");

    }
}
