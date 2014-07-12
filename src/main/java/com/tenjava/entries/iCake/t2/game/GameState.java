package com.tenjava.entries.iCake.t2.game;

import com.tenjava.entries.iCake.t2.game.timerActions.*;

public enum GameState {

    WAITING(10, "Game starting in %s", "Game starting, good luck!", new WaitingAction()), //60
    INVINCIBLE(10, "Invincibility is over in %s", "Invincibility has worn off!", new InvincibleAction()), //30
    STARTED(60, "Game ending in %s", "Game ending!", new StartingAction()), //600
    ENDING(10, "Switching worlds in %s", "Switching worlds...", new EndingAction()); //30
    
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
            case WAITING: setCurrentState(INVINCIBLE); break;
            case INVINCIBLE: setCurrentState(STARTED); break;
            case STARTED: setCurrentState(ENDING); break;
            case ENDING: setCurrentState(null); break;
        }
    }
    
}