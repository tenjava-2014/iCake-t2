package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

import com.tenjava.entries.iCake.t2.game.GameState;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.timers.GameLoop;
import com.tenjava.entries.iCake.t2.utils.Chat;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        
        if(player.getGameMode() != GameMode.CREATIVE) {
            if(GameState.getCurrentState() != GameState.STARTED) {
                e.setCancelled(true);
            } else if(block.getType() == Material.GOLD_BLOCK && WorldUtils.getCoreBlocks().contains(block.getLocation())) {
                WorldUtils.getCoreBlocks().remove(block.getLocation());
                
                if(WorldUtils.getCoreBlocks().isEmpty()) {
                    Chat.broadcast("&6The core has been &lfully&r&6 destroyed!");
                    
                    GameState.getCurrentState().doAction();
                    GameLoop.setTime(GameState.getCurrentState().getTimeForState());
                } else {
                    Chat.broadcast("&6" + player.getName() + " destroyed one of the core blocks! &7(" + WorldUtils.getCoreBlocks().size() + "/4 remain)");
                }
            }
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
