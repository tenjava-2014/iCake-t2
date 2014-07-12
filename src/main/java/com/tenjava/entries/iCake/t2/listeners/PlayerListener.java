package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;
import com.tenjava.entries.iCake.t2.timers.BorderTask;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        UserManager.getUser(e.getPlayer());
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        UserManager.delUser(e.getPlayer());
    }
    
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        UserManager.delUser(e.getPlayer());
    }
    
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        User user = UserManager.getUser(player);
        
        if((e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) && player.getWorld().getName().equalsIgnoreCase(WorldUtils.WORLD_NAME)) {
            if(user.getBorderTask() == null && (Math.abs(e.getTo().getBlockX()) >= 500 || Math.abs(e.getTo().getBlockZ()) >= 500)) {
                user.setBorderTask(new BorderTask(player).runTaskTimer(TenJava.getInstance(), 10, 5));
            }
        }
    }
    
}