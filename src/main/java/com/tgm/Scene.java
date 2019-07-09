package com.tgm;

public class Scene {
    private String name;
    private String text;
    private String [][] options;
    private boolean end;

    private Scene() {

    }

    public Scene (String name, String text, String [][] options, boolean end) {
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

    public String[][] getOptions() {
        return options;
    }

    public void setOptions(String[][] options) {
        this.options = options;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
