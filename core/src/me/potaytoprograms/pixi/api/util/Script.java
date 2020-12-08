package me.potaytoprograms.pixi.api.util;

import com.badlogic.gdx.utils.Disposable;
import me.potaytoprograms.pixi.api.ashley.entities.PEntity;

/**
 * A class to add functionality to PEntities
 */
public abstract class Script implements Disposable {

    /**
     * The entity this script is linked to
    */
    public PEntity entity;

    /**
     * Called every frame
     * @param delta time since the last frame
     */
    public abstract void update(float delta);

    /**
     * Initialize the script
     * @param entity the entity this script is linked to
     */
    public void init(PEntity entity){
        this.entity = entity;
    }
}
