package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.*;

public class MainMenuUI extends Table {

    public MainMenuUI() {
        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.25f));
        this.padRight(Value.percentWidth(0.25f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));

        addButtons();
    }

    private void addButtons()
    {
        TextButton b;
        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.15f);

        b = new TextButton("Solo", Graphics.GUI.getSkin());
        b.addListener(new ScreenChangeListener(SoloMenuScreen.class));
        this.add(b).growX().space(spacing).row();

        b = new TextButton("Multiplayer", skin);
        b.addListener(new ScreenChangeListener(MultiMenuScreen.class));
        this.add(b).growX().space(spacing).row();

        b = new TextButton("Map Editor", Graphics.GUI.getSkin());
        b.addListener(new ScreenChangeListener(MapEditorMenuScreen.class));
        this.add(b).growX().space(spacing).row();

        b = new TextButton("Settings", Graphics.GUI.getSkin());
        b.addListener(new ScreenChangeListener(SettingsMenuScreen.class));
        this.add(b).growX().space(spacing).row();

        b = new TextButton("Quit", Graphics.GUI.getSkin());
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        this.add(b).growX();
    }
}
