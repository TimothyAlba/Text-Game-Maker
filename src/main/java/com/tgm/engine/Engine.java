package com.tgm.engine;

import com.tgm.AppRuntime;
import com.tgm.Game;

public class Engine {

    public static int playGame(Game game) {
        System.out.println("Playing the game!");
        return AppRuntime.QUIT_GAME;
    }
}
