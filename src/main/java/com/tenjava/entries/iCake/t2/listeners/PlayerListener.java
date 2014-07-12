package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.GameState;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;
import com.tenjava.entries.iCake.t2.power.PowerFoods;
import com.tenjava.entries.iCake.t2.timers.BorderTask;
import com.tenjava.entries.iCake.t2.utils.Chat;
import com.tenjava.entries.iCake.t2.utils.Utils;

public class PlayerListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        
        if(e.getResult() == Result.ALLOWED && GameState.getCurrentState() != GameState.WAITING && !player.isOp()) {
           e.disallow(Result.KICK_OTHER, Chat.color("&cGame currently in progress, join back later!"));
        }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if(GameState.getCurrentState() == GameState.WAITING) {
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }
        
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(player.getMaxHealth());
        player.setAllowFlight(false);

        for(PotionEffect pot : player.getActivePotionEffects()) {
            player.removePotionEffect(pot.getType());
        }

        TenJava.getBoard().delScoreboard(player);
        UserManager.getUser(player);

        e.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        
        TenJava.getBoard().delScoreboard(player);
        UserManager.delUser(player);
        
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player player = e.getPlayer();
        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        
        TenJava.getBoard().delScoreboard(player);
        UserManager.delUser(player);

        e.setLeaveMessage(null);
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

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        User user = UserManager.getUser(player);
        int power = PowerFoods.getAmount(player.getItemInHand());
        
        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getWorld().getName().equalsIgnoreCase(WorldUtils.WORLD_NAME) && GameState.getCurrentState() == GameState.STARTED && power > 0 && user.getPower() != UserManager.MAX_POWER) {
            user.setPower(user.getPower() + power);
            
            Chat.sendMessage(player, "&a+" + power + " power");
            
            if(player.getItemInHand().getAmount() - 1 > 0) {
                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
            } else {
                player.setItemInHand(null);
            }
            
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        User user = UserManager.getUser(player);
        
        if(player.getWorld().getName().equalsIgnoreCase(WorldUtils.WORLD_NAME)) {
            player.setHealth(player.getMaxHealth());
            player.setNoDamageTicks(20 * 3);
            player.setFallDistance(-3f);
            
            user.setPower(UserManager.MAX_POWER);

            for(ItemStack item : e.getDrops()) {
                Item droppedItem = (Item)player.getWorld().dropItemNaturally(player.getLocation(), item);
                droppedItem.setPickupDelay(20);
            }
            
            Location loc = new Location(player.getWorld(), Utils.getRandom().nextBoolean() ? -Utils.getCentral(250, 450) : Utils.getCentral(250, 450), 0, Utils.getRandom().nextBoolean() ? -Utils.getCentral(250, 450) : Utils.getCentral(250, 450));
            loc.setY(player.getWorld().getHighestBlockYAt(loc) + 2);
            player.teleport(loc);
            
            e.getDrops().clear();
        }
    }
    
}
