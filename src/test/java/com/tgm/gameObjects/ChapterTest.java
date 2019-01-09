package com.tgm.gameObjects;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class ChapterTest {

    private final String SCENE_ID = "start";
    private final Scene SCENE_OBJ = new Scene("bckImg","bkcMsc",new HashMap<String, Dialog>());

    public HashMap<String,Scene> getSceneHashMap() {
        HashMap<String,Scene> scenes = new HashMap<>();
        scenes.put(SCENE_ID,SCENE_OBJ);
        return scenes;
    }

    @Test
    public void getScenesTest() {
        Chapter chapter = new Chapter(getSceneHashMap());
        assertEquals(SCENE_OBJ, chapter.getScene(SCENE_ID));
    }
}
