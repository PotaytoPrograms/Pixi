package me.potaytoprograms.pixi.p2d.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.esotericsoftware.spine.SkeletonRenderer;
import me.potaytoprograms.pixi.p2d.render.Animation2D;
import me.potaytoprograms.pixi.p2d.render.ImageAnimation;
import me.potaytoprograms.pixi.p2d.render.SpineAnimation;
import me.potaytoprograms.pixi.p2d.util.ShaderSetup;

import java.util.HashMap;

public class AnimationComponent2D implements Component {
	
	private TransformComponent2D transform;
	private ShaderProgram shader;
	private ShaderSetup setup;
	private HashMap<String, Animation2D> animations;
	private String currAnim;
	private SkeletonRenderer skeletonRenderer;
	
	public AnimationComponent2D(TransformComponent2D transform, ShaderProgram shader, ShaderSetup setup){
		this.transform = transform;
		this.shader = shader;
		this.setup = setup;
		animations = new HashMap<>();
		skeletonRenderer = new SkeletonRenderer();
	}
	
	public void draw(SpriteBatch batch, float delta){
		Animation2D anim = animations.get(currAnim);
		if(anim instanceof ImageAnimation) {
			Sprite sprite = anim.getSprite(delta);
			sprite.setPosition(transform.x - sprite.getWidth() / 2, transform.y - sprite.getHeight() / 2);
			sprite.setScale(transform.scaleX, transform.scaleY);
			sprite.setRotation(transform.rotation);
			batch.setShader(shader);
			batch.begin();
			if(setup != null) setup.setup(shader);
			sprite.draw(batch);
		}else{
			batch.setShader(shader);
			batch.begin();
			if(setup != null) setup.setup(shader);
			((SpineAnimation)anim).update(delta, transform);
			skeletonRenderer.draw(batch,((SpineAnimation)anim).skeleton);
		}
		batch.end();
		
	}
	
	public void addAnim(String name, Animation2D animation){
		animations.put(name, animation);
		if(currAnim == null) currAnim = name;
	}
	
	public void removeAnim(String name){
		animations.remove(name);
	}
	
	public void setAnim(String name){
		if(animations.containsKey(name)) currAnim = name;
	}
	
	public void setAnim(String name, int trackIndex, boolean loop){
		((SpineAnimation) animations.get("spine")).animationState.setAnimation(trackIndex, name, loop);
	}
}
