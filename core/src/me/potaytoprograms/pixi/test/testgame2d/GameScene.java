package me.potaytoprograms.pixi.test.testgame2d;

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
import me.potaytoprograms.pixi.p2d.box2d.RayCastRender;
import me.potaytoprograms.pixi.shared.ashley.systems.ScriptSystem;
import me.potaytoprograms.pixi.shared.scene.IScene;
import me.potaytoprograms.pixi.shared.scene.SceneManager;
import me.potaytoprograms.pixi.test.Constants;

public class GameScene implements IScene {
	
	private SceneManager sceneManager;
	private Engine engine;
	private OrthographicCamera camera;
	private World world;
	private RenderSystem2D renderSystem2D;
	private ScriptSystem scriptSystem;
	private final SpriteBatch batch;
	private Box2DDebugRenderer b2dr;
	private TiledMap tiledMap;
	private TmxMapLoader tmxMapLoader;
	
	public GameScene(SpriteBatch batch){
		this.batch = batch;
	}
	
	@Override
	public void init(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		engine = sceneManager.engine;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800/ Constants.PPM, 480/Constants.PPM);
		world = new World(new Vector2(), true);
		b2dr = new Box2DDebugRenderer();
		renderSystem2D = new RenderSystem2D(1, batch, camera);
		scriptSystem = new ScriptSystem(0);
		tmxMapLoader = new TmxMapLoader();
		tiledMap = tmxMapLoader.load("untitled.tmx");
		
		Box2dUtil.parseTiledMapLayer(tiledMap.getLayers().get("Object Layer 1"), world, 1.0f/Constants.PPM,null);
		
		engine.addSystem(renderSystem2D);
		engine.addSystem(scriptSystem);
		
		engine.addEntity(new Player(10/Constants.PPM, 100/Constants.PPM, world, camera));
	}
	
	@Override
	public void update(float delta) {
		world.step(10f/60.0f, 6, 2);
		engine.update(delta);
		b2dr.render(world, camera.combined);
		RayCastRender.render(camera.combined, true, true);
	}
	
	@Override
	public void dispose() {
		b2dr.dispose();
		world.dispose();
		tiledMap.dispose();
	}
}
