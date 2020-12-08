package me.potaytoprograms.pixi.test;

import me.potaytoprograms.pixi.api.ashley.entities.NullEntity;
import me.potaytoprograms.pixi.api.scene.Scene;
//TODO: Add rest of systems and components
public class GameScene extends Scene {

    @Override
    public void init() {
        setRoot(new NullEntity(this, "root", 0, 0));
        getRoot().addChild(new NullEntity(this, "1", 0, 0));
    }

    @Override
    public void dispose() {

    }
}
