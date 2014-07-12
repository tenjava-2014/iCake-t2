package com.tenjava.entries.iCake.t2;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.listeners.*;
import com.tenjava.entries.iCake.t2.timers.GameLoop;

public class TenJava extends JavaPlugin {

    private static TenJava instance;

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

    public static TenJava getInstance() {
        return instance;
    }

}
