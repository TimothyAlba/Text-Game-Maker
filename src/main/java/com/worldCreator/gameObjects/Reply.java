package com.worldCreator.gameObjects;

import java.util.ArrayList;

public class Reply {

    private ArrayList<Text> replies;

    protected Reply(){}

    public Reply(ArrayList<Text> replies){
        this.replies = replies;
    }

    public ArrayList<Text> getReplies() {
        return replies;
    }
}
