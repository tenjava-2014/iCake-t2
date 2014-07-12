package com.tenjava.entries.iCake.t2;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.tenjava.entries.iCake.t2.managers.UserManager;

public class User {

    private Player player;
    
    private BukkitTask borderTask, powerTask, noPowerTask;
    private int power;
    
    public User(Player player) {
        this.player = player;
        
        this.borderTask = null;
        this.power = UserManager.MAX_POWER;
    }
    
    //   RIP LOMBOK   \\
    public Player getPlayer() { return player; }
    public BukkitTask getBorderTask() { return borderTask; }
    public void setBorderTask(BukkitTask borderTask) { this.borderTask = borderTask; }
    
    public BukkitTask getPowerTask() { return powerTask; }
    public void setPowerTask(BukkitTask powerTask) { this.powerTask = powerTask; }
    
    public BukkitTask getNoPowerTask() { return noPowerTask; }
    public void setNoPowerTask(BukkitTask noPowerTask) { this.noPowerTask = noPowerTask; }
    
    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; player.setLevel(power); }
    public void increasePower() { this.power++; if(power >= 100) { power = 100; } }
    // I WILL MISS YOU \\
    
    public void cleanup() {
        player = null;
        
        if(borderTask != null) {
            borderTask.cancel();
            borderTask = null;
        }
    }
    
}