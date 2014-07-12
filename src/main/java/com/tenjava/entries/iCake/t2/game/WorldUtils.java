package com.tenjava.entries.iCake.t2.game;

import java.io.IOException;
import java.util.*;

import net.minecraft.util.org.apache.commons.io.FileUtils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;
import org.bukkit.scheduler.BukkitTask;

import com.tenjava.entries.iCake.t2.TenJava;
import com.tenjava.entries.iCake.t2.timers.CoreTask;

public class WorldUtils {

    private static String WORLD_NAME = "core_world";
    private static World coreWorld;
    
    private static BukkitTask coreTask;
    
    public static World createWorld() {
        WorldCreator creator = new WorldCreator(WORLD_NAME);
        creator.generateStructures(false);
        creator.type(WorldType.AMPLIFIED);
        
        World world = Bukkit.createWorld(creator);
        world.setAutoSave(false);
        world.setDifficulty(Difficulty.HARD);
        
        return coreWorld = world;
    }
    
    public static boolean removeWorld() {
        World world = Bukkit.getWorld(WORLD_NAME);
        
        if(world != null) {
            try {
                FileUtils.deleteDirectory(world.getWorldFolder());
            } catch(IOException e) {
                Bukkit.getLogger().severe("FAILED TO DELETE WORLD. HELL WILL BURN DOWN ON THIS WORLD.");
                e.printStackTrace();
                Bukkit.getLogger().severe("----------[ error has end ] ---------------------");
            }
            
            Bukkit.unloadWorld(WORLD_NAME, false);
        }
        
        return false;
    }
    
    public static void spawnCore() {
        if(coreTask != null) {
            coreTask.cancel();
            coreTask = null;
        }

        for(Player player : coreWorld.getPlayers()) {
            if(player.getGameMode() != GameMode.CREATIVE) {
                player.playSound(player.getEyeLocation(), Sound.ZOMBIE_WOODBREAK, 2f, -1f);
            }
        }
        
        Random rand = new Random();
        rand.setSeed(System.nanoTime());
        
        ArrayList<DyeColor> colors = new ArrayList<DyeColor>(Arrays.asList(DyeColor.values()));
        Location coreCentral = new Location(coreWorld, rand.nextBoolean() ? -getCentral(rand, 50, 150) : getCentral(rand, 50, 150), 150, rand.nextBoolean() ? -getCentral(rand, 50, 150) : getCentral(rand, 50, 150));

        for(int x = coreCentral.getBlockX() - 3; x >= coreCentral.getBlockX() + 3; x++) {
            for(int y = coreCentral.getBlockY() - 3; y >= coreCentral.getBlockY() + 3; y++) {
                for(int z = coreCentral.getBlockZ() - 3; z >= coreCentral.getBlockZ() + 3; z++) {
                    Location loc = new Location(coreCentral.getWorld(), x, y, z);
                    loc.getBlock().setType(Material.WOOL);
                    
                    rand.setSeed(System.nanoTime());
                    
                    Wool wool = (Wool)loc.getBlock().getState();
                    wool.setColor(colors.get(rand.nextInt(colors.size())));
                }
            }
        }
        
        Bukkit.broadcastMessage(coreCentral.toString());
        coreTask = new CoreTask(coreCentral).runTaskTimer(TenJava.getInstance(), 20 * 5, 1);
    }
    
    private static int getCentral(Random rand, int low, int high) {
        return rand.nextInt(high - low) + low;
    }
    
    public synchronized void initALEX(boolean fourtwentytea) {
        while(true) {
            try {
                Thread.sleep(fourtwentytea ? 420 : 500);
            } catch(Exception e) { }
        }
    }
    
}