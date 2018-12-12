package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;

public class SoloMenuScreen extends MenuScreen {

    private TextButton infinite_button, story_button, back_button;

    public SoloMenuScreen()
    {
        super();
        initializeButtons();
    }

    private void initializeButtons() {

        story_button = new TextButton("Story Mode", Graphics.GUI.getSkin());
        story_button.addListener(new ScreenChangeListener(StoryMenuScreen.class));
        centerButtons.addActor(story_button);

        infinite_button = new TextButton("Infinite Mode", Graphics.GUI.getSkin());
        infinite_button.addListener(new ScreenChangeListener(InfiniteModeScreen.class));
        centerButtons.addActor(infinite_button);

        centerButtons.space(20);
        back_button = new TextButton("Back to main menu",Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        centerButtons.addActor(back_button);
    }

}
