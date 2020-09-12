package me.potaytoprograms.pixi.p2d.box2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class RayCastRender {
	
	private static ShapeRenderer shapeRenderer;
	private static Array<RayCastRenderRequest> polledRayCasts;
	
	private static Color rayColor = Color.CORAL;
	private static Color contactColor = Color.CYAN;
	private static Color normalColor = Color.FIREBRICK;
	
	public static void init(){
		shapeRenderer = new ShapeRenderer();
		polledRayCasts = new Array<>();
	}
	
	public static void requestRender(RayCastRenderRequest r){
		polledRayCasts.add(r);
	}
	
	public static void render(Matrix4 projMatrix, boolean showContact, boolean showNormal){
		shapeRenderer.setProjectionMatrix(projMatrix);
		while(polledRayCasts.size > 0) {
			RayCastRenderRequest ray = polledRayCasts.get(0);
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(rayColor);
			shapeRenderer.line(ray.start, ray.end);
			shapeRenderer.end();
			if (showContact) {
				if(ray.contact != null) {
					shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
					shapeRenderer.setColor(contactColor);
					shapeRenderer.line(ray.start, ray.contact);
					shapeRenderer.end();
				}
			}
			if (showNormal) {
				if(ray.normal != null) {
					shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
					shapeRenderer.setColor(normalColor);
					shapeRenderer.line(ray.contact, new Vector2(ray.contact.x + ray.normal.x, ray.contact.y + ray.normal.y));
					shapeRenderer.end();
				}
			}
			polledRayCasts.removeIndex(0);
		}
		
	}
	
	public static void setRayColor(Color rayColor) {
		RayCastRender.rayColor = rayColor;
	}
	
	public static void setContactColor(Color contactColor) {
		RayCastRender.contactColor = contactColor;
	}
	
	public static void setNormalColor(Color normalColor) {
		RayCastRender.normalColor = normalColor;
	}
	
	public static void dispose(){
		shapeRenderer.dispose();
	}
}
