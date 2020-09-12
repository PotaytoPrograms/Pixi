package me.potaytoprograms.pixi.shared.scene;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;

public class SceneManager implements Disposable {
	
	public Engine engine;
	private IScene scene;
	
	public SceneManager(){
		engine = new Engine();
		ShaderProgram.pedantic = false;
	}
	
	public void update(float delta){
		if(scene != null) scene.update(delta);
	}
	
	public void loadScene(IScene scene){
		if(this.scene != null) this.scene.dispose();
		this.scene = scene;
		this.scene.init(this);
	}
	
	@Override
	public void dispose() {
		scene.dispose();
		engine.removeAllEntities();
	}
}
