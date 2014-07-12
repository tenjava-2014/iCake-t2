package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.tenjava.entries.iCake.t2.game.GameState;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player && GameState.getCurrentState() == GameState.INVINCIBLE) {
            e.setCancelled(true);
        }
    }

}
