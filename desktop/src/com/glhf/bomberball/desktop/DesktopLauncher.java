package com.glhf.bomberball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.glhf.bomberball.Game;

public class DesktopLauncher {
	static final private int width = 4*200;
	static final private int height = 3*200;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = width;
		config.height = height;
		config.resizable = false;
		new LwjglApplication(new Game(), config);
	}
}
