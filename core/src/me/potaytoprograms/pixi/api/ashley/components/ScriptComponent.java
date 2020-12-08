package me.potaytoprograms.pixi.api.ashley.components;

import com.badlogic.ashley.core.Component;
import me.potaytoprograms.pixi.api.ashley.entities.PEntity;
import me.potaytoprograms.pixi.api.util.Script;

import java.util.HashMap;

/**
 * The component that holds all of the scripts and runs them
 */
public class ScriptComponent implements Component {
    /**
     * the scripts
     */
    private HashMap<String, Script> scripts;
    /**
     * the entity this is linked to
     */
    private final PEntity entity;

    /**
     *
     * @param entity the entity this is linked to
     */
    public ScriptComponent(PEntity entity){
        this.entity = entity;
    }

    /**
     * Adds a script to the entity
     * @param name the name of the script, so that you can get it or remove it later
     * @param script the script you are adding
     */
    public void addScript(String name, Script script){
        scripts.put(name, script);
        script.init(entity);
    }

    /**
     * Gets a script
     * @param name the name of the script you want ot get
     * @return returns the script if it exists otherwise it returns null
     */
    public Script getScript(String name){
        if(scripts.containsKey(name)) {
            return scripts.get(name);
        }
        return null;
    }

    /**
     * Removes a script
     * @param name the name of the script you want ot get
     * @return returns the script if it exists otherwise it returns null
     */
    public Script removeScript(String name){
        if(scripts.containsKey(name)){
            scripts.get(name).dispose();
            return scripts.remove(name);
        }
        return null;
    }

    /**
     * called every frame, runs the update function in all scripts
     * @param delta the time since the last frame
     */
    public void update(float delta){
        for(Script script : scripts.values()){
            script.update(delta);
        }
    }
}
