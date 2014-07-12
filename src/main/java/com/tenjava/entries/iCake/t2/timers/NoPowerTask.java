package com.tenjava.entries.iCake.t2.timers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.GameState;
import com.tenjava.entries.iCake.t2.managers.UserManager;

public class NoPowerTask extends BukkitRunnable {

    private Player player;
    
    public NoPowerTask(Player player) {
        this.player = player;
    }

    public void run() {
        User user = UserManager.getUser(player);
        
        if(GameState.getCurrentState() == GameState.STARTED) {
            if(user.getPower() <= 0) {
                player.damage(1.0);
                
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 1));
            } else {
               this.cancel();
               user.setNoPowerTask(null);
            }
        } else {
            this.cancel();
            user.setNoPowerTask(null);
        }
    }
    
}