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

        TexturePacker.process("core/assets/sprites", "core/packedImages", "pack");

		new LwjglApplication(new Game(), config);
	}
}
