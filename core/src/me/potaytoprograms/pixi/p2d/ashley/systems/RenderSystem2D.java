package me.potaytoprograms.pixi.p2d.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.potaytoprograms.pixi.p2d.ashley.components.AnimationComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.ImageComponent2D;
import me.potaytoprograms.pixi.shared.ashley.util.Mapper;

import java.util.Comparator;

public class RenderSystem2D extends SortedIteratingSystem {
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public RenderSystem2D(int priority, SpriteBatch batch, OrthographicCamera camera) {
		super(Family.one(ImageComponent2D.class, AnimationComponent2D.class).get(),new SortZ(), priority);
		this.batch = batch;
		this.camera = camera;
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		if(Mapper.IMAGE_COMPONENT_2_D_COMPONENT_MAPPER.has(entity)){
			Mapper.IMAGE_COMPONENT_2_D_COMPONENT_MAPPER.get(entity).draw(batch);
		}
		if(Mapper.ANIMATION_COMPONENT_2_D_COMPONENT_MAPPER.has(entity)){
			Mapper.ANIMATION_COMPONENT_2_D_COMPONENT_MAPPER.get(entity).draw(batch, deltaTime);
		}
		if(Mapper.PARTICLE_COMPONENT_2_D_COMPONENT_MAPPER.has(entity)){
			Mapper.PARTICLE_COMPONENT_2_D_COMPONENT_MAPPER.get(entity).draw(batch, deltaTime);
		}
	}
	
	private static class SortZ implements Comparator<Entity>{
		
		
		@Override
		public int compare(Entity entity, Entity t1) {
			int z1 = Mapper.TRANSFORM_COMPONENT_COMPONENT_MAPPER.get(entity).zSort;
			int z2 = Mapper.TRANSFORM_COMPONENT_COMPONENT_MAPPER.get(t1).zSort;
			return (int)Math.signum(z1-z2);
		}
	}
}
