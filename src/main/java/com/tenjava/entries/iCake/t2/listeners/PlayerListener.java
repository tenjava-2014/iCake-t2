package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;
import com.tenjava.entries.iCake.t2.timers.BorderTask;
import com.tenjava.entries.iCake.t2.utils.Chat;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(player.getMaxHealth());
        player.setAllowFlight(false);

        for(PotionEffect pot : player.getActivePotionEffects()) {
            player.removePotionEffect(pot.getType());
        }

        UserManager.getUser(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        UserManager.delUser(e.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        UserManager.delUser(e.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        User user = UserManager.getUser(player);

        if((e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) && player.getWorld().getName().equalsIgnoreCase(WorldUtils.WORLD_NAME)) {
            if(user.getBorderTask() == null && (Math.abs(e.getTo().getBlockX()) >= 500 || Math.abs(e.getTo().getBlockZ()) >= 500)) {
                user.setBorderTask(new BorderTask(player).runTaskTimer(TenJava.getInstance(), 10, 5));
            }
        }
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();

        if(e.getBed().getType() == Material.BED) { // just to be sure, to avoid any dodgy hack clients
            Chat.sendMessage(player, "&e&oYou feel your power slowly raise up...");
        }
    }

    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent e) {
        Player player = e.getPlayer();

        if(e.getBed().getType() == Material.BED) { // just to be sure, to avoid any dodgy hack clients
            UserManager.getUser(player).setPower(UserManager.MAX_POWER);
        }
    }

}
