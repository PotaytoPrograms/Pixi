package me.potaytoprograms.pixi.api.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import me.potaytoprograms.pixi.api.game.Game;

public class SceneManager implements Disposable {

    private Scene scene;
    private Scene loadingScene;

    public SceneManager(){

    }

    /**
     * ques a scene to be loaded at the end of the frame
     * @param scene the scene you want to load
     */
    public void loadScene(Scene scene){
        loadingScene = scene;
    }

    public void update(){
        if(scene != null) scene.update(Gdx.graphics.getDeltaTime());
        if(loadingScene != null){
            System.out.println("loading scene");
            if(scene != null) scene.dispose();
            Game.getEngine().removeAllEntities();
            Game.sysInit();
            scene = loadingScene;
            scene.init();
            loadingScene = null;
        }
    }

    @Override
    public void dispose() {
        if(scene != null) scene.dispose();
        if(loadingScene != null) loadingScene.dispose();
    }
}
