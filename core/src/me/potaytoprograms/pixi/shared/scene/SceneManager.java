package me.potaytoprograms.pixi.shared.scene;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import me.potaytoprograms.pixi.shared.ashley.RemoveListener;

public class SceneManager implements Disposable {
	
	public Engine engine;
	private IScene scene;
	private final RemoveListener removeListener;
	
	public SceneManager(){
		engine = new Engine();
		removeListener = new RemoveListener();
		engine.addEntityListener(removeListener);
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
