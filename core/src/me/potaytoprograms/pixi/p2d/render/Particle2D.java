package me.potaytoprograms.pixi.p2d.render;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import me.potaytoprograms.pixi.p2d.ashley.components.TransformComponent2D;

public class Particle2D {
	
	public TransformComponent2D transform;
	public Vector2 offSet;
	public ParticleEffectPool particle;
	public ParticleEffect effect;
	public float scale;
	
	public Particle2D(TransformComponent2D transform, Vector2 offSet, ParticleEffect particle, int startAmount, int maxAmount, float scale){
		this.transform = transform;
		this.offSet = offSet;
		this.particle = new ParticleEffectPool(particle, startAmount, maxAmount);
		this.effect = particle;
		this.scale = scale;
		effect.setEmittersCleanUpBlendFunction(false);
	}
}
