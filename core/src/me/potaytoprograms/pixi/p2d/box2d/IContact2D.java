package me.potaytoprograms.pixi.p2d.box2d;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

public interface IContact2D {
	
	void beginContact(Contact contact);
	void preSolve(Contact contact, Manifold oldManifold);
	void postSolve(Contact contact, ContactImpulse impulse);
	void endContact(Contact contact);
}
