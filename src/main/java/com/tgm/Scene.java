package com.tgm;

import java.util.List;

public class Scene {
    private String name;
    private String text;
    private List<String> options;
    private boolean end;

    private Scene() {

    }

    public Scene (String name, String text, List<String> options, boolean end) {
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
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
        for (String option : getOptions()) {
            compiledString += String.format("%n%t%t%s",option);
        }
        return compiledString;
    }
}
