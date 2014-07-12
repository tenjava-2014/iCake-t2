package com.tenjava.entries.iCake.t2;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.listeners.*;
import com.tenjava.entries.iCake.t2.timers.GameLoop;
import com.tenjava.entries.iCake.t2.utils.Board;
import com.tenjava.entries.iCake.t2.utils.Chat;

public class TenJava extends JavaPlugin {

    private static TenJava instance;
    private static Board board;

    public void onEnable() {
        instance = this;
        board = new Board();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);

        WorldUtils.createWorld();
        new GameLoop().runTaskTimer(this, 20 * 3, 20);
    }

    public void onDisable() {
        for(Player player : WorldUtils.getCoreWorld().getPlayers()) {
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);

            for(PotionEffect pot : player.getActivePotionEffects()) {
                player.removePotionEffect(pot.getType());
            }
            
            Chat.sendMessage(player, "&a&oThe server has been reloaded!");
        }
        
        instance = null;
    }

    public static TenJava getInstance() {
        return instance;
    }
    
    public static Board getBoard() {
        return board;
    }

}
