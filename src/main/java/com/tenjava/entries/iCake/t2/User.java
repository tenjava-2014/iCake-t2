package com.tenjava.entries.iCake.t2;

import org.bukkit.entity.Player;

public class User {

    private Player player;
    
    public User(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() { return player; } //no lombok :(
    
    public void cleanup() {
        player = null;
    }
    
}