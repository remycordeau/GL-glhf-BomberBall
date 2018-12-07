package com.glhf.bomberball.TMP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.glhf.bomberball.Graphics;

public class MenuStage extends Stage {

    Table table;

    public MenuStage() {
        super(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        table = new Table();
        table.setFillParent(true);

        addButtons();

        table.setDebug(true, true);
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
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        table.add(b).grow();
    }
}
