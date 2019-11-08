package com.tgm.parser;

public class BadScriptTagException extends Exception {

    private BadScriptTagException() {
        //Ignored
    }

    public BadScriptTagException(String textWithTag) {
        super("There is no ScriptMainTags tag for the line: "+textWithTag);
    }
}
