package com.tgm.parser.objects.scene;

import com.tgm.parser.ScriptTag;

public enum SceneObjectTags {

    OPTION_TAG("option"),
    SCRIPT_TAG("script");

    private ScriptTag tag;

    SceneObjectTags(String tagName) {
        tag = new ScriptTag(tagName);
    }

    public boolean textContainsTag(String text) {
        return tag.textContainsTag(text);
    }

    public static SceneObjectTags getSceneObjectTag(String text) {
        for (SceneObjectTags tag : SceneObjectTags.values()) {
            if (tag.textContainsTag(text))
                return tag;
        }
        return null;
    }
}
