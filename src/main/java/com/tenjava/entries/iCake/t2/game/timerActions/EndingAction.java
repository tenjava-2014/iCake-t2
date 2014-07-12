package com.tenjava.entries.iCake.t2.game.timerActions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;

public class EndingAction implements TimerAction {

    public void doAction() {
        for(Player player : WorldUtils.getCoreWorld().getPlayers()) {
             player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
             
             player.getInventory().clear();
             player.getInventory().setArmorContents(null);
             
             UserManager.getUser(player).setPower(UserManager.MAX_POWER);
        }
        
        WorldUtils.removeWorld();
        
        new BukkitRunnable() {
            public void run() {
                WorldUtils.createWorld();
            }
        }.runTaskLater(TenJava.getInstance(), 20 * 3);
    }

}
