package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.MultiMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;
import com.glhf.bomberball.screens.GameMultiScreen;

public class MainMenuUI extends Table {

    public MainMenuUI() {
        this.setFillParent(true);

        addButtons();
    }

    private void addButtons()
    {
        TextButton b;
        Skin skin = Graphics.GUI.getSkin();

        b = new TextButton("Solo", Graphics.GUI.getSkin());
        this.add(b).grow().row();

        b = new TextButton("Multiplayer", skin);
        b.addListener(new ScreenChangeListener(MultiMenuScreen.class));
        this.add(b).grow().row();

        b = new TextButton("Map Editor", Graphics.GUI.getSkin());
        this.add(b).grow().row();

        b = new TextButton("Settings", Graphics.GUI.getSkin());
        this.add(b).grow().row();

        b = new TextButton("Quit", Graphics.GUI.getSkin());
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        this.add(b).grow();
    }
}
