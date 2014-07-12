package com.tenjava.entries.iCake.t2.game.timerActions;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;

public class StartingAction implements TimerAction {

    public void doAction() {
        for(Player player : WorldUtils.getCoreWorld().getPlayers()) {
            player.setVelocity(new Vector(0, .3, 0));
            player.setAllowFlight(true);
            player.setFlying(true);

            User user = UserManager.getUser(player);

            if(user.getBorderTask() != null) {
                user.getBorderTask().cancel();
                user.setBorderTask(null);
            }

            if(user.getNoPowerTask() != null) {
                user.getNoPowerTask().cancel();
                user.setNoPowerTask(null);
            }

            if(user.getPowerTask() != null) {
                user.getPowerTask().cancel();
                user.setPowerTask(null);
            }
        }
    }

}
