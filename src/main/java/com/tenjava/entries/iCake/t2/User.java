package com.tenjava.entries.iCake.t2;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.tenjava.entries.iCake.t2.managers.UserManager;
import com.tenjava.entries.iCake.t2.utils.Chat;

public class User {

    private Player player;

    private BukkitTask borderTask, powerTask, noPowerTask;
    private int power;

    public User(Player player) {
        this.player = player;

        this.borderTask = null;
        this.power = UserManager.MAX_POWER;
    }

    public Player getPlayer() {
        return player;
    }

    public BukkitTask getBorderTask() {
        return borderTask;
    }

    public void setBorderTask(BukkitTask borderTask) {
        this.borderTask = borderTask;
    }

    public BukkitTask getPowerTask() {
        return powerTask;
    }

    public void setPowerTask(BukkitTask powerTask) {
        this.powerTask = powerTask;
    }

    public BukkitTask getNoPowerTask() {
        return noPowerTask;
    }

    public void setNoPowerTask(BukkitTask noPowerTask) {
        this.noPowerTask = noPowerTask;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
        player.setLevel(power);

        switch(power) {
            case 10:
                Chat.sendMessage(player, "&aHmmm... My power level is becoming low...");
                break;
            case 5:
                Chat.sendMessage(player, "&cOk, now I should really think about fixing my power..");
                break;
        }
    }

    public void cleanup() {
        player = null;

        if(borderTask != null) {
            borderTask.cancel();
            borderTask = null;
        }
        
        if(powerTask != null) {
            powerTask.cancel();
            powerTask = null;
        }
        
        if(noPowerTask != null) {
            noPowerTask.cancel();
            noPowerTask = null;
        }
    }

}
