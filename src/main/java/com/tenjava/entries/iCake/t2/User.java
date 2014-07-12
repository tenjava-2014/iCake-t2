package com.tenjava.entries.iCake.t2;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class User {

    private Player player;
    
    private BukkitTask borderTask;
    
    public User(Player player) {
        this.player = player;
        
        this.borderTask = null;
    }
    
    //   RIP LOMBOK   \\
    public Player getPlayer() { return player; }
    public BukkitTask getBorderTask() { return borderTask; }
    public void setBorderTask(BukkitTask task) { this.borderTask = task; }
    // I WILL MISS YOU \\
    
    public void cleanup() {
        player = null;
        
        if(borderTask != null) {
            borderTask.cancel();
            borderTask = null;
        }
    }
    
}