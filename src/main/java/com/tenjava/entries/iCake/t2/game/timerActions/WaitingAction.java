package com.tenjava.entries.iCake.t2.game.timerActions;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.tenjava.entries.iCake.t2.game.WorldUtils;

public class WaitingAction implements TimerAction {

    public void doAction() {
        World world = WorldUtils.getCoreWorld();
        world.setSpawnLocation(0, world.getHighestBlockYAt(world.getSpawnLocation()) + 2, 0);
        
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getGameMode() != GameMode.CREATIVE) {
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                
                player.setNoDamageTicks(20 * 3);
                player.setFallDistance(-3f);
                
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                
                for(PotionEffect pot : player.getActivePotionEffects()) {
                    player.removePotionEffect(pot.getType());
                }
                
                player.setGameMode(GameMode.SURVIVAL);
                
                if(player.getVehicle() != null) {
                    player.getVehicle().eject();
                }
                
                player.teleport(world.getSpawnLocation());
                
                player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.LOG, 3) });
            }
        }
    }
    
}