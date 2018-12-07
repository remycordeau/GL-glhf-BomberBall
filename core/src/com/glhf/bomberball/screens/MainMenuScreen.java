package com.glhf.bomberball.screens;

import com.glhf.bomberball.interfaces.AbstractInterface;
import com.glhf.bomberball.interfaces.MainMenuInterface;

public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen() {
        super();
        setInterface(new MainMenuInterface());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
