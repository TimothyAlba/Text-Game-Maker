package com.tgm.display;

import com.tgm.objects.Game;
import com.tgm.objects.Scene;

public interface Display {

    int displayScene(Scene scene) throws InterruptedException;

    int displayMainMenu() throws InterruptedException;
}
