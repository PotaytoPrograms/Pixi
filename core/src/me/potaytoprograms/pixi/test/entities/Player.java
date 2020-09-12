package me.potaytoprograms.pixi.test.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import me.potaytoprograms.pixi.p2d.ashley.components.AnimationComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.ParticleComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.PhysicsComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.TransformComponent2D;
import me.potaytoprograms.pixi.p2d.render.ImageAnimation;
import me.potaytoprograms.pixi.p2d.render.Particle2D;
import me.potaytoprograms.pixi.shared.ashley.components.ScriptComponent;
import me.potaytoprograms.pixi.test.scripts.Movement;
import me.potaytoprograms.pixi.test.util.Constants;


public class Player extends Entity {
	
	public Player(World world, float x, float y, OrthographicCamera camera){
		ShaderProgram shader = new ShaderProgram(Gdx.files.internal("passthrough_vert.glsl"), Gdx.files.internal("passthrough_frag.glsl"));
		
		Texture texture = new Texture("badlogic.jpg");
		
		Array<Sprite> textures = new Array<>();
		textures.add(new Sprite(texture));
		textures.add(new Sprite(texture));
		textures.add(new Sprite(texture));
		
		TransformComponent2D transformComponent2D = new TransformComponent2D(x,y, textures.get(0).getWidth(), textures.get(0).getHeight(), 0.125f/Constants.PPM, 0.125f/Constants.PPM, 0, 0);
		add(transformComponent2D);
		
		ImageAnimation animation = new ImageAnimation(textures, 3, true);
		
		AnimationComponent2D animationComponent2D = new AnimationComponent2D(transformComponent2D,shader, null);
		animationComponent2D.addAnim("default", animation);
		add(animationComponent2D);
		
		Movement movement = new Movement();
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(transformComponent2D.width/2, transformComponent2D.height/2);
		PhysicsComponent2D physicsComponent2D = new PhysicsComponent2D(transformComponent2D, world, shape, 0, 0, 1, Constants.PLAYER, Constants.WORLD, (short) 0, true, false, false, movement);
		
		add(physicsComponent2D);
		
		ScriptComponent scriptComponent = new ScriptComponent(this);
		scriptComponent.addScript("Movement", movement);
		add(scriptComponent);
		
		ParticleComponent2D particleComponent2D = new ParticleComponent2D(shader, null);
		ParticleEffect particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal("particle.p"), Gdx.files.internal(""));
		Particle2D particle2D = new Particle2D(transformComponent2D, new Vector2(), particleEffect, 1, 1,3);
		particleComponent2D.addParticle("particle", particle2D);
		particleComponent2D.playParticle("particle");
		add(particleComponent2D);
	}
}