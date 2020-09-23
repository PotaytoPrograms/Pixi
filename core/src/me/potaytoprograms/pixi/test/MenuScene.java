package me.potaytoprograms.pixi.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import me.potaytoprograms.pixi.shared.scene.IScene;
import me.potaytoprograms.pixi.shared.scene.SceneManager;
import me.potaytoprograms.pixi.test.testgame2d.GameScene;
import me.potaytoprograms.pixi.test.testgame3d.GameScene3D;

import java.util.HashMap;

public class MenuScene implements IScene {
	
	private final ModelBatch mBatch;
	private SceneManager sceneManager;
	private HashMap<String, IScene> tests;
	private Stage stage;
	private final SpriteBatch batch;
	private VerticalGroup group;
	private ShapeRenderer shapeRenderer;
	
	public MenuScene(SpriteBatch batch, ModelBatch mBatch){
		this.batch = batch;
		this.mBatch = mBatch;
	}
	
	@Override
	public void init(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		shapeRenderer = new ShapeRenderer();
		stage = new Stage(new FitViewport(800, 480), batch);
		tests = new HashMap();
		
		Gdx.input.setInputProcessor(stage);
		
		addScenes();
		
		group = new VerticalGroup();
		group.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		group.debug();
		
		VisTextField field = new VisTextField();
		field.setMessageText("search");
		field.setPosition((Gdx.graphics.getWidth()/2)-(field.getWidth()/2), Gdx.graphics.getHeight()-field.getHeight());
		field.addListener(new InputListener() {
			
			Array<VisTextButton> buttons;
			
			@Override
			public boolean keyTyped(InputEvent event, char character) {
				if(buttons == null){
					init();
				}
				
				search();
				
				return super.keyTyped(event, character);
			}
			
			private void init(){
				buttons = new Array<>();
				for(Actor actor : group.getChildren()){
					if(actor instanceof VisTextButton){
						VisTextButton button = (VisTextButton) actor;
						buttons.add(button);
					}
				}
			}
			
			private void search(){
				for(Actor actor : group.getChildren()){
					group.removeActor(actor);
				}
				for(int i = 0; i < buttons.size; i++){
					VisTextButton button = buttons.get(i);
					if(button.getText().toString().contains(field.getText()))
						group.addActor(button);
				}
			}
		});
		stage.addActor(field);
		
		for(String name : tests.keySet()){
			VisTextButton button = new VisTextButton(name);
			button.pad(10);
			button.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					sceneManager.loadScene(tests.get(name));
				}
			});
			group.addActor(button);
		}
		
		stage.addActor(group);
	}
	
	public void addScenes(){
		tests.put("2D", new GameScene(batch));
		tests.put("3D", new GameScene3D(mBatch));
	}
	
	@Override
	public void update(float delta) {
		shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		group.drawDebug(shapeRenderer);
		shapeRenderer.end();
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		shapeRenderer.dispose();
	}
}