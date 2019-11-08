package com.tgm.parser;

public enum ScriptMainTags {

    SCENE_TAG("scene");

    private ScriptTag tag;

    ScriptMainTags(String tagName) {
        this.tag = new ScriptTag(tagName);
    }

    public boolean textContainsTag(String text) {
        return tag.textContainsTag(text);
    }

    public static ScriptMainTags getScriptMainTag(String text) throws BadScriptTagException {
        for (ScriptMainTags tag : ScriptMainTags.values()) {
            if (tag.textContainsTag(text))
                return tag;
        }
        throw new BadScriptTagException(text);
    }
}
