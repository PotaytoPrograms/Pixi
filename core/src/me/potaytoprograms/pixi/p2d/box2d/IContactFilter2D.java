package me.potaytoprograms.pixi.p2d.box2d;

import com.badlogic.gdx.physics.box2d.Fixture;

public interface IContactFilter2D {
	
	boolean shouldCollide(Fixture fixtureA, Fixture fixtureB);
}
