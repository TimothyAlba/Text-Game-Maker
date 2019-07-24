package com.tgm.objects;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scene {
    private String name;
    private String text;
    private List<Pair<String,String>> options;
    private boolean end;

    public Scene() {
        options = new ArrayList<Pair<String, String>>();
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

    public void addOption(String key, String value) {
        options.add(new Pair<String, String>(key, value));
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

    public boolean IsValid() {
        if (!this.name.isEmpty() && (!this.text.isEmpty() || !options.isEmpty())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        return String.format("Scene: %s" +
                "%nText: %s" +
                "%nOptions: %s",getName(),getText(),optionsToString());
    }

    private String optionsToString() {
        String compiledString = "";
        int optionCounter = 1;
        for (Pair<String,String> option : options) {
            compiledString += "\n\t" + option.getKey() + "\t" + option.getValue();
            ++optionCounter;
        }
        return compiledString;
    }
}
