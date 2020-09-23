package me.potaytoprograms.pixi.test.testgame3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import me.potaytoprograms.pixi.shared.scene.IScene;
import me.potaytoprograms.pixi.shared.scene.SceneManager;

public class GameScene3D implements IScene {
	
	private PerspectiveCamera camera;
	private final ModelBatch batch;
	private ModelBuilder builder;
	private Model model;
	private ModelInstance instance;
	private Environment environment;
	private CameraInputController camController;
	
	public GameScene3D(ModelBatch batch){
		this.batch = batch;
	}
	
	@Override
	public void init(SceneManager sceneManager) {
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(new Vector3(10, 10, 10));
		camera.lookAt(new Vector3(0,0,0));
		camera.near = 1f;
		camera.far = 300f;
		camera.update();
		
		camController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(camController);
		
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		builder = new ModelBuilder();
		model = builder.createBox(5, 5, 5, new Material(ColorAttribute.createDiffuse(Color.WHITE)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		instance = new ModelInstance(model);
	}
	
	@Override
	public void update(float delta) {
		camController.update();
		batch.begin(camera);
		batch.render(instance, environment);
		batch.end();
	}
	
	@Override
	public void dispose() {
		model.dispose();
	}
}
