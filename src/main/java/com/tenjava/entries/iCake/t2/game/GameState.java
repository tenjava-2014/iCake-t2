package com.tenjava.entries.iCake.t2.game;

import com.tenjava.entries.iCake.t2.game.timerActions.*;

public enum GameState {

    WAITING(10, "&eGame starting in &6%s", "&6Game starting, good luck!", new WaitingAction()), // 60
    INVINCIBLE(10, "&cInvincibility is over in &4%s", "&4Invincibility has worn off!", new InvincibleAction()), // 30
    STARTED(60, "&eGame ending in &6%s", "&6Game ending!", new StartingAction()), // 600
    ENDING(10, "&eSwitching worlds in &6%s", "&6Switching worlds...", new EndingAction()); // 30

    private int timePerState;
    private String messageToBroadcast, completeMessage;
    private TimerAction action;

    private static GameState currentState;

    private GameState(int timePerState, String messageToBroadcast, String completeMessage, TimerAction action) {
        this.timePerState = timePerState;
        this.messageToBroadcast = messageToBroadcast;
        this.completeMessage = completeMessage;
        this.action = action;
    }

    public int getTimeForState() {
        return timePerState;
    }

    public String getRawMessageToBroadcast() {
        return messageToBroadcast;
    }

    public String getMessageToBroadcast(int curTime) {
        if(curTime % 60 == 0) {
            return String.format(getRawMessageToBroadcast(), curTime % 60 + (curTime % 60 == 1 ? " minute" : " minutes"));
        }

        return String.format(getRawMessageToBroadcast(), curTime + (curTime == 1 ? " second" : " seconds"));
    }

    public String getCompleteMessage() {
        return completeMessage;
    }

    public static GameState getCurrentState() {
        if(currentState == null) {
            setCurrentState(WAITING);
        }

        return currentState;
    }

    public static void setCurrentState(GameState state) {
        currentState = state;
    }

    public TimerAction getAction() {
        return action;
    }

    public void doAction() {
        getAction().doAction();

        switch(getCurrentState()) {
            case WAITING:
                setCurrentState(INVINCIBLE);
                break;
            case INVINCIBLE:
                setCurrentState(STARTED);
                break;
            case STARTED:
                setCurrentState(ENDING);
                break;
            case ENDING:
                setCurrentState(null);
                break;
        }
    }

}
