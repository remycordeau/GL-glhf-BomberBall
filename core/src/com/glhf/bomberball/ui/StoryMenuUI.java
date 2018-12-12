package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.InfiniteMenuScreen;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;
import com.glhf.bomberball.screens.StoryMenuScreen;

public class StoryMenuUI extends Table {

    private TextButton back_button;

    public StoryMenuUI(){

        super();

        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.25f));
        this.padRight(Value.percentWidth(0.25f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));

        addButtons();
    }

    private void addButtons() {

        Value spacing = Value.percentHeight(0.15f);

        back_button = new TextButton("Back to main menu",Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(back_button).growX().space(spacing).row();
        }
}


