package me.potaytoprograms.pixi.api.ashley.entities;

import me.potaytoprograms.pixi.api.scene.Scene;

import java.util.HashMap;

/**
 * An place holder entity, good for root
 */
public class NullEntity extends PEntity{

    public NullEntity(Scene scene, String name, float x, float y) {
        super(scene, name, x, y);
    }

    @Override
    public void addChild(PEntity entity) {
        super.addChild(entity);
    }

    @Override
    public PEntity getChild(String name) {
        return super.getChild(name);
    }

    @Override
    public PEntity removeChild(String name) {
        return super.removeChild(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Scene getScene() {
        return super.getScene();
    }

    @Override
    public HashMap getTree() {
        return super.getTree();
    }

    @Override
    public PEntity getParent() {
        return super.getParent();
    }

    @Override
    public String getPath() {
        return super.getPath();
    }
}
