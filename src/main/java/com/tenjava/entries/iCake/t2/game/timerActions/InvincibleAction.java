package com.tenjava.entries.iCake.t2.game.timerActions;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.iCake.t2.game.WorldUtils;

public class InvincibleAction implements TimerAction {

    public void doAction() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getGameMode() != GameMode.CREATIVE) {
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                
                player.getInventory().addItem(new ItemStack(Material.COOKED_FISH, 2));
            }
        }
        
        WorldUtils.spawnCore();
    }

}
