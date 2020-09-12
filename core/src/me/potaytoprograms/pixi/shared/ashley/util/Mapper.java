package me.potaytoprograms.pixi.shared.ashley.util;

import com.badlogic.ashley.core.ComponentMapper;
import me.potaytoprograms.pixi.p2d.ashley.components.*;
import me.potaytoprograms.pixi.shared.ashley.components.ScriptComponent;

public class Mapper {
	public static final ComponentMapper<TransformComponent2D> TRANSFORM_COMPONENT_COMPONENT_MAPPER = ComponentMapper.getFor(TransformComponent2D.class);
	public static final ComponentMapper<ScriptComponent> SCRIPT_COMPONENT_COMPONENT_MAPPER = ComponentMapper.getFor(ScriptComponent.class);
	public static final ComponentMapper<PhysicsComponent2D> PHYSICS_COMPONENT_COMPONENT_MAPPER = ComponentMapper.getFor(PhysicsComponent2D.class);
	public static final ComponentMapper<ImageComponent2D> IMAGE_COMPONENT_2_D_COMPONENT_MAPPER = ComponentMapper.getFor(ImageComponent2D.class);
	public static final ComponentMapper<AnimationComponent2D> ANIMATION_COMPONENT_2_D_COMPONENT_MAPPER = ComponentMapper.getFor(AnimationComponent2D.class);
	public static final ComponentMapper<ParticleComponent2D> PARTICLE_COMPONENT_2_D_COMPONENT_MAPPER = ComponentMapper.getFor(ParticleComponent2D.class);
}
