package com.glhf.bomberball.menu;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State implements InputProcessor{
    protected SpriteBatch batch;

    //Attributes
    private String state_name;

    //Constructors
    public State(String e){
        state_name = e;
        batch = new SpriteBatch();
    }

    //Methods
    public State getState(){
        return this;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String new_state){
        state_name = new_state;
    }

    public abstract void draw();

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
}
