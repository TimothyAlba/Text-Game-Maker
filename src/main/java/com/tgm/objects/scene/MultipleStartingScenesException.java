package com.tgm.objects.scene;

public class MultipleStartingScenesException extends Exception {

    private MultipleStartingScenesException() {

    }

    public MultipleStartingScenesException(SceneMap sceneMap, Scene badScene) {
        super("Duplicate starting scenes in script!\tFirst starting scene: "+sceneMap.getStartingScene().getName()+"\tSecond starting scene: "+badScene.getName());
    }
}
