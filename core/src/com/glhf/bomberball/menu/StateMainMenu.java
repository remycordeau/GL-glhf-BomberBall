package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.menu.listener.SetStateListener;

import java.awt.*;

public class StateMainMenu extends StateMenu{

    public StateMainMenu()
    {
        super();
        initializeButtons();
    }

    public void initializeButtons(){
        TextButton textButton = new TextButton("Solo", Graphics.GUI.getSkin());
        textButton.addListener(new SetStateListener(new StateSoloMenu(this)));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Multiplayer", Graphics.GUI.getSkin());
        textButton.addListener(new SetStateListener(new StateMultiMenu(this)));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Map Editor", Graphics.GUI.getSkin());
        textButton.addListener(new SetStateListener(new StateMapEditorMenu(this)));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Settings", Graphics.GUI.getSkin());
        textButton.addListener(new SetStateListener(new StateSettingsMenu()));
        centerButtons.addActor(textButton);

        textButton = new TextButton("Quit", Graphics.GUI.getSkin());
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        centerButtons.addActor(textButton);
    }
}
