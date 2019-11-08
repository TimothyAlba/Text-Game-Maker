package com.tgm.objects.scene;

public class NoStartingSceneException extends Exception {

    public NoStartingSceneException() {
       super("No starting scene is set in the script!");
    }
}
