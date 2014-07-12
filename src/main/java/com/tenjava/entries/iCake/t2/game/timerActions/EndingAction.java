package com.tenjava.entries.iCake.t2.game.timerActions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.tenjava.entries.iCake.t2.game.WorldUtils;

public class EndingAction implements TimerAction {

    public void doAction() {
        for(Player player : WorldUtils.getCoreWorld().getPlayers()) {
             player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
             
             player.getInventory().clear();
             player.getInventory().setArmorContents(null);
        }
        
        WorldUtils.removeWorld();
    }

}
