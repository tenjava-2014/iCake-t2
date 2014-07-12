package com.tenjava.entries.iCake.t2.game;

import com.tenjava.entries.iCake.t2.game.timerActions.*;

public enum GameState {

    WAITING(30, "&eGame starting in &6%s", "&6Game starting, good luck!", new WaitingAction()), // 60
    INVINCIBLE(30, "&cInvincibility is over in &4%s", "&4Invincibility has worn off!", new InvincibleAction()), // 30
    STARTED(600, "&eGame ending in &6%s", "&6Game ending!", new StartingAction()), // 600
    ENDING(15, "&eSwitching worlds in &6%s", "&6Switching worlds...", new EndingAction()); // 30

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

    /**
     * Get the time that remains for the state of the game
     * @return the time for each state
     */
    public int getTimeForState() {
        return timePerState;
    }

    /**
     * Get raw message to broadcast, with no formatting
     * @return raw message
     */
    public String getRawMessageToBroadcast() {
        return messageToBroadcast;
    }

    /**
     * Gets the message to broadcast, with the time included
     * @param curTime the currrent time in GameLoop
     * @return formatted message
     */
    public String getMessageToBroadcast(int curTime) {
        if(curTime % 60 == 0) {
            return String.format(getRawMessageToBroadcast(), curTime / 60 + (curTime / 60 == 1 ? " minute" : " minutes"));
        }

        return String.format(getRawMessageToBroadcast(), curTime + (curTime == 1 ? " second" : " seconds"));
    }

    /**
     * @return the message when timer ends
     */
    public String getCompleteMessage() {
        return completeMessage;
    }

    /**
     * @return the current game state
     */
    public static GameState getCurrentState() {
        if(currentState == null) {
            setCurrentState(WAITING);
        }

        return currentState;
    }

    /**
     * Set the current state
     * @param state
     */
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
