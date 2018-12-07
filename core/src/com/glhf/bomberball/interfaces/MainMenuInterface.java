package com.glhf.bomberball.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.ScreenChangeListener;
import com.glhf.bomberball.screens.GameMultiScreen;

public class MainMenuInterface extends AbstractInterface {

    Table table;

    public MainMenuInterface() {
        super();
        table = new Table();
        table.setFillParent(true);

        addButtons();

        this.addActor(table);
    }

    private void addButtons()
    {
        TextButton b;
        Skin skin = Graphics.GUI.getSkin();

        b = new TextButton("Multiplayer", skin);
        b.addListener(new ScreenChangeListener(GameMultiScreen.class));
        table.add(b).grow().row();

        b = new TextButton("Quit", Graphics.GUI.getSkin());
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(b).grow();
    }
}
