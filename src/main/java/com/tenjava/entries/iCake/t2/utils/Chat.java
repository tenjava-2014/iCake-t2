package com.tenjava.entries.iCake.t2.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Chat {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendMessage(CommandSender sender, String msg) {
        sender.sendMessage(color("&7" + msg));
    }

    public static void broadcast(String msg) {
        Bukkit.broadcastMessage(color(msg));
    }

}
