package me.potaytoprograms.pixi.p2d.box2d;

import com.badlogic.gdx.math.Vector2;

public class RayCastRenderRequest {
	
	public Vector2 start, end, contact, normal;
	
	public RayCastRenderRequest(Vector2 start, Vector2 end, Vector2 contact, Vector2 normal){
		this.start = start;
		this.end = end;
		this.contact = contact;
		this.normal = normal;
	}
}
