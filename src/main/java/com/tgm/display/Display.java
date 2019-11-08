package com.tgm.display;

import com.tgm.objects.scene.Scene;

public interface Display {

    int displayScene(Scene scene) throws InterruptedException;

    int displayMainMenu() throws InterruptedException;
}
