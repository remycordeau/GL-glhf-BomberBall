package com.glhf.bomberball.TMP;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Graphics;

public class GameTMP extends Game {

    public static GameTMP instance;

    @Override
    public void create() {
        instance = this;
        Graphics.load();
        changeScreen(new MainMenuScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    public static void changeScreen(Screen screen) {
        instance.setScreen(screen);
    }
}
