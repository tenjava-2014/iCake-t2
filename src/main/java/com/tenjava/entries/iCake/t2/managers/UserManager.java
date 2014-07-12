package com.tenjava.entries.iCake.t2.managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.tenjava.entries.iCake.t2.User;

public class UserManager {

    private static HashMap<String, User> users = new HashMap<String, User>();

    public static int MAX_POWER = 30;

    public static User getUser(Player player) {
        return users.containsKey(player.getUniqueId().toString()) ? users.get(player.getUniqueId().toString()) : users.put(player.getUniqueId().toString(), new User(player));
    }

    public static void delUser(Player player) {
        if(users.containsKey(player.getUniqueId().toString())) {
            users.get(player.getUniqueId().toString()).cleanup();
            users.remove(player.getUniqueId().toString());
        }
    }

}
