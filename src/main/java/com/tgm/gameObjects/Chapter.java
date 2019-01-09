package com.tgm.gameObjects;

import java.util.HashMap;

public class Chapter {

    private HashMap<String,Scene> scenes;

    protected Chapter() {}

    public Chapter(HashMap<String,Scene> scenes) {
        this.scenes = scenes;
    }

    public Scene getScene(String sceneID) {
        return scenes.get(sceneID);
    }
}
