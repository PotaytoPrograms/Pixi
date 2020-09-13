package me.potaytoprograms.pixi.p2d.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ImageAnimation implements Animation2D {
	
	private Array<Sprite> sprites;
	private final float fps;
	private float lastFrame = 0;
	private int frame = 0;
	private final boolean loop;
	
	public ImageAnimation(Texture texture, int vFrames, int hFrames, float fps, boolean loop){
		this(new TextureRegion(texture), vFrames, hFrames, fps, loop);
	}
	
	public ImageAnimation(TextureRegion texture, int vFrames, int hFrames, float fps, boolean loop){
		int frameSizeX = texture.getRegionWidth() / hFrames;
		int frameSizeY = texture.getRegionHeight() / vFrames;
		for(int x = 0; x < hFrames; x++){
			for(int y = 0; y < vFrames; y++){
				TextureRegion t = new TextureRegion(texture,x,y,frameSizeX,frameSizeY);
				sprites.add(new Sprite(t));
			}
		}
		this.fps = fps;
		this.loop = loop;
	}
	
	public ImageAnimation(Array<Sprite> sprites, float fps, boolean loop){
		this.sprites = sprites;
		this.fps = fps;
		this.loop = loop;
	}
	
	public Sprite getSprite(float delta){
		lastFrame += delta;
		if(lastFrame >= 1.0/fps){
			frame++;
			lastFrame = 0;
			if(frame >= sprites.size) frame = 0;
			return sprites.get(frame);
		}
		return sprites.get(frame);
	}
	
	@Override
	public void dispose() {
		for(Sprite sprite : sprites){
			sprite.getTexture().dispose();
		}
	}
}
