package com.tenjava.entries.iCake.t2.timers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.GameState;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;
import com.tenjava.entries.iCake.t2.utils.Chat;

public class PowerTask extends BukkitRunnable {

    private Player player;
    
    private int sprintingTicks, swimmingTicks;
    
    public PowerTask(Player player) {
        this.player = player;
    }
    
    public void run() {
        User user = UserManager.getUser(player);
        
        if(GameState.getCurrentState() == GameState.STARTED && player.getWorld().getName().equalsIgnoreCase(WorldUtils.WORLD_NAME)) {
            if(player.isSprinting()) {
                if(sprintingTicks % 20 == 0) {
                    user.setPower(user.getPower() - 1);
                }
                
                sprintingTicks++;
            } else {
                sprintingTicks--;
                
                if(sprintingTicks < 0) {
                    sprintingTicks = 0;
                }
            }
            
            if(player.getLocation().getBlock().getType() == Material.WATER || player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
                if(swimmingTicks % 10 == 0) {
                    user.setPower(user.getPower() - 1);
                }
                
                swimmingTicks++;
            } else {
                swimmingTicks--;
                
                if(swimmingTicks < 0) {
                    swimmingTicks = 0;
                }
            }
            
            check(user);
        } else {
            this.cancel();
            user.setPowerTask(null);
        }
    }
    
    public void check(User user) {
        if(user.getPower() <= 0 && user.getNoPowerTask() == null) {
            user.setNoPowerTask(new NoPowerTask(player).runTaskTimer(TenJava.getInstance(), 10, 20));
            return;
        }
        
        switch(user.getPower()) {
            case 10:
                Chat.sendMessage(user.getPlayer(), "&aHmmm... My power level is becoming low...");
                break;
            case 5:
                Chat.sendMessage(user.getPlayer(), "&cOk, now I should really think about fixing my power..");
                break;
        }
    }
    
}