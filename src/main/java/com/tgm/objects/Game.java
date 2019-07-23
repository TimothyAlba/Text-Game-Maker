package com.tgm.objects;

import java.util.Map;

public class Game {

    Map<String, Scene> sceneList;

    public Game() {
    }

    public Scene getScene(String sceneName) {
        return sceneList.get(sceneName);
    }
}
