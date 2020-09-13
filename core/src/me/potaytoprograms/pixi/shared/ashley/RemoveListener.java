package me.potaytoprograms.pixi.shared.ashley;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;

public class RemoveListener implements EntityListener {
	@Override
	public void entityAdded(Entity entity) {
	
	}
	
	@Override
	public void entityRemoved(Entity entity) {
		if(entity instanceof PEntity) ((PEntity)entity).dispose();
	}
}
