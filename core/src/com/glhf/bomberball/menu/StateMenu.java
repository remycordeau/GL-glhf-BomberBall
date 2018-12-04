package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.glhf.bomberball.Constants;

public abstract class StateMenu extends State{
    protected VerticalGroup centerButtons;

    protected TextButtonStyle textButton_style;

    public StateMenu() {
        textButton_style = new TextButtonStyle();
        textButton_style.font = new BitmapFont(new FileHandle(Constants.PATH_FONTS+"UniDreamLED.fnt"));

        centerButtons = new VerticalGroup();
        centerButtons.setFillParent(true);
        centerButtons.center();

        stage.addActor(centerButtons);
        Gdx.input.setInputProcessor(stage);
    }
}
