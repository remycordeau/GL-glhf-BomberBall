package com.glhf.bomberball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.menu.InputHandler;

public abstract class AbstractScreen implements Screen {

    protected InputHandler inputHandler;
    private Stage stage;

    public AbstractScreen() {
        inputHandler = new InputHandler();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage.setDebugAll(Bomberball.debug);
        stage.addListener(inputHandler);
        registerActionsHandlers();
    }

    protected void registerActionsHandlers() { }

    public void addUI(Actor ui) {
        stage.addActor(ui);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Graphics.GUI.scaleFont();
    }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
