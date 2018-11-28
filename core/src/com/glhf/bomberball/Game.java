package com.glhf.bomberball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.menu.State;
import com.glhf.bomberball.menu.StateGame;
import com.glhf.bomberball.menu.StateGameMulti;

import java.util.HashMap;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private HashMap<String, Texture> textures;
	private State state;

    private DebugRenderer debugRenderer;

	@Override
	public void create () {
		Graphics.load();
		batch = new SpriteBatch();
		font = new BitmapFont();
		state = new StateGameMulti("classic_maze_1.json");
		font.setColor(Color.RED);
		Gdx.input.setInputProcessor(state);
		Config.load();
		Config.setInput(Input.Keys.UP, "moveUp");
		Config.setInput(Input.Keys.RIGHT, "moveRight");
		Config.setInput(Input.Keys.DOWN, "moveDown");
		Config.setInput(Input.Keys.LEFT, "moveLeft");

        debugRenderer = new DebugRenderer(batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		state.draw();
		//debugRenderer.drawLines(2);
        batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
