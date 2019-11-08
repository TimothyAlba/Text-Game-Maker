package com.tgm.display;

import com.tgm.objects.scene.Scene;
import static com.tgm.Utilities.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleDisplay implements Display {

    private Scanner input;
    private List<String> mainMenuOptions;

    private ConsoleDisplay() {

    }

    public ConsoleDisplay(List<String> mainMenuOptions) {
        this.mainMenuOptions = mainMenuOptions;
    }

    private final String OPTION_NUMBER_REGEX = "[1-9]+[0-9]*";

    /**
     * Takes a Scene object, displays the scene name, text, and options, validates user input, and returns an integer
     * representing the index of the chosen option.
     * @param scene The scene to display
     * @return An integer representing the index of the option picked
     */
    public int displayScene(Scene scene) throws InterruptedException {
        return getUserInput(scene.getText(), mapPairValues(scene.getOptions()));
    }

    public int displayMainMenu() throws InterruptedException {
        String displayMessage = "Please select a main menu option";
        return getUserInput(displayMessage,mainMenuOptions);
    }

    private int getUserInput(String outputText, List<String> options) throws InterruptedException {
        String option;
        String optionString = listToString(options);
        do {
            resetInput();
            System.out.print(outputText +
                    "\n\n" + optionString +
                    "\n-------------------------------\nOption: ");
            option = input.next();
            String optionError = isPickedOptionValid(option, options.size());
            if (optionError != null) {
                System.out.println("\n\n!!!!\t" + optionError);
                Thread.sleep(4000);
                continue;
            }
            return Integer.parseInt(option) - 1;
        } while (true);
    }

    private String listToString(List<String> options) {
        String optionsString = "";
        int optionCounter = 1;
        for (String option : options) {
            optionsString += optionCounter + "  --  " + option + "\n";
            ++optionCounter;
        }
        return optionsString;
    }

    private String isPickedOptionValid(String pickedOption, int numberOfOptions) {
        if (pickedOption.matches(OPTION_NUMBER_REGEX)) {
            int optionNumber = Integer.parseInt(pickedOption);
            if (optionNumber <= numberOfOptions) {
                return null;
            } else {
                return "Oh no! You picked a number higher than the highest option! Try again.";
            }
        } else {
            return "Hmmm, your option isn't an accepted number, try again!";
        }
    }

    private void resetInput() {
        if (input == null)
            input = new Scanner(System.in);
        else
            input.reset();
    }
}
