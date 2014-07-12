package com.tenjava.entries.iCake.t2.timers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;
import com.tenjava.entries.iCake.t2.utils.Chat;

public class BorderTask extends BukkitRunnable {

    private Player player;
    
    public BorderTask(Player player) {
        this.player = player;
    }
    
    public void run() {
        if(player.getLocation().getWorld().getName().equals(WorldUtils.WORLD_NAME)) {
            int x = Math.abs(player.getLocation().getBlockX()), z = Math.abs(player.getLocation().getBlockZ());
            
            if(x >= 500 || z >= 500) {
                player.damage(2.0);
                Chat.sendMessage(player, "&4&lMOVE OUTTA THE WAY. GO BACK.");
            } else {
                stop();
            }
        } else {
            stop();
        }
    }
    
    public void stop() {
        this.cancel();
        UserManager.getUser(player).setBorderTask(null);
        
        Chat.sendMessage(player, "&aNICE. THANK YOU.");
    }

}