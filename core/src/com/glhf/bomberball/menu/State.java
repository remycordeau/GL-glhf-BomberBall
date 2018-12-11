package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.glhf.bomberball.Constants;

/**
 * abstract class State
 *
 * Extend from State to create a new application state
 *
 * @author ?
 */
public abstract class State
{
    /**
     * scene2d.Stage to draw ui.
     */
    protected Stage stage;

    /**
     * InputHandler to handle key and button inputs
     */
    protected InputHandler inputHandler = new InputHandler();

    /**
     * Default constructor
     */
    public State()
    {
        stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
        stage.addListener(inputHandler);
    }

    /**
     * Draws the state
     * Override this method to draw sprite batches on top of the stage.
     */
    public void draw()
    {
        stage.draw();
    }

    /**
     * Sets the state as an input processor
     */
    public void setInputProcessor()
    {
        Gdx.input.setInputProcessor(stage);
    }
}
