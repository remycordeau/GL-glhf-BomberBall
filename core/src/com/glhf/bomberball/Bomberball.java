package com.glhf.bomberball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.screens.MainMenuScreen;

public class Bomberball extends Game {

	public static Bomberball instance;
    public static float time_elapsed;
    public static boolean debug = true;

	@Override
	public void create() {
		instance = this;
		Bomberball.time_elapsed = 0;
		Graphics.load();

		changeScreen(new MainMenuScreen());
	}

	@Override
	public void render() {
		super.render(); // Renders current screen
		time_elapsed += Gdx.graphics.getDeltaTime();
	}

	public static void changeScreen(Screen screen) {
		instance.setScreen(screen);
	}
}
