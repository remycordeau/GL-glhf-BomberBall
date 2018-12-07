package com.glhf.bomberball.menu.listener;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.menu.State;

public class SetStateListener extends ChangeListener {
    private State state;
    public SetStateListener(State state){
        this.state = state;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        Bomberball.setState(state);
    }
}