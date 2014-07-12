package com.tenjava.entries.iCake.t2;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.listeners.*;
import com.tenjava.entries.iCake.t2.timers.GameLoop;

public class TenJava extends JavaPlugin {

    private static TenJava instance;
    
    /**
     * <strong>THEMES</strong>:</br>
     * How can energy be harnessed and used in the Minecraft world?</br>
     * energy: the strength and vitality required for sustained physical or mental activity</br>
     * </br>
     * 
     * <strong>IDEAS</strong>:</br>
     * Gamemode, some kind of core spawns and the energy level meter rises(?) some kind of lava explosion or something</br>
     * Something to do with nuclear EXPLOSIONS
     */
    public void onEnable() {
        instance = this;
        
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        
        WorldUtils.createWorld();
        new GameLoop().runTaskTimer(this, 20 * 3, 20);
    }

    public void onDisable() {
        instance = null;
    }
    
    public static TenJava getInstance() { return instance; } //again, no lombok :(((

}
