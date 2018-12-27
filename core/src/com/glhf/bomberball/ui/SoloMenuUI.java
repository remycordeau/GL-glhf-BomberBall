package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.InfiniteModeScreen;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;
import com.glhf.bomberball.screens.StoryMenuScreen;

public class SoloMenuUI extends Table {

    private TextButton infinite_button, story_button, back_button;

    public SoloMenuUI(){
        super();

        this.setFillParent(true);
        initializeButtons();
    }

    private void initializeButtons() {

        story_button = new TextButton("Story Mode", Graphics.GUI.getSkin());
        story_button.addListener(new ScreenChangeListener(StoryMenuScreen.class));
        this.add(story_button).expandX().row();

        infinite_button = new TextButton("Infinite Mode", Graphics.GUI.getSkin());
        infinite_button.addListener(new ScreenChangeListener(InfiniteModeScreen.class));
        this.add(infinite_button).spaceTop(Value.percentHeight(1f)).row();

        back_button = new TextButton("Back to main menu",Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(back_button).spaceTop(Value.percentHeight(3f)).row();
    }

}
