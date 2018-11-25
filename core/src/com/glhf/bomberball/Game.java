package com.glhf.bomberball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		Textures.loadTextures();
		font.setColor(Color.RED);
		//String t = new Maze().toJson();
		//Maze tt = Maze.fromJson(t);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Natan il est trop beau", 200, 200);
        font.draw(batch, "Vincent trop stock", 500, 500);
		font.draw(batch, "OBIIIIIIIIIIIIIIIIIIIIIIII", 300, 300);
        batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

}
