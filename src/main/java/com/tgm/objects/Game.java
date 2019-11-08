package com.tgm.objects;

import com.tgm.objects.scene.NoStartingSceneException;
import com.tgm.objects.scene.Scene;
import com.tgm.objects.scene.SceneMap;

import java.util.Map;

public class Game {

    SceneMap sceneMap;

    private Game() {
        //Do not use
    }

    public Game(SceneMap sceneMap) throws NoStartingSceneException {
        if (!sceneMap.hasStartingScene())
            throw new NoStartingSceneException();
        this.sceneMap = sceneMap;
    }

    public Scene getScene(String sceneName) {
        return sceneMap.getScene(sceneName);
    }

    public Scene getStart() {
        return sceneMap.getStartingScene();
    }

    public String toString() {
        return sceneMap.toString();
    }
}
