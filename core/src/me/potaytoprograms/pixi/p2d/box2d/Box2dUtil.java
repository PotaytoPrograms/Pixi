package me.potaytoprograms.pixi.p2d.box2d;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import me.potaytoprograms.pixi.shared.ashley.util.IScript;

public class Box2dUtil {
	
	public static Body createBox(
			World world, float x, float y, float width, float height, float density, boolean isStatic,
			boolean fixedRotation){
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x, y);
		if(isStatic) bodyDef.type = BodyDef.BodyType.StaticBody;
		else bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.fixedRotation = fixedRotation;
		Body body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width,height);
		body.createFixture(shape,density);
		shape.dispose();
		return body;
	}
	
	public static Body createBox(
			World world, float x, float y, float width, float height, float friction, float restitution,
			float density, short category, short mask, short group, boolean isStatic, boolean fixedRotation, IScript script){
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x, y);
		if(isStatic) bodyDef.type = BodyDef.BodyType.StaticBody;
		else bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.fixedRotation = fixedRotation;
		Body body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width,height);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		fixtureDef.filter.groupIndex = group;
		fixtureDef.friction = friction;
		fixtureDef.restitution = restitution;
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef).setUserData(script);
		shape.dispose();
		return body;
	}
	
	public static Body createBody(World world, Shape shape, float x, float y, float friction, float restitution, float density, short category, short mask, short group, boolean fixedRotation, boolean isStatic, boolean isSensor, IScript script){
		BodyDef bodyDef = new BodyDef();
		bodyDef.fixedRotation = fixedRotation;
		bodyDef.position.set(x, y);
		if(isStatic) bodyDef.type = BodyDef.BodyType.StaticBody;
		else bodyDef.type = BodyDef.BodyType.DynamicBody;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.density = density;
		fixtureDef.isSensor = isSensor;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		fixtureDef.filter.groupIndex = group;
		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData(script);
		return body;
	}
	
	public static Array<Body> parseTiledMapLayer(MapLayer layer, World world, float scale, float friction, float restitution, float density, short category, short mask, short group, boolean fixedRotation, boolean isStatic, boolean isSensor, IScript script){
		Array<Body> bodies = new Array<>();
		MapObjects objects = layer.getObjects();
		
		for(MapObject object : objects){
			PolygonShape shape = new PolygonShape();
			
			float x = object.getProperties().get("x", float.class);
			float y = object.getProperties().get("y", float.class);
			if(object instanceof PolygonMapObject){
				Polygon polygon = ((PolygonMapObject)object).getPolygon();
				polygon.setScale(scale, scale);
				shape = new PolygonShape();
				shape.set(polygon.getVertices());
			}else if(object instanceof PolylineMapObject){
				Polyline polygon = ((PolylineMapObject)object).getPolyline();
				polygon.setScale(scale,scale);
				shape = new PolygonShape();
				shape.set(polygon.getVertices());
			}else if(object instanceof RectangleMapObject){
				Rectangle polygon = ((RectangleMapObject)object).getRectangle();
				shape = new PolygonShape();
				shape.setAsBox(polygon.width/2, polygon.height/2);
			}
			bodies.add(createBody(world, shape, x, y, friction, restitution, density, category, mask, group, fixedRotation, isStatic, isSensor, script));
		}
		return bodies;
	}
	
	public static Array<Body> parseTiledMapLayer(MapLayer layer, World world, float scale, IScript script){
		Array<Body> bodies = new Array<>();
		MapObjects objects = layer.getObjects();
		for(MapObject object : objects){
			Shape shape = null;
			
			float friction = 0;
			if(object.getProperties().containsKey("friction")) friction = object.getProperties().get("friction", float.class);
			float restitution = 0;
			if(object.getProperties().containsKey("restitution")) restitution = object.getProperties().get("restitution", float.class);
			float density = 0;
			if(object.getProperties().containsKey("density")) density = object.getProperties().get("density", float.class);
			int category = 0;
			if(object.getProperties().containsKey("category")) category = object.getProperties().get("category", int.class);
			int mask = 0;
			if(object.getProperties().containsKey("mask")) mask = object.getProperties().get("mask", int.class);
			int group = 0;
			if(object.getProperties().containsKey("group")) group = object.getProperties().get("group", int.class);
			boolean fixedRotation = false;
			if(object.getProperties().containsKey("fixedRotation")) fixedRotation = object.getProperties().get("fixedRotation", boolean.class);
			boolean isStatic = false;
			if(object.getProperties().containsKey("isStatic")) isStatic = object.getProperties().get("isStatic", boolean.class);
			boolean isSensor = false;
			if(object.getProperties().containsKey("isSensor")) isSensor = object.getProperties().get("isSensor", boolean.class);
			
			float x = object.getProperties().get("x", float.class);
			float y = object.getProperties().get("y", float.class);
			if(object instanceof PolygonMapObject){
				Polygon polygon = ((PolygonMapObject)object).getPolygon();
				polygon.setScale(scale,scale);
				shape = new PolygonShape();
				((PolygonShape) shape).set(polygon.getVertices());
			}else if(object instanceof PolylineMapObject){
				Polyline polygon = ((PolylineMapObject)object).getPolyline();
				polygon.setScale(scale,scale);
				shape = new ChainShape();
				((ChainShape)shape).createChain(polygon.getVertices());
			}else if(object instanceof RectangleMapObject){
				Rectangle polygon = ((RectangleMapObject)object).getRectangle();
				shape = new PolygonShape();
				((PolygonShape)shape).setAsBox(polygon.width/2, polygon.height/2);
			}
			bodies.add(createBody(world, shape, x, y, friction, restitution, density, (short) category, (short) mask, (short) group, fixedRotation, isStatic, isSensor, script));
		}
		return bodies;
	}
}
