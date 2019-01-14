package com.glhf.bomberball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.glhf.bomberball.utils.Constants;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.config.AppConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		AppConfig app_config = AppConfig.get();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = app_config.resolution.width;
		config.height = app_config.resolution.height;
		config.fullscreen = app_config.fullscreen;
		config.resizable = true;


//        TexturePacker.process(Constants.PATH_GRAPHICS+"animations", Constants.PATH_PACKS, "pack_animations");
//		TexturePacker.process(Constants.PATH_GRAPHICS+"sprites", Constants.PATH_PACKS, "pack_sprites");
//        TexturePacker.process(Constants.PATH_GRAPHICS+"gui", Constants.PATH_PACKS, "pack_gui");


		new LwjglApplication(new Bomberball(), config);
	}
}
