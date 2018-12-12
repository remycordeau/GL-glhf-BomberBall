package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;

public class StoryMenuScreen extends MenuScreen {


    private TextButton back_button;

    public StoryMenuScreen(){
        super();
        initializeButtons();

    }

    private void initializeButtons() {

        back_button = new TextButton("Back to main menu", Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        centerButtons.addActor(back_button);
    }

}


