package com.tenjava.entries.iCake.t2.power;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

@SuppressWarnings("deprecation")
public class PowerFoods {
    
    private static HashMap<MaterialData, Integer> foods = new HashMap<>();
    
    public static int getAmount(ItemStack item) {
        if(!foods.containsKey(item.getData())) {
            return 0;
        }
        
        return foods.get(item.getData());
    }
    
    static {
        foods.put(new MaterialData(Material.APPLE), 2);
        foods.put(new MaterialData(Material.MUSHROOM_SOUP), 4);
        foods.put(new MaterialData(Material.BREAD), 3);
        foods.put(new MaterialData(Material.PORK), 2);
        foods.put(new MaterialData(Material.GRILLED_PORK), 4);
        foods.put(new MaterialData(Material.RAW_FISH), 3);
        foods.put(new MaterialData(Material.RAW_FISH, (byte)1), 5);
        foods.put(new MaterialData(Material.RAW_FISH, (byte)2), 5);
        foods.put(new MaterialData(Material.RAW_FISH, (byte)3), 5);
        foods.put(new MaterialData(Material.COOKED_FISH), 5);
        foods.put(new MaterialData(Material.COOKED_FISH, (byte)1), 5);
        foods.put(new MaterialData(Material.COOKIE), 1);
        foods.put(new MaterialData(Material.MELON), 1);
        foods.put(new MaterialData(Material.RAW_BEEF), 2);
        foods.put(new MaterialData(Material.COOKED_BEEF), 4);
        foods.put(new MaterialData(Material.RAW_CHICKEN), 2);
        foods.put(new MaterialData(Material.COOKED_CHICKEN), 4);
        foods.put(new MaterialData(Material.ROTTEN_FLESH), 2);
        foods.put(new MaterialData(Material.CARROT_ITEM), 2);
        foods.put(new MaterialData(Material.POTATO_ITEM), 2);
        foods.put(new MaterialData(Material.POISONOUS_POTATO), 2);
        foods.put(new MaterialData(Material.PUMPKIN_PIE), 4);
    }
    
}