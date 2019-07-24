package com.tgm.parser;

import com.tgm.objects.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextParser {
    public static Game parse(String filePath) {
        Game game = new Game();

        for(Scene s : ParseSceneInfo(readFile(filePath))){
            game.addScene(s);
        }

        return game;
    }

    private static List<Scene> ParseSceneInfo(String gameText) {
        List<Scene> result = new ArrayList<>();
        Scene scene = null;// = new Scene();
        String action;
        String data;

        for(String line : FileDataToList(gameText)){
            data = "";
            if (line.indexOf(":") > -1) {
                action = line.substring(0, line.indexOf(":")).trim();
                data = line.substring(line.indexOf(":") + 1).trim();
            }
            else {
                action = line.trim();
            }

            switch (action){
                case "scene":
                    //if (scene.IsValid()) {
                    //    result.add(scene);
                    //}
                    scene = new Scene();
                    scene.setName(data);
                    result.add(scene);
                    break;
                case "script":
                    scene.setText(data);
                    break;
                case "start":
                    break;
                case "end":
                    scene.setEnd(true);
                    break;
                case "option":
                    String[] options = data.split("=");
                    scene.addOption(options[0].trim(), options[1].trim());
                    break;
            }
        }

        return result;
    }

    private static List<String> FileDataToList(String gameText) {
        List<String> result = new ArrayList<>();
        StringBuilder current_line = new StringBuilder();
        int char_index = 0;
        Character curr_char;
        Character next_char;
        Character quote_char = 0;
        String mode = "normal";
        String data;
        boolean isEscaped = false;

        data = gameText.replace(System.lineSeparator(), "\\n");
        current_line.setLength(0);

        while(char_index < data.length()){
            curr_char = data.charAt(char_index);
            next_char = 0;
            if (char_index + 1 < data.length()){
                next_char = data.charAt(char_index + 1);
            }

            switch(mode) {
                case "normal":
                    if (curr_char.equals('\\')){
                        if (!current_line.toString().isEmpty())
                            result.add(current_line.toString());
                        current_line.setLength(0);
                        char_index += 1;
                    } else if (curr_char.equals('/')){
                        if (next_char.equals('/')){
                            mode = "line_comment";
                        }
                        if (next_char.equals('*')){
                            mode = "comment_block";
                        }
                    } else if (curr_char.equals('\'') || curr_char.equals('"')){
                        mode = "quote";
                        quote_char = curr_char;
                        current_line.append(curr_char);
                    }
                    else {
                        current_line.append(curr_char);
                    }
                    break;

                case "line_comment":
                    if (curr_char.equals('\\') && next_char.equals('n')){
                        mode = "normal";
                        char_index += 1;
                    }
                    break;

                case "comment_block":
                    if (curr_char.equals('*') && next_char.equals('/')){
                        mode = "normal";
                        char_index += 1;
                    }
                    break;

                case "quote":
                    if (!isEscaped && curr_char.equals(quote_char)){
                        quote_char = 0;
                        mode = "normal";
                    }
                    isEscaped = curr_char.equals('\\');
                    current_line.append(curr_char);
                    break;
            }

            char_index += 1;
        }

        if (!current_line.toString().isEmpty())
            result.add(current_line.toString());

        return result;
    }


    private static String readFile(String filePath) {
        StringBuilder result = new StringBuilder();
        try {
            InputStream inStream = TextParser.class.getResourceAsStream(filePath);
            Scanner reader = new Scanner(inStream);
            String line;
            while (reader.hasNext()) {
                line = reader.nextLine();
                result.append(line);
                result.append(System.lineSeparator());
            }
        }
        catch(Exception ex){
            System.out.println("THE FILE DOESN'T EXIST!");
        }
        return result.toString();
    }

}
