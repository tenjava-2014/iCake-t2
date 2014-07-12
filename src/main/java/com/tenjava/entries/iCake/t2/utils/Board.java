package com.tenjava.entries.iCake.t2.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import com.tenjava.entries.iCake.t2.game.WorldUtils;

public class Board {

    private Scoreboard board;
    private Objective obj;

    private Score players, core;

    public void setScoreboard(Player player, boolean update) {
        if(board == null || update) {
            board = Bukkit.getScoreboardManager().getNewScoreboard();

            obj = board.registerNewObjective("data", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(Chat.color("&6Core"));

            updatePlayers();
            updateCores();
        }

        player.setScoreboard(board);
    }

    public void delScoreboard(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void updatePlayers() {
        if(players == null) {
            players = obj.getScore(Chat.color("Players"));
        }

        players.setScore(WorldUtils.getCoreWorld().getPlayers().size());
    }

    public void updateCores() {
        if(core == null) {
            core = obj.getScore(Chat.color("Cores Left"));
        }

        core.setScore(WorldUtils.getCoreBlocks().size());
    }

}