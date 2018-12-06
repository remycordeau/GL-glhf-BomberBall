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

public abstract class State {
    protected Stage stage;
    protected SpriteBatch batch; //to be remove

    //Constructors
    public State(){
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

    public void setInputProcessor(){
        Gdx.input.setInputProcessor(stage);
    }
}
