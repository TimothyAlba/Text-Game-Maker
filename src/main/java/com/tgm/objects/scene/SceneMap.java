package com.tgm.objects.scene;

import com.tgm.parser.objects.scene.SceneHeaderTags;

import java.util.HashMap;
import java.util.Map;

public class SceneMap {

    private HashMap<String, Scene> scenes;
    private Scene startingScene = null;

    public SceneMap() {
        scenes = new HashMap<>();
    }

    public void addScene(Scene scene) throws MultipleStartingScenesException {
        if (scene.getTag() == SceneHeaderTags.START) {
            if (hasStartingScene()) {
                throw new MultipleStartingScenesException(this, scene);
            }
            startingScene = scene;
        } else {
            scenes.put(scene.getName(),scene);
        }
    }

    public Scene getScene(String sceneName) {
        return scenes.get(sceneName);
    }

    public Scene getStartingScene() {
        return startingScene;
    }

    public boolean hasStartingScene() {
        return startingScene != null;
    }

    public String toString() {
        String sceneStrings = "===========Starting scene=============\n"+startingScene.toString()+"\n==============================\n\n";
        for (Map.Entry<String,Scene> pair : scenes.entrySet()) {
            sceneStrings += pair.getValue().toString() + "\n\n";
        }
        return "Number of scenes: " + (scenes.size()+1) +
                "\n" + sceneStrings;
    }
}
