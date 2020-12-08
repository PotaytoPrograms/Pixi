package me.potaytoprograms.pixi.test;

import com.badlogic.gdx.ApplicationAdapter;
import me.potaytoprograms.pixi.api.game.Game;
import me.potaytoprograms.pixi.api.scene.SceneManager;

public class Test extends ApplicationAdapter {

    public SceneManager scnMgr;

    @Override
    public void create() {
        super.create();
        Game.init();
        scnMgr = new SceneManager();
        scnMgr.loadScene(new GameScene());
    }

    @Override
    public void render() {
        super.render();
        scnMgr.update();
    }
}
