package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.glhf.bomberball.Constants;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class State implements InputProcessor {
    protected Stage stage;
    protected SpriteBatch batch; //to be remove
    //Attributes
    private String state_name;

    //Constructors
    public State(String e){
        state_name = e;
        stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
        batch = new SpriteBatch();
    }

    //Methods
    public State getState(){
        return this;
    }

    public void draw() {
        stage.draw();
    }

    public void draw(){
        stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public void setInputProcessor(){
        Gdx.input.setInputProcessor(stage);
    }
}
