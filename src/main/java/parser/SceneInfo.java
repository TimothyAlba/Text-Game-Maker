package parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SceneInfo {
    public String scene;
    public String script;
    public boolean isStart;
    public boolean isEnd;
    public List<String> options;

    public SceneInfo() {
        scene = "";
        script = "";
        isStart = false;
        isEnd = false;
        options = new ArrayList();
    }

    public void AddLine(String action, String value) throws Exception {
        switch(action.toLowerCase()){
            case "scene":
                if (scene.isEmpty()){
                    scene = value;
                }
                else {
                    throw new Exception();
                }
                break;
            case "script":
                if (script.isEmpty()){
                    script = value;
                }
                else {
                    throw new Exception();
                }
                break;
            case "option":
                options.add(value);
                break;
            case "start":
                isStart = true;
                break;
            case "end":
                isEnd = true;
                break;
        }
    }
    public boolean IsValid() {
        if (!scene.isEmpty() && (!script.isEmpty() || !options.isEmpty())){
            return true;
        }
        return false;
    }
}
