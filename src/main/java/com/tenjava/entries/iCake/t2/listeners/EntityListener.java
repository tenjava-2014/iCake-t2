package com.tenjava.entries.iCake.t2.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import com.tenjava.entries.iCake.t2.User;
import com.tenjava.entries.iCake.t2.game.GameState;
import com.tenjava.entries.iCake.t2.game.WorldUtils;
import com.tenjava.entries.iCake.t2.managers.UserManager;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity().getWorld().getName().equalsIgnoreCase(WorldUtils.WORLD_NAME)) {
            if(e.getEntity() instanceof Player) {
                Player player = (Player)e.getEntity();
                User user = UserManager.getUser(player);

                if(GameState.getCurrentState() != GameState.STARTED) {
                    e.setCancelled(true);
                } else {
                    user.setPower(user.getPower() - 2);
                }
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityBlockForm(EntityChangeBlockEvent e) {
        Entity entity = e.getEntity();
        Block block = e.getBlock();

        if(entity.hasMetadata("core")) {
            e.setCancelled(true);
            block.setType(block.getRelative(BlockFace.DOWN).getType() == Material.WOOL ? Material.AIR : Material.LAVA);
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }

}
