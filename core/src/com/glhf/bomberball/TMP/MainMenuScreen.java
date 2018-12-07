package com.glhf.bomberball.TMP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainMenuScreen implements Screen {

    private Stage stage;

    public MainMenuScreen() {
        stage = new MenuStage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(34/255f, 34/255f, 34/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}
