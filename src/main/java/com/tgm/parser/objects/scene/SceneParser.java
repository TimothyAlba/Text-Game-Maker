package com.tgm.parser.objects.scene;

import com.tgm.Utilities;
import com.tgm.objects.scene.MultipleStartingScenesException;
import com.tgm.objects.scene.Scene;
import com.tgm.objects.scene.SceneMap;
import com.tgm.parser.ScriptTag;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SceneParser {

    private static SceneObjectTags parsingTag = null;
    private static String optionName;
    private static StringBuilder sceneTextBuilder = null;
    private static int sceneNewLineBuffer = 0;


    //Temp
    private static String sceneName;
    private static SceneHeaderTags sceneHeaderTag;
    private static String sceneText;
    private static List<Pair<String,String>> sceneOptions;

    public static Scene buildScene() {
        buildParsingTagText();
        Scene scene = new Scene(sceneName,sceneText,sceneOptions,sceneHeaderTag);
        reset();
        return scene;
    }

    private static void reset() {
        parsingTag = null;
        optionName = "";
        sceneTextBuilder =null;
        sceneNewLineBuffer = 0;
        sceneName = "";
        sceneHeaderTag = null;
        sceneText = "";
        sceneOptions = null;
    }

    public static String buildScene(BufferedReader reader, SceneMap scenes, String tempString) throws IOException, MultipleStartingScenesException {
        //First line given to this method should always be the scene tag
        parseName(tempString);
        sceneOptions = new ArrayList<>();
        do {
            tempString = reader.readLine();
            if (tempString == null)
                break;
            tempString = parseInputString(tempString);
        } while (tempString == null);
        scenes.addScene(buildScene());
        return tempString;
    }

    /**
     * This method takes in an input String and parses it based on SceneObjectTags. If the line tag doesn't match a SceneTag
     * and isn't a plain text line then this will return the String indicating it didn't consume the String. If
     * parseInputString accepts the input it will return null indicating it consumed the String.
     * @param input Input String to add to the Scene object
     * @return Null if the input String is consumed or the input String if the String isn't consumed
     */
    private static String parseInputString(String input) {
        //Check if input is a tag
        if (input.matches(ScriptTag.getGenericScriptTagRegex())) {
            SceneObjectTags sceneTag;
            //Check if input is scene object tag
            if ((sceneTag = SceneObjectTags.getSceneObjectTag(input)) != null) {
                //Build and store string for previous parsingTag if exists
                if (parsingTag != null) {
                    buildParsingTagText();
                }
                sceneTextBuilder = new StringBuilder();
                parsingTag = sceneTag;
                switch (parsingTag) {
                    case OPTION_TAG: {
                        parseOption(input);
                        break;
                    }
                    case SCRIPT_TAG: {
                        parseScript(input);
                        break;
                    }
                }
            }
            //Else return input to indicate all scene options built
            else {
                return input;
            }
        }
        //Process new line for parsingTag type
        else {
            appendLine(input);
        }
        return null;
    }

    private static void parseName(String rawName) {
        String strippedName = ScriptTag.stripTagFromText(rawName);
        if (strippedName.contains("=")) {
            sceneName = Utilities.trimWhiteSpace(strippedName.substring(0,strippedName.indexOf('=')));
            sceneHeaderTag = SceneHeaderTags.getTagByPhrase(strippedName.substring(strippedName.indexOf('=')+1));
        } else {
            sceneName = Utilities.trimWhiteSpace(strippedName);
        }
    }

    private static void parseOption(String rawOption) {
        String strippedOption = ScriptTag.stripTagFromText(rawOption);
        optionName = Utilities.trimWhiteSpace(strippedOption.substring(0,strippedOption.indexOf('=')));
        sceneTextBuilder.append(Utilities.trimWhiteSpace(strippedOption.substring(strippedOption.indexOf('=')+1)));
    }

    private static void parseScript(String rawScript) {
        String strippedScript = ScriptTag.stripTagFromText(rawScript);
        sceneTextBuilder.append(Utilities.trimWhiteSpace(strippedScript));
    }

    private static void buildParsingTagText() {
        switch (parsingTag) {
            case OPTION_TAG: {
                sceneOptions.add(new Pair<>(optionName,sceneTextBuilder.toString()));
                break;
            }
            case SCRIPT_TAG: {
                sceneText = sceneTextBuilder.toString();
                break;
            }
        }
    }

    private static void appendLine(String input) {
        if (input.isEmpty()) {
            ++sceneNewLineBuffer;
        } else {
            String newLineBuffer = "";
            for (int i = sceneNewLineBuffer; i > 0; --i) {
                newLineBuffer += "\n";
            }
            sceneTextBuilder.append(newLineBuffer+"\n"+input);
            sceneNewLineBuffer = 0;
        }
    }
}
