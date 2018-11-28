package com.glhf.bomberball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.menu.State;
import com.glhf.bomberball.menu.StateGame;

import java.util.HashMap;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private HashMap<String, Texture> textures;
	private State state;

    private DebugRenderer debugRenderer;

	@Override
	public void create () {
		Textures.loadTextures();
		batch = new SpriteBatch();
		font = new BitmapFont();
		state = new StateGame().loadMaze("classic_maze_1.json");
		font.setColor(Color.RED);

        debugRenderer = new DebugRenderer(batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		state.draw();
		//debugRenderer.drawLines(2);
		font.draw(batch, "Natan il est trop beau :)", 200, 200);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
