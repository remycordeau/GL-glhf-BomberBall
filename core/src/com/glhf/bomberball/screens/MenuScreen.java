package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public abstract class MenuScreen extends AbstractScreen {
    protected VerticalGroup centerButtons;

    public MenuScreen() {

        centerButtons = new VerticalGroup();
        centerButtons.setFillParent(true);
        centerButtons.center();
        addUI(centerButtons);
    }
}