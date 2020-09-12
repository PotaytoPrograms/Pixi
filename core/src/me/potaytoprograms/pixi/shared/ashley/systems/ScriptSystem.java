package me.potaytoprograms.pixi.shared.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.potaytoprograms.pixi.shared.ashley.components.ScriptComponent;
import me.potaytoprograms.pixi.shared.ashley.util.Mapper;

public class ScriptSystem extends IteratingSystem {
	
	public ScriptSystem(int priority){
		super(Family.all(ScriptComponent.class).get(), priority);
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		Mapper.SCRIPT_COMPONENT_COMPONENT_MAPPER.get(entity).update(deltaTime);
	}
}
