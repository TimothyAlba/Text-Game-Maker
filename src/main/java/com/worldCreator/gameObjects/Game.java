package com.worldCreator.gameObjects;

import java.util.HashMap;

public class Game {

    private HashMap<String,Chapter> gameStory;

    protected Game() {}

    public Game(HashMap<String,Chapter> gameStory) {
        this.gameStory = gameStory;
    }

    public HashMap<String,Chapter> getGameStory() {
        return gameStory;
    }
}
