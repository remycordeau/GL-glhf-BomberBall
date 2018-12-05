package com.glhf.bomberball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.menu.State;
import com.glhf.bomberball.menu.TitleMenu;

import java.util.HashMap;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private HashMap<String, Texture> textures;
	private static State state;

    private DebugRenderer debugRenderer;

    public static float time_elapsed;

	@Override
	public void create () {
		Config.load();
		Graphics.load();
		batch = new SpriteBatch();
		font = new BitmapFont();
        setState(new TitleMenu());
        //setState(new StateGameMulti("maze_0.json"));
		font.setColor(Color.RED);

        debugRenderer = new DebugRenderer(batch);
        Game.time_elapsed = 0;

		//Sound sound = Gdx.audio.newSound(Gdx.files.internal(Constants.PATH_ASSET+"sounds/musics/test.mp3"));
		//sound.loop();
	}

	@Override
	public void render () {
	    Game.time_elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		state.draw();
		//debugRenderer.drawLines(8);
        batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

	public static void setState(State etat){
		state = etat;
		state.setInputProcessor();
	}
}
