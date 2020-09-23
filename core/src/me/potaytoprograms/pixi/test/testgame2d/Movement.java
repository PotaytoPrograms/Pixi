package me.potaytoprograms.pixi.test.testgame2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import me.potaytoprograms.pixi.p2d.ashley.components.PhysicsComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.TransformComponent2D;
import me.potaytoprograms.pixi.shared.ashley.util.IScript;
import me.potaytoprograms.pixi.shared.ashley.util.Mapper;
import me.potaytoprograms.pixi.test.Constants;

public class Movement implements IScript {
	
	private final float gravity = -50;
	private final float speed = 1000;
	private final float jumpPower = 5000;
	private final float jumpMulti = 5;
	private final float length = 1;
	private float yVel = 0;
	private boolean grounded = false;
	private boolean justJumped = false;
	private World world;
	private Body body;
	private PhysicsComponent2D pc;
	private final Vector2 start = new Vector2();
	private final Vector2 end = new Vector2();
	private TransformComponent2D transform;
	private final OrthographicCamera camera;
	
	public Movement(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public void init(Entity entity) {
		pc = Mapper.PHYSICS_COMPONENT_COMPONENT_MAPPER.get(entity);
		body = pc.body;
		world = body.getWorld();
		transform = Mapper.TRANSFORM_COMPONENT_COMPONENT_MAPPER.get(entity);
		body.setLinearDamping(0);
	}
	
	@Override
	public void update(float delta) {
		pc.updateTransform();
		camera.position.set(body.getPosition(), 0);
		camera.update();
		updateGround();
		yVel = yVel + gravity;
		if(grounded)yVel = -0.1f;
		System.out.println(body.getLinearVelocity().y);
		float x = 0;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) x = -speed;
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x = speed;
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && grounded) {
			yVel = jumpPower;
			justJumped = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP) && !grounded && justJumped && yVel > 0) yVel += jumpMulti;
		if(!Gdx.input.isKeyPressed(Input.Keys.UP)) justJumped = false;
		body.setLinearVelocity(x * delta, yVel * delta);
	}
	
	private void updateGround(){
		grounded = false;
		Vector2 pos = body.getPosition();
		RayCastCallback rayCastCallback = (fixture, point, normal, fraction) -> {
			if(fixture.getFilterData().categoryBits == Constants.WORLD) {
				grounded = true;
			}
			return 0;
		};
		
		start.set(pos.x - transform.width/2, pos.y - transform.height/2);
		end.set(start.x, start.y - length);
		world.rayCast(rayCastCallback, start, end);
		
		start.set(pos.x + transform.width/2, pos.y - transform.height/2);
		end.set(start.x, start.y - length);
		world.rayCast(rayCastCallback, start, end);
	}
	
	@Override
	public void dispose() {
	
	}
}
