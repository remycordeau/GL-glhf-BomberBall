package com.glhf.bomberball.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.glhf.bomberball.Bomberball;

public abstract class AbstractInterface extends Stage {

    public AbstractInterface() {
        super(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.setDebugAll(Bomberball.debug);
    }
}
