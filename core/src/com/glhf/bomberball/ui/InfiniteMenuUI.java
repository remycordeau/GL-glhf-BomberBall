package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.utils.ScreenChangeListener;

public class InfiniteMenuUI extends Table {

     private TextButton back_button;

    public InfiniteMenuUI(){

        super();

        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.25f));
        this.padRight(Value.percentWidth(0.25f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));

        initializeButtons();

    }

    private void initializeButtons() {

        back_button = new AudioButton(Translator.translate("Back to main menu"), Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(back_button).row();
    }
}
