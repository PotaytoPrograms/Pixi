package me.potaytoprograms.pixi.api.scene;

import com.badlogic.gdx.utils.Disposable;
import me.potaytoprograms.pixi.api.ashley.entities.PEntity;
import me.potaytoprograms.pixi.api.game.Game;

import java.util.HashMap;

//TODO:add scene sception (instance scenes inside a scene)
public abstract class Scene implements Disposable {

    private PEntity root;
    private final HashMap<String, PEntity> tree = new HashMap<>();

    public void init(){
    }

    public void setRoot(PEntity entity){
        root = entity;
        root.init(root.getPath(), null);
        tree.put(root.getPath(), root);
        Game.getEngine().addEntity(root);
    }

    public void update(float delta){
        Game.getEngine().update(delta);
    }

    public void physicsUpdate(float delta){
        Game.getWorld().step(1f/60f, 6, 2);
    }

    /**
     * gets the hierarchy hasmap for this scene
     * @return hashmap hierarchy
     */
    public HashMap getTree(){
        return tree;
    }

    public PEntity getRoot(){
        return root;
    }
}
