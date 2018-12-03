package com.glhf.bomberball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.menu.State;
import com.glhf.bomberball.menu.StateGame;
import com.glhf.bomberball.menu.StateGameMulti;
import com.glhf.bomberball.menu.StateMainMenu;

import java.util.HashMap;

public class Game extends ApplicationAdapter {
	private Stage stage;
	private SpriteBatch batch;
	private BitmapFont font;
	private HashMap<String, Texture> textures;
	private static State state;

    private DebugRenderer debugRenderer;

    public static float time_elapsed;

	@Override
	public void create () {
		stage = new Stage(new StretchViewport(720, 480));
		Gdx.input.setInputProcessor(stage);
		Config.load();
		Graphics.load();
		batch = new SpriteBatch();
		font = new BitmapFont();
        setState(new StateMainMenu("MainMenu"));
        //setState(new StateGameMulti("maze_0.json"));
		font.setColor(Color.RED);

        debugRenderer = new DebugRenderer(batch);
        Game.time_elapsed = 0;

		//Sound sound = Gdx.audio.newSound(Gdx.files.internal(Constants.PATH_ASSET+"sounds/musics/test.mp3"));
		//sound.loop();
	}

	public void resize (int width, int height) {
		// See below for what true means.
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime();
	    Game.time_elapsed += delta;
		Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		state.draw();
		//debugRenderer.drawLines(8);
        batch.end();
		stage.act(delta);
        stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		stage.dispose();
	}

	public static void setState(State etat){
		state = etat;
		Gdx.input.setInputProcessor(state);
	}
}
