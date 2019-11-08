package com.tgm.parser;

import com.tgm.objects.*;
import com.tgm.objects.scene.MultipleStartingScenesException;
import com.tgm.objects.scene.NoStartingSceneException;
import com.tgm.objects.scene.SceneMap;
import com.tgm.parser.objects.scene.SceneParser;

import java.io.*;

public class TextParser {
    public static Game parse(String filePath) throws IOException, BadScriptTagException, MultipleStartingScenesException, NoStartingSceneException {
        Game game = null;
        File inFile = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        try {
            game = parseFile(reader);
        } finally {
            reader.close();
        }

        return game;
    }

    private static Game parseFile(BufferedReader reader) throws IOException, BadScriptTagException, MultipleStartingScenesException, NoStartingSceneException {
        SceneMap sceneMap = new SceneMap();
        String tempString = reader.readLine();
        newObject : do {
            switch (ScriptMainTags.getScriptMainTag(tempString)) {
                case SCENE_TAG: {
                    tempString = SceneParser.buildScene(reader,sceneMap,tempString);
                    break;
                }
            }
        } while(tempString !=null);
        return new Game(sceneMap);
    }
}