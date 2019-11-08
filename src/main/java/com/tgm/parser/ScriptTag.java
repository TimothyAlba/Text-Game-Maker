package com.tgm.parser;

public class ScriptTag {

    private static final char TAG_PREFIX = '|';
    private static final char TAG_SUFFIX = ':';

    private String tagName;
    private String tagRegex;

    private ScriptTag() {
        //Ignored
    }

    public ScriptTag(String tagName) {
        this.tagName = tagName;
        this.tagRegex = "\\"+TAG_PREFIX+this.tagName+TAG_SUFFIX+".*";
    }

    public boolean textContainsTag(String text) {
        return text.matches(tagRegex);
    }

    public static String getGenericScriptTagRegex() {
        return "\\"+TAG_PREFIX+".*"+TAG_SUFFIX+".*";
    }

    public static String stripTagFromText(String taggedString) {
        return taggedString.substring(taggedString.indexOf(TAG_SUFFIX)+1);
    }
}
