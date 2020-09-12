package me.potaytoprograms.pixi.test.scripts;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import me.potaytoprograms.pixi.p2d.ashley.components.PhysicsComponent2D;
import me.potaytoprograms.pixi.p2d.ashley.components.TransformComponent2D;
import me.potaytoprograms.pixi.p2d.box2d.IContactFilter2D;
import me.potaytoprograms.pixi.p2d.box2d.RayCastRender;
import me.potaytoprograms.pixi.p2d.box2d.RayCastRenderRequest;
import me.potaytoprograms.pixi.shared.ashley.util.IScript;
import me.potaytoprograms.pixi.shared.ashley.util.Mapper;
import me.potaytoprograms.pixi.test.util.Constants;

public class Movement implements IScript, IContactFilter2D {
	
	private Body body;
	private PhysicsComponent2D pc;
	private float speed = 50;
	private float gravity = 9.8f;
	private float jumpPower = 100;
	private float jumpMulti = 5;
	private boolean justJumped = false;
	private World world;
	private TransformComponent2D transform;
	private Vector2 from = new Vector2();
	private Vector2 to = new Vector2();
	private boolean grounded = false;
	private float rayLength = 1;
	
	
	@Override
	public void init(Entity entity) {
		pc = Mapper.PHYSICS_COMPONENT_COMPONENT_MAPPER.get(entity);
		body = pc.body;
		world = body.getWorld();
		transform = Mapper.TRANSFORM_COMPONENT_COMPONENT_MAPPER.get(entity);
	}
	
	@Override
	public void update(float delta) {
		pc.updateTransform();
		Vector2 move = body.linVelLoc;
		grounded = isOnGround();
		if(grounded && move.y < 0) move.y = -0.01f;
		else move.y -= gravity;
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			move.x = speed;
		}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			move.x = -speed;
		}else {
			move.x = 0;
		}
		if(grounded && Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			move.y = jumpPower;
			justJumped = true;
		}
		if(justJumped && !grounded && Gdx.input.isKeyPressed(Input.Keys.UP) && move.y > 0){
			move.y += jumpMulti;
		}
		if(!Gdx.input.isKeyPressed(Input.Keys.UP)){
			justJumped = false;
		}
		body.setLinearVelocity(move);
	}
	
	public boolean isOnGround(){
		final boolean[] b = {false};
		RayCastCallback callback = (fixture, point, normal, fraction) -> {
			RayCastRender.requestRender(new RayCastRenderRequest(new Vector2(from), new Vector2(to),new Vector2(point),new Vector2(normal)));
			if(fixture.getFilterData().categoryBits == Constants.WORLD || fixture.getFilterData().categoryBits == Constants.ONE_WAY_PLATFORM) {
				if (!b[0]) {
					b[0] = true;
				}
				return 0;
			}
			return 1;
		};
		
		Vector2 pos = body.getPosition();
		from.set(pos.x - transform.width/2, pos.y - transform.height/2);
		to.set(from.x, from.y - rayLength);
		
		world.rayCast(callback, from, to);
		if(!b[0]){
			RayCastRender.requestRender(new RayCastRenderRequest(new Vector2(from), new Vector2(to), null, null));
		}
		from.set(pos.x + transform.width/2, pos.y - transform.height/2);
		to.set(from.x, from.y - rayLength);
		
		world.rayCast(callback, from, to);
		if(!b[0]) {
			RayCastRender.requestRender(new RayCastRenderRequest(new Vector2(from), new Vector2(to), null, null));
		}
		return b[0];
	}
	
	@Override
	public void dispose() {
	
	}
	
	
	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		if(fixtureA.getFilterData().categoryBits == Constants.ONE_WAY_PLATFORM || fixtureB.getFilterData().categoryBits == Constants.ONE_WAY_PLATFORM){
			if(body.linVelLoc.y > 0) return false;
		}
		return true;
	}
}
