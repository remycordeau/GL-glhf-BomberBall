package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Bomberball;

public class ScreenChangeListener extends ChangeListener {
    Class<? extends AbstractScreen> screen_class;

    public <T extends AbstractScreen> ScreenChangeListener(Class<T> screen_class) {
        this.screen_class = screen_class;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        try {
            Bomberball.changeScreen(screen_class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}