package com.tenjava.entries.iCake.t2.timers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.iCake.t2.game.GameState;
import com.tenjava.entries.iCake.t2.utils.Chat;

public class GameLoop extends BukkitRunnable {

    private static int time;

    public GameLoop() {
        time = GameState.getCurrentState().getTimeForState();
    }

    public void run() {
        if(time % 60 == 0 && time >= 60) {
            Bukkit.broadcastMessage(Chat.color(GameState.getCurrentState().getMessageToBroadcast(time)));
        } else if(time % 10 == 0 && time <= 30 && time >= 10) {
            Bukkit.broadcastMessage(Chat.color(GameState.getCurrentState().getMessageToBroadcast(time)));
        } else if(time <= 5 && time >= 1) {
            Bukkit.broadcastMessage(Chat.color(GameState.getCurrentState().getMessageToBroadcast(time)));
        } else if(time == 0) {
            Bukkit.broadcastMessage(Chat.color(GameState.getCurrentState().getCompleteMessage()));

            GameState.getCurrentState().doAction();
            time = GameState.getCurrentState().getTimeForState();
        }

        time--;
    }
    
    public static void setTime(int newtime) {
        time = newtime;
    }

}
