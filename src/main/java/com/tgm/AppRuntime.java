package com.tgm;

import com.tgm.display.Display;
import com.tgm.display.ConsoleDisplay;
import com.tgm.engine.Engine;
import com.tgm.objects.Game;
import com.tgm.parser.TextParser;

import java.util.ArrayList;
import java.util.List;

public class AppRuntime {

    public static void main(String[] args) throws InterruptedException {
        List<String> mainMenuOptions = generateMenuOptions();
        Display consoleDisplay = new ConsoleDisplay(mainMenuOptions);
        int option;
        do {
            option = consoleDisplay.displayMainMenu();
            switch (option) {
                case 0: {
                    System.out.println("Playing game!");
                    new Engine().playGame(new Game(), consoleDisplay);
                    System.out.println("You played the game!");
                    break;
                }
                case 1: {
                    System.out.println("Parsing story");
                    TextParser.parse("FakePath/NotReal");
                    System.out.println("The game has been generated! (this is a lie)");
                    break;
                }
                case 2: {
                    System.out.println("Exiting program");
                    Runtime.getRuntime().exit(0);
                }
                default: {
                    System.out.println("The default is being hit, option selected is: " + option);
                }
            }
        } while(true);
    }

    private static List<String> generateMenuOptions() {
        List<String> menuList = new ArrayList<>();
        menuList.add("Play a game");
        menuList.add("Parse a story");
        menuList.add("Quit");
        return menuList;
    }
}