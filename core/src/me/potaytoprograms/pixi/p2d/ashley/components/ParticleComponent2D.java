package me.potaytoprograms.pixi.p2d.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import me.potaytoprograms.pixi.p2d.render.Particle2D;
import me.potaytoprograms.pixi.p2d.util.ShaderSetup;

import java.util.HashMap;

public class ParticleComponent2D implements Component {

	private HashMap<String, Particle2D> particles;
	private ShaderProgram shader;
	private ShaderSetup setup;
	private HashMap<ParticleEffectPool.PooledEffect, Particle2D> effects;
	
	public ParticleComponent2D(ShaderProgram shader, ShaderSetup setup){
		this.shader = shader;
		this.setup = setup;
		particles = new HashMap<>();
		effects = new HashMap<>();
	}
	
	public void draw(SpriteBatch batch, float delta){
		batch.setShader(shader);
		batch.begin();
		if(setup != null) setup.setup(shader);
		for(ParticleEffectPool.PooledEffect effect : effects.keySet()){
			Particle2D t = effects.get(effect);
			effect.setPosition(t.transform.x + t.offSet.x, t.transform.y + t.offSet.y);
			effect.draw(batch, delta);
		}
		batch.end();
	}
	
	public void addParticle(String name, Particle2D particle){
		particles.put(name, particle);
	}
	
	public Particle2D getParticle(String name){
		return particles.get(name);
	}
	
	public void removeParticle(String name){
		particles.remove(name);
	}
	
	public void playParticle(String name){
		particles.get(name).effect.start();
		ParticleEffectPool.PooledEffect effect = particles.get(name).particle.obtain();
		Particle2D particle2D = particles.get(name);
		effect.setPosition(particle2D.transform.x + particle2D.offSet.x, particle2D.transform.y + particle2D.offSet.y);
		effect.scaleEffect(particle2D.transform.scaleX * particle2D.scale, particle2D.transform.scaleY * particle2D.scale, ((particle2D.transform.scaleX + particle2D.transform.scaleY)/2)*particle2D.scale);
		effects.put(effect, particle2D);
	}
	
	public void stopParticle(String name){
		particles.get(name).effect.reset();
		effects.remove(particles.get(name).particle.obtain(), false);
	}
}
