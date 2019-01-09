package com.tgm.gameObjects;

import javafx.util.Pair;

public class Text {

    private String text;
    private Pair nextElement;

    protected Text(){}

    public Text(String text){
        this(text, null);
    }

    public Text(String text, Pair nextElement){
        this.text = text;
        this.nextElement = nextElement;
    }

    public String getText() {
        return text;
    }

    public Pair getNextElement() {
        return nextElement;
    }

    public boolean hasNextElement() {
        if(nextElement != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
