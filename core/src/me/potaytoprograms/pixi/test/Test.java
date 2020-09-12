package me.potaytoprograms.pixi.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import me.potaytoprograms.pixi.p2d.box2d.RayCastRender;
import me.potaytoprograms.pixi.shared.scene.SceneManager;
import me.potaytoprograms.pixi.test.scenes.GameScene;

public class Test extends ApplicationAdapter {
	
	SceneManager sceneManager;
	SpriteBatch batch;
	
	@Override
	public void create () {
		sceneManager = new SceneManager();
		batch = new SpriteBatch();
		sceneManager.loadScene(new GameScene(batch));
		Box2D.init();
		RayCastRender.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sceneManager.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		sceneManager.dispose();
		batch.dispose();
		RayCastRender.dispose();
	}
}
