package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.ScreenChangeListener;
import com.glhf.bomberball.screens.SettingsMenuScreen;
import com.glhf.bomberball.screens.SoloMenuScreen;

public class InfiniteModeUI extends Table {

    public InfiniteModeUI() {
        this.padBottom(Value.percentHeight(0.25f));
//        this.padLeft(Value.percentWidth(0.25f));
//        this.padRight(Value.percentWidth(0.25f));
        this.setFillParent(true);
        addButtons();
    }

    private void addButtons(){
        TextButton b;
        CheckBox c;

        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.5f);

        b = new TextButton("Menu de personnalisation du mode infini", skin);
        this.add(b).space(spacing).row();

        c = new CheckBox("Sans Bonus", skin);

        this.add(c).space(spacing).row();

        b = new TextButton("Retour", skin);
        b.addListener(new ScreenChangeListener(SoloMenuScreen.class));
        this.add(b).left();
    }
}
