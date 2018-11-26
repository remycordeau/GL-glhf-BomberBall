package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
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
}
