package com.glhf.bomberball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import javax.xml.soap.Text;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.APP_WIDTH;
		config.height = Constants.APP_HEIGHT;
		config.resizable = false;

        TexturePacker.process(Constants.PATH_GRAPHICS+"animations", Constants.PATH_PACKS, "pack_animations");
		TexturePacker.process(Constants.PATH_GRAPHICS+"sprites", Constants.PATH_PACKS, "pack_sprites");
        TexturePacker.process(Constants.PATH_GRAPHICS+"gui", Constants.PATH_PACKS, "pack_gui");

		new LwjglApplication(new Game(), config);
	}
}
