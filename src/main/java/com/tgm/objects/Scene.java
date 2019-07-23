package com.tgm.objects;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class Scene {
    private String name;
    private String text;
    private List<Pair<String,String>> options;
    private boolean end;

    private Scene() {

    }

    public Scene (String name, String text, List<Pair<String,String>> options, boolean end) {
        this.name = name;
        this.text = text;
        this.options = options;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Pair<String,String>> getOptions() {
        return options;
    }

    public void setOptions(List<Pair<String,String>> options) {
        this.options = options;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("Scene: %s" +
                "Text: %s" +
                "Options: ",getName(),getText(),optionsToString());
    }

    private String optionsToString() {
        String compiledString = "";
        int optionCounter = 1;
        for (Pair<String,String> option : options) {
            compiledString += String.format("%n%t%d%t%s",optionCounter,option.getValue());
            ++optionCounter;
        }
        return compiledString;
    }
}
