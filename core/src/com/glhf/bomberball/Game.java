package com.glhf.bomberball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.util.HashMap;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture test;
	private TextureRegion region;
	private HashMap<String, Texture> textures;
//	private State state;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		Textures.loadTextures();
		//state = StartState.get();
        test = Textures.get("badlogic");
		region = new TextureRegion(test, 0, 0, 256, 256);
		font.setColor(Color.RED);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 255);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		//state.draw(batch);
		font.draw(batch, "Natan il est trop beau", 200, 200);
		batch.draw(region, 300, 200);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
