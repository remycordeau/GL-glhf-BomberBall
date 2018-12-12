package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.ui.SoloMenuUI;

public class SoloMenuScreen extends MenuScreen {

    public SoloMenuScreen(){
        super();
        addUI(new SoloMenuUI());
    }
}
