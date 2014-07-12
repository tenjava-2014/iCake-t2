package com.tenjava.entries.iCake.t2.game.timerActions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;
import com.tenjava.entries.iCake.t2.timers.PowerTask;

public class InvincibleAction implements TimerAction {

    public void doAction() {
        for(Player player : WorldUtils.getCoreWorld().getPlayers()) {
            User user = UserManager.getUser(player);
            
            player.getInventory().addItem(new ItemStack(Material.COOKED_FISH, 2));
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);
            
            user.setPower(20);
            user.setPowerTask(new PowerTask(player).runTaskTimer(TenJava.getInstance(), 20 * 5, 3));
        }
        
        WorldUtils.spawnCore();
    }

}
