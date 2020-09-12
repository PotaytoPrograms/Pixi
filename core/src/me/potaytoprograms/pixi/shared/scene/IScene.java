package me.potaytoprograms.pixi.shared.scene;

public interface IScene {
	
	void init(SceneManager sceneManager);
	void update(float delta);
	void dispose();
}
