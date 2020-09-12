package me.potaytoprograms.pixi.shared.ashley.util;

import com.badlogic.ashley.core.Entity;

public interface IScript {
	
	void init(Entity entity);
	void update(float delta);
	void dispose();
}
