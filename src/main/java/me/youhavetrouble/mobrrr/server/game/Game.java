package me.youhavetrouble.mobrrr.server.game;

public abstract class Game {

    private GameState state = GameState.WAITING_FOR_PLAYERS;

    public Game() {

    }

    public GameState getState() {
        return state;
    }

    public enum GameState {
        WAITING_FOR_PLAYERS,
        IN_PROGRESS,
        ENDED
    }

}
