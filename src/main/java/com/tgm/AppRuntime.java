package com.tgm;

import com.tgm.display.Display;
import com.tgm.display.ConsoleDisplay;
import com.tgm.engine.Engine;
import com.tgm.objects.Game;
import com.tgm.objects.scene.MultipleStartingScenesException;
import com.tgm.objects.scene.NoStartingSceneException;
import com.tgm.parser.BadScriptTagException;
import com.tgm.parser.TextParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppRuntime {

    public static void main(String[] args) throws InterruptedException, MultipleStartingScenesException, BadScriptTagException, NoStartingSceneException, IOException {
        List<String> mainMenuOptions = generateMenuOptions();
        Display consoleDisplay = new ConsoleDisplay(mainMenuOptions);
        int option;
        Game game = null;
        do {
            option = consoleDisplay.displayMainMenu();
            switch (option) {
                case 0: {
                    if (game != null) {
                        Engine gameEngine = new Engine(consoleDisplay);
                        System.out.println("Playing game!");
                        gameEngine.playGame(game);
                        System.out.println("You played the game!");
                    } else {
                        System.out.println("First you need to build the game! Select Option 2 next time first.");
                    }
                    break;
                }
                case 1: {
                    System.out.println("Parsing story");
                    game = TextParser.parse("D:\\Tim\\Documents\\Programming Practice\\Text-Game-Maker\\src\\main\\resources\\drew-story.txt");
//                    System.out.println(game);
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