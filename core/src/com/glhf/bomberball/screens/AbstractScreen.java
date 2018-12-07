package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.glhf.bomberball.interfaces.AbstractInterface;
import com.glhf.bomberball.menu.InputHandler;

public abstract class AbstractScreen implements Screen {

    protected InputHandler inputHandler;

    private AbstractInterface main_interface;

    public AbstractScreen() {
        inputHandler = new InputHandler();
    }

    public void setInterface(AbstractInterface I) {
        main_interface = I;
        main_interface.addListener(inputHandler);
        Gdx.input.setInputProcessor(main_interface);
    }

    public void registerActionsHandlers() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (main_interface != null) {
            main_interface.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        main_interface.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void show() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}
