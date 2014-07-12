package com.tenjava.entries.iCake.t2.utils;

import java.util.Random;

public class Utils {

    private static Random rand = new Random();

    public static Random getRandom() {
        rand.setSeed(System.nanoTime());
        return rand;
    }
    
    public static int getCentral(int low, int high) {
        return getRandom().nextInt(high - low) + low;
    }

}
