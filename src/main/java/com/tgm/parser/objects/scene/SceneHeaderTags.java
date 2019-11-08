package com.tgm.parser.objects.scene;

import com.tgm.Utilities;

public enum SceneHeaderTags {

    START("start",0),
    END("end",1);

    String phrase;
    int number;

    SceneHeaderTags(String phrase, int number) {
        this.phrase = phrase;
        this.number = number;
    }

    public static SceneHeaderTags getTagByPhrase(String text) {
        text = Utilities.trimWhiteSpace(text);
        for (SceneHeaderTags tag : SceneHeaderTags.values()) {
            if (tag.phrase.equalsIgnoreCase(text))
                return tag;
        }
        return null;
    }
}
