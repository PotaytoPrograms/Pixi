package me.potaytoprograms.pixi.shared.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import me.potaytoprograms.pixi.shared.ashley.util.IScript;

import java.util.HashMap;
import java.util.Map;

public class ScriptComponent implements Component {
	private HashMap<String, IScript> scripts;
	private Entity entity;
	
	public ScriptComponent(Entity entity){
		this.entity = entity;
		scripts = new HashMap<>();
	}
	
	public void addScript(String id, IScript script){
		scripts.put(id, script);
		script.init(entity);
	}
	
	public void update(float delta){
		for(Map.Entry<String, IScript> entry : scripts.entrySet()){
			entry.getValue().update(delta);
		}
	}
	
	public void removeScript(String id){
		if(scripts.containsKey(id)){
			scripts.remove(id);
		}
	}
	
	public IScript getScript(String id){
		return scripts.get(id);
	}
}
