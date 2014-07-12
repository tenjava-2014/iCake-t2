package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.tenjava.entries.iCake.t2.game.GameState;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player && GameState.getCurrentState() == GameState.INVINCIBLE) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onEntityBlockForm(EntityChangeBlockEvent e) {
        Entity entity = e.getEntity();
        Block block = e.getBlock();
        
        if(entity.hasMetadata("core")) {
            e.setCancelled(true);
            block.setType(block.getRelative(BlockFace.DOWN).getType() == Material.WOOL ? Material.AIR : Material.LAVA);
        }
    }
    
}
