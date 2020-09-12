package me.potaytoprograms.pixi.p2d.box2d;

import com.badlogic.gdx.physics.box2d.*;

public class PhysicsSystem implements ContactFilter, ContactListener {
	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		boolean a = false,b = false;
		if(fixtureA.getUserData() instanceof IContactFilter2D || fixtureB.getUserData() instanceof IContactFilter2D){
			if (fixtureA.getUserData() instanceof IContactFilter2D) {
				a = ((IContactFilter2D) fixtureA.getUserData()).shouldCollide(fixtureA, fixtureB);
			}
			if (fixtureB.getUserData() instanceof IContactFilter2D) {
				b = ((IContactFilter2D) fixtureB.getUserData()).shouldCollide(fixtureA, fixtureB);
			}
			return a || b;
		}else return true;
	}
	
	@Override
	public void beginContact(Contact contact) {
		if(contact.getFixtureA().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureA().getUserData()).beginContact(contact);
		}
		if(contact.getFixtureB().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureB().getUserData()).beginContact(contact);
		}
	}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		if(contact.getFixtureA().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureA().getUserData()).preSolve(contact, oldManifold);
		}
		if(contact.getFixtureB().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureB().getUserData()).preSolve(contact, oldManifold);
		}
	}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		if(contact.getFixtureA().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureA().getUserData()).postSolve(contact, impulse);
		}
		if(contact.getFixtureB().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureB().getUserData()).postSolve(contact, impulse);
		}
	}
	
	@Override
	public void endContact(Contact contact) {
		if(contact.getFixtureA().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureA().getUserData()).endContact(contact);
		}
		if(contact.getFixtureB().getUserData() instanceof IContact2D){
			((IContact2D)contact.getFixtureB().getUserData()).endContact(contact);
		}
	}
}
