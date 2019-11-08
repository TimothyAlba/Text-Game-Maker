package com.tgm.objects.scene;

import com.tgm.parser.objects.scene.SceneHeaderTags;
import javafx.util.Pair;
import java.util.List;

public class Scene {

    //Scene objects
    private String name;
    private String text;
    private List<Pair<String,String>> options;
    private SceneHeaderTags tag;

    public Scene(String name, String text, List<Pair<String,String>> options, SceneHeaderTags tag) {
        this.name = name;
        this.text = text;
        this.options = options;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public List<Pair<String,String>> getOptions() {
        return options;
    }

    public String getSceneForOption(int optionNum) {
        return options.get(optionNum).getValue();
    }

    public SceneHeaderTags getTag() {
        return tag;
    }

    public boolean isEnd() {
        return tag != null && tag == SceneHeaderTags.END;
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
                "%nTag: %s" +
                "%nText: %s" +
                "%nOptions: %s",getName(),getTag(),getText(),optionsToString());
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
