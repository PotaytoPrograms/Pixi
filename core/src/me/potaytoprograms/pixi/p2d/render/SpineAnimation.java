package me.potaytoprograms.pixi.p2d.render;


import com.esotericsoftware.spine.*;
import me.potaytoprograms.pixi.p2d.ashley.components.TransformComponent2D;

public class SpineAnimation implements Animation2D{
	
	public Skeleton skeleton;
	public AnimationState animationState;
	
	public SpineAnimation(Skeleton skeleton, AnimationState animationState){
		this.skeleton = skeleton;
		this.animationState = animationState;
	}
	
	public SpineAnimation(SkeletonData skeletonData, AnimationStateData animationStateData){
		skeleton = new Skeleton(skeletonData);
		animationState = new AnimationState(animationStateData);
	}
	
	public void update(float delta, TransformComponent2D transform){
		SkeletonBounds skeletonBounds = new SkeletonBounds();
		animationState.update(delta);
		animationState.apply(skeleton);
		skeleton.getRootBone().setRotation(transform.rotation);
		skeleton.getRootBone().setScale(transform.scaleX, transform.scaleY);
		skeleton.updateWorldTransform();
		skeletonBounds.update(skeleton, false);
		skeleton.setPosition(transform.x - (skeletonBounds.getWidth()/2), transform.y - (skeletonBounds.getHeight()/2));
		skeleton.updateWorldTransform();
	}
	
	@Override
	public void dispose() {
	
	}
}
