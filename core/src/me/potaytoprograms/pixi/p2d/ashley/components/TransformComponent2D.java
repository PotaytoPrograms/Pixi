package me.potaytoprograms.pixi.p2d.ashley.components;

import com.badlogic.ashley.core.Component;

public class TransformComponent2D implements Component {
	public float x,y, width, height, scaleX, scaleY, rotation;
	public int zSort;
	
	public TransformComponent2D(float x, float y, float width, float height, float scaleX, float scaleY, float rotation, int zSort){
		this.x = x;
		this.y = y;
		this.width = width * scaleX;
		this.height = height * scaleY;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.rotation = rotation;
		this.zSort = zSort;
	}
}
