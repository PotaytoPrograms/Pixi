package me.potaytoprograms.pixi.test.testgame2d;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import me.potaytoprograms.pixi.p2d.ashley.components.ImageComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.PhysicsComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.TransformComponent2D;
import me.potaytoprograms.pixi.shared.ashley.PEntity;
import me.potaytoprograms.pixi.shared.ashley.components.ScriptComponent;
import me.potaytoprograms.pixi.test.Constants;

public class Player extends PEntity {
	
	private final ImageComponent2D image;
	private final ScriptComponent scriptComponent;
	
	public Player(float x, float y, World world, OrthographicCamera camera){
		Texture texture = new Texture("badlogic.jpg");
		
		TransformComponent2D transform = new TransformComponent2D(x, y, texture.getWidth(), texture.getHeight(), 1.0f/Constants.PPM, 1.0f/Constants.PPM, 0, 0);
		add(transform);
		
		image = new ImageComponent2D(texture, transform, null, null);
		add(image);
		
		Movement m = new Movement(camera);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(transform.width/2, transform.height/2);
		PhysicsComponent2D p = new PhysicsComponent2D(transform, world, shape, 0, 0, 1, Constants.PLAYER, Constants.WORLD, (short)0, true, false, false, m);
		add(p);
		
		scriptComponent = new ScriptComponent(this);
		add(scriptComponent);
		scriptComponent.addScript("movement", m);
	}
	
	@Override
	public void dispose() {
		image.dispose();
		scriptComponent.dispose();
	}
}