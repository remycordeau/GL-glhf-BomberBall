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
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.util.HashMap;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private HashMap<String, Texture> textures;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		loadTextures();
		font.setColor(Color.RED);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Natan il est trop beau", 200, 200);
        font.draw(batch, "Vincent trop stock", 500, 500);
        batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}


	private void loadTextures() {
		textures = new HashMap<String, Texture>();
		loadTextures("core/assets/");
	}
	private void loadTextures(String path) {
		textures = new HashMap<String, Texture>();
		File f = new File(path);
		File[] files = f.listFiles();
		assert files != null;// le fichier f doit être un dossier
		for(File file : files) {
			if (file.isDirectory()){
				loadTextures(path + file.getName() + "/");
				continue;
			}
			String stringId = file.getName().replace(".png", "");
			System.out.println(stringId);
			textures.put(stringId, new Texture(Gdx.files.internal(path + file.getName())));
		}
	}
}
