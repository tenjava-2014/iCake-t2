package com.tenjava.entries.iCake.t2;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.iCake.t2.listeners.PlayerListener;

public class TenJava extends JavaPlugin {

    /**
     * <strong>THEMES</strong>:</br>
     * How can energy be harnessed and used in the Minecraft world?</br>
     * energy: the strength and vitality required for sustained physical or mental activity</br>
     * </br>
     * What can increase Minecraft's replay value?
     */
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public void onDisable() {
        
    }

}
