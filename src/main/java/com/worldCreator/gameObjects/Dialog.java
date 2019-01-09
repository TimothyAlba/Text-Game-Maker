package com.worldCreator.gameObjects;

import java.util.ArrayList;

public class Dialog {

    private ArrayList<Text> speaker;
    private Reply response;

    protected Dialog(){}

    public Dialog(ArrayList<Text> speaker) {
        this(speaker, null);
    }

    public Dialog(ArrayList<Text> speaker, Reply response) {
        this.speaker = speaker;
        this.response = response;
    }

    public ArrayList<Text> getSpeaker() {
        return speaker;
    }

    public Reply getResponse() {
        return response;
    }

    public boolean hasResponse() {
        if(response != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
