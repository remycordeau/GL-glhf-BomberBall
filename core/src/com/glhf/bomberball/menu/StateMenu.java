package com.glhf.bomberball.menu;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public abstract class StateMenu extends State{
    protected VerticalGroup centerButtons;

    public StateMenu() {

        centerButtons = new VerticalGroup();
        centerButtons.setFillParent(true);
        centerButtons.center();
        stage.addActor(centerButtons);
    }
}