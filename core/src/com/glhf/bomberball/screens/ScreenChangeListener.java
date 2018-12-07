package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Bomberball;

public class ScreenChangeListener extends ClickListener {
    Class<? extends AbstractScreen> screen_class;

    public <T extends AbstractScreen> ScreenChangeListener(Class<T> screen_class) {
        this.screen_class = screen_class;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        try {
            Bomberball.changeScreen(screen_class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}