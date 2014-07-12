package com.tenjava.entries.iCake.t2.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.game.GameState;
import com.tenjava.entries.iCake.t2.utils.Utils;

public class EntityListener implements Listener {

    private ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(Color.AQUA, Color.FUCHSIA, Color.LIME, Color.ORANGE, Color.WHITE, Color.TEAL));
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player && GameState.getCurrentState() == GameState.INVINCIBLE) {
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
        } else if(entity.hasMetadata("core-tnt")) {
            e.setCancelled(true);
         
            TNTPrimed tnt = block.getWorld().spawn(block.getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(5 + Utils.getRandom().nextInt(10));
        }
    }
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        for(final Block block : e.blockList()) {
            final Material beforeMat = block.getType();
            final byte beforeData = block.getData();
            
            Firework firework = block.getWorld().spawn(block.getLocation(), Firework.class);
            FireworkMeta meta = firework.getFireworkMeta();
            meta.addEffect(FireworkEffect.builder().withColor(colors.get(Utils.getRandom().nextInt(colors.size()))).trail(false).build());
            firework.detonate();
            
            block.setType(Material.AIR);
            
            new BukkitRunnable() {
                public void run() {
                    block.setTypeIdAndData(beforeMat.getId(), beforeData, false);
                }
            }.runTaskLater(TenJava.getInstance(), 15);
        }
        
        e.setCancelled(true);
    }
    
}
