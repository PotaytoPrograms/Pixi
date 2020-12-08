package me.potaytoprograms.pixi.api.ashley.entities;

import com.badlogic.ashley.core.Entity;
import com.sun.istack.internal.Nullable;
import me.potaytoprograms.pixi.api.ashley.components.ScriptComponent;
import me.potaytoprograms.pixi.api.ashley.components.TransformComponent;
import me.potaytoprograms.pixi.api.game.Game;
import me.potaytoprograms.pixi.api.scene.Scene;

import java.util.HashMap;

/**
 * A base entity required if you want to use pixi features
 */
//TODO: Add pre-made entities for the different components (useful for quick development)
public abstract class PEntity extends Entity {

    private final String name;
    private final HashMap<String, PEntity> children = new HashMap<>();
    private final Scene scene;
    public TransformComponent transform;
    public TransformComponent globalTransform;
    private final ScriptComponent scriptComponent;
    private PEntity parent;
    private String path;

    public PEntity(Scene scene, String name, float x, float y){
        this.name = name;
        this.scene = scene;
        this.scriptComponent = new ScriptComponent(this);
        this.path = "/" + name;
        transform = new TransformComponent(x, y, 0, 0, 1, 1);
    }

    /**
     * Initializes variables that need a parent entity
     * @param path Where this entity is in the hierarchy
     * @param parent The parent entity
     */
    public void init(String path, @Nullable PEntity parent){
        this.path = path;
        this.parent = parent;
        if(scene == null) System.err.println(path + ": scene cannot be null");
        if(parent == null)
            globalTransform = transform;
        else
            //TODO:(transform) make everything relative to the parent, needed for ui features
            globalTransform = new TransformComponent(parent.globalTransform.x + transform.x, parent.globalTransform.y + transform.y, transform.width, transform.height, transform.scaleX, transform.scaleY);

        add(globalTransform);
    }

    /**
     * add a child entity
     * @param entity
     */
    public void addChild(PEntity entity){
        children.put(entity.getName(), entity);
        entity.init(getPath() + entity.getPath(), this);
        getTree().put(entity.getPath(), entity);
        Game.getEngine().addEntity(entity);
    }

    public PEntity getChild(String name){
        if(children.containsKey(name)){
            return children.get(name);
        }
        return null;
    }

    public PEntity removeChild(String name){
        if(children.containsKey(name)){
            Game.getEngine().removeEntity(children.get(name));
            return children.remove(name);
        }
        return null;
    }

    public String getName(){
        return name;
    }

    public Scene getScene(){
        return scene;
    }

    /**
     * Gets the hierarchy hashmap of the scene
     * @return
     */
    public HashMap getTree(){
        return scene.getTree();
    }

    public PEntity getParent(){
        return parent;
    }

    public String getPath(){
        return path;
    }

    /**
     * gets the script component
     * @return
     */
    public ScriptComponent getSC(){
        return scriptComponent;
    }
}
