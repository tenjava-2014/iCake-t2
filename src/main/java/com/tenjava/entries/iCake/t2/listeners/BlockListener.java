package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

import com.tenjava.entries.iCake.t2.game.GameState;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if(player.getGameMode() != GameMode.CREATIVE && GameState.getCurrentState() != GameState.STARTED) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if(player.getGameMode() != GameMode.CREATIVE && GameState.getCurrentState() != GameState.STARTED) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(HangingBreakByEntityEvent e) {
        if(e.getRemover() instanceof Player) {
            Player player = (Player)e.getRemover();

            if(player.getGameMode() != GameMode.CREATIVE && GameState.getCurrentState() != GameState.STARTED) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onPlace(HangingPlaceEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();

            if(player.getGameMode() != GameMode.CREATIVE && GameState.getCurrentState() != GameState.STARTED) {
                e.setCancelled(true);
            }
        }
    }

}
