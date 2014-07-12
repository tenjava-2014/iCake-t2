package com.tenjava.entries.iCake.t2.timers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.utils.Utils;

public class CoreTask extends BukkitRunnable {

    private Location coreCentral;
    private int tick;
    
    public CoreTask(Location coreCentral) {
        this.coreCentral = coreCentral;
        
        coreCentral.setY(coreCentral.getWorld().getHighestBlockYAt(coreCentral) + 1);
        
        this.tick = -1;
    }
    
    public void run() {
        if(tick == -1 || Utils.getRandom().nextInt(100) <= 3) {
            int[] blockDataS = new int[] { 15, 14, 1, 4 };
            
            for(int loop = 0; loop < 3; loop++) {
                for(int i = 0; i < blockDataS.length; i++) {
                    @SuppressWarnings("deprecation") FallingBlock block = coreCentral.getWorld().spawnFallingBlock(coreCentral, Material.WOOL, (byte)blockDataS[i]);
                    block.setVelocity(Vector.getRandom().multiply(Utils.getRandom().nextBoolean() ? -2 : 2));
                    block.setMetadata("core", new FixedMetadataValue(TenJava.getInstance(), true));
                    block.setDropItem(false);
                }
            }
        }
        
        tick++;
    }

}
