package me.potaytoprograms.pixi.test.scenes;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import me.potaytoprograms.pixi.p2d.ashley.systems.RenderSystem2D;
import me.potaytoprograms.pixi.p2d.box2d.Box2dUtil;
import me.potaytoprograms.pixi.p2d.box2d.PhysicsSystem;
import me.potaytoprograms.pixi.p2d.box2d.RayCastRender;
import me.potaytoprograms.pixi.shared.ashley.systems.ScriptSystem;
import me.potaytoprograms.pixi.shared.scene.IScene;
import me.potaytoprograms.pixi.shared.scene.SceneManager;
import me.potaytoprograms.pixi.test.entities.Player;
import me.potaytoprograms.pixi.test.util.Constants;

public class GameScene implements IScene {
	
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	private SceneManager sceneManager;
	private Engine engine;
	private World world;
	private ScriptSystem scriptSystem;
	private RenderSystem2D renderSystem2D;
	private SpriteBatch batch;
	private PhysicsSystem physicsSystem;
	private TmxMapLoader tmxMapLoader;
	private TiledMap tiledMap;
	
	public GameScene(SpriteBatch batch){
		this.batch = batch;
	}
	
	@Override
	public void init(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		engine = sceneManager.engine;
		physicsSystem = new PhysicsSystem();
		world = new World(new Vector2(0,0),true);
		camera = new OrthographicCamera();
		scriptSystem = new ScriptSystem(0);
		renderSystem2D = new RenderSystem2D(1, batch, camera);
		
		world.setContactFilter(physicsSystem);
		world.setContactListener(physicsSystem);
		
		engine.addSystem(scriptSystem);
		engine.addSystem(renderSystem2D);
		
		camera.setToOrtho(false,800/Constants.PPM, 480/Constants.PPM);
		b2dr = new Box2DDebugRenderer();
		
		Player player = new Player(world, 200, 80, camera);
		engine.addEntity(player);
		
		Box2dUtil.createBox(world, 400/Constants.PPM, 20/Constants.PPM, 480/2/Constants.PPM,40/2/Constants.PPM, 0,0,1,
				Constants.WORLD,Constants.PLAYER, (short) 0,true,true, null);
		
		Box2dUtil.createBox(world,400/Constants.PPM,80/Constants.PPM,20/Constants.PPM, 5/Constants.PPM,0,0,1,Constants.ONE_WAY_PLATFORM, Constants.PLAYER, (short) 0, true, true, null);
		
		tmxMapLoader = new TmxMapLoader();
		tiledMap = tmxMapLoader.load("untitled.tmx");
		Box2dUtil.parseTiledMapObjectLayer(tiledMap.getLayers().get("Object Layer 1"), world, null);
	}
	
	@Override
	public void update(float delta){
		world.step(1.0f/60.0f, 6, 2);
		engine.update(delta);
		b2dr.render(world,camera.combined);
		RayCastRender.render(camera.combined,true,true);
	}
	
	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
	}
}
