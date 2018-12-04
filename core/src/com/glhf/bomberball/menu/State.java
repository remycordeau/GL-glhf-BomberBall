package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class State {
    protected Stage stage;

    //Constructors
    public State(){
        stage = new Stage();
    }

    //Methods
    public State getState(){
        return this;
    }

    public void draw() {
        stage.draw();
    }
}
