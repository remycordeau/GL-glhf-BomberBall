package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.glhf.bomberball.Constants;

public abstract class State {
    protected Stage stage;
    InputHandler inputHandler = new InputHandler();

    //Constructors
    public State(){
        stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
        stage.addListener(inputHandler);
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
