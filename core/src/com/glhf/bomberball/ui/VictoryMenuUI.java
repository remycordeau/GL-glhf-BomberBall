package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.MultiMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;


public class VictoryMenuUI extends Table {

    public VictoryMenuUI(Player player) {
        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.35f));
        this.padRight(Value.percentWidth(0.35f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));

        if (player == null) {
            this.add(new Label("égalité ...", Graphics.GUI.getSkin(), "default")).row();
        } else {
            this.add(new Label("VICTOIRE !", Graphics.GUI.getSkin(), "default")).row();
            AnimationActor player_animation = new AnimationActor(player.getAnimation());
            player_animation.mustMove(true);
            this.add(player_animation).grow().row();
        }

        addButtons();
    }

    private void addButtons() {
        TextButton b;
        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.20f);

        b = new TextButton("Rejouer", skin);
        this.add(b).growX().space(spacing).row();

        b = new TextButton("Menu multijoueur", skin);
        b.addListener(new ScreenChangeListener(MultiMenuScreen.class));
        this.add(b).growX().space(spacing).row();

        b = new TextButton("Menu principal", skin);
        b.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(b).growX();
    }
}
