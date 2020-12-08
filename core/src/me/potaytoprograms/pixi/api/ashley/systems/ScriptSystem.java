package me.potaytoprograms.pixi.api.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.potaytoprograms.pixi.api.ashley.components.ScriptComponent;
import me.potaytoprograms.pixi.api.ashley.components.TransformComponent;
import me.potaytoprograms.pixi.api.game.Game;

public class ScriptSystem extends IteratingSystem {

    public ScriptSystem() {
        super(Family.all(TransformComponent.class, ScriptComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Game.SCRIPT_COMPONENT.get(entity).update(deltaTime);
    }
}
