package com.tgm.objects;

import com.tgm.Utilities;

import java.util.HashMap;
import java.util.Map;

public class Game {

    Map<String, Scene> sceneList;

    public Game() {
        sceneList = new HashMap<>();
    }

    public void addScene(Scene scene) {
        sceneList.put(scene.getName(), scene);
    }
    public Scene getScene(String sceneName) {
        return sceneList.get(sceneName);
    }

    public String toString() {
        String sceneStrings = "";
        for (Map.Entry<String,Scene> pair : sceneList.entrySet()) {
            sceneStrings += pair.getValue().toString() + "\n\n";
        }
        return "Number of scenes: " + sceneList.size() +
                "\n" + sceneStrings;
    }
}
