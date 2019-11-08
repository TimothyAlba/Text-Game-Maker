package com.tgm.engine;

import com.tgm.objects.Game;
import com.tgm.display.Display;
import com.tgm.objects.scene.Scene;
import com.tgm.parser.objects.scene.SceneHeaderTags;

public class Engine {

    private Display display;

    public Engine(Display display) {
        this.display = display;
    }

    public int playGame(Game game) throws InterruptedException {
        Scene scene = game.getStart();
        do {
            int optionSelected = display.displayScene(scene);
            if (scene.isEnd())
                break;
            String nextSceneName = scene.getSceneForOption(optionSelected);
            scene = game.getScene(nextSceneName);
        } while (true);
        return 1;
    }
}
