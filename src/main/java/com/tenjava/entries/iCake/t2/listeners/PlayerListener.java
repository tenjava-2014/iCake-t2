package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import com.tenjava.entries.iCake.t2.managers.UserManager;

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
    
}