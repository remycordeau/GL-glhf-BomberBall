package com.glhf.bomberball.TMP;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ScreenChangeListener extends ClickListener {
    Class<? extends Screen> screen_class;

    public <T extends Screen> ScreenChangeListener(Class<T> screen_class) {
        this.screen_class = screen_class;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        try {
            GameTMP.changeScreen(screen_class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}