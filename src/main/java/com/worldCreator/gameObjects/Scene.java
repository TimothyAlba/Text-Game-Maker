package com.worldCreator.gameObjects;

import java.util.HashMap;

public class Scene {

    private String backgroundImg;
    private String backgroundMusic;
    private HashMap<String,Dialog> dialogs;

    protected Scene(){}

    public Scene(String backgroundImg, String backgroundMusic, HashMap<String,Dialog> dialogs) {
        this.backgroundImg = backgroundImg;
        this.backgroundMusic = backgroundImg;
        this.dialogs = dialogs;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public String getBackgroundMusic() {
        return backgroundMusic;
    }

    public Dialog getDialog(String dialogID) {
        return dialogs.get(dialogID);
    }
}
