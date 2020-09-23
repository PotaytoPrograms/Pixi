package me.potaytoprograms.pixi.p2d.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import me.potaytoprograms.pixi.p2d.util.ShaderSetup;

public class ImageComponent2D implements Component, Disposable {
	
	public Sprite sprite;
	private final TransformComponent2D transform;
	public ShaderProgram shader;
	private final ShaderSetup setup;
	public boolean visible = true;
	
	public ImageComponent2D(Texture img, TransformComponent2D transform, ShaderProgram shader, ShaderSetup setup){
		this(new Sprite(img), transform, shader, setup);
	}
	
	public ImageComponent2D(TextureRegion img, TransformComponent2D transform, ShaderProgram shader, ShaderSetup setup){
		this(new Sprite(img), transform, shader, setup);
	}
	
	public ImageComponent2D(Sprite img, TransformComponent2D transform, ShaderProgram shader, ShaderSetup setup){
		sprite = new Sprite(img);
		this.transform = transform;
		this.shader = shader;
		this.setup = setup;
		
		sprite.setScale(this.transform.scaleX, this.transform.scaleY);
		sprite.setOriginCenter();
		sprite.setRotation(this.transform.rotation);
		this.transform.width = sprite.getWidth() * sprite.getScaleX();
		this.transform.height = sprite.getHeight() * sprite.getScaleY();
	}
	
	public void draw(SpriteBatch batch){
		if(!visible) return;
		sprite.setPosition(transform.x - sprite.getWidth()/2, transform.y - sprite.getHeight()/2);
		sprite.setScale(transform.scaleX, transform.scaleY);
		sprite.setRotation(transform.rotation);
		batch.setShader(shader);
		batch.begin();
		if(shader != null) {
			shader.bind();
			if (setup != null) setup.setup(shader);
			shader.end();
		}
		sprite.draw(batch);
		batch.end();
		batch.setShader(null);
	}
	
	@Override
	public void dispose() {
		sprite.getTexture().dispose();
		if(shader != null) shader.dispose();
	}
}
