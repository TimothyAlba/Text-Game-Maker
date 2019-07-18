package com.tgm.display.console;

import com.tgm.Scene;
import com.tgm.display.Display;

public class ConsoleDisplay implements Display {

    public int displayScene(Scene scene) {
        System.out.println(scene);
        return 1;
    }

    public int displayMainMenu() {
        System.out.println("Select a main menu option")
    }
}
