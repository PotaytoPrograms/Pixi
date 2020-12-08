package me.potaytoprograms.pixi.api.game;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import me.potaytoprograms.pixi.api.ashley.components.ScriptComponent;
import me.potaytoprograms.pixi.api.ashley.components.TransformComponent;
import me.potaytoprograms.pixi.api.ashley.systems.ScriptSystem;

/**
 * A singleton for pixi, contains various things that would be needed everywhere
 */
public class Game implements Disposable {

    private static Engine engine;
    private static boolean init = false;
    private static World world;
    private static ScriptSystem scriptSystem;

    public static final ComponentMapper<TransformComponent> TRANSFORM_COMPONENT = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<ScriptComponent> SCRIPT_COMPONENT = ComponentMapper.getFor(ScriptComponent.class);

    public static void init(){
        init(new Engine());
    }

    public static void init(Engine engine){
        init = true;
        Game.engine = engine;
        world = new World(new Vector2(0,-9.8f), true);
        scriptSystem = new ScriptSystem();

        sysInit();
    }

    /**
     * Adds all default systems to the engine, called when loading a scene in case they were changed for a specific scene
     */
    public static void sysInit(){
        engine.addSystem(scriptSystem);
    }

    public static Engine getEngine(){
        return engine;
    }

    public static World getWorld(){
        return world;
    }

    @Override
    public void dispose() {
        world.dispose();
        engine.removeAllEntities();
    }
}
