package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.screens.GameMultiScreen;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.MultiMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;


public class VictoryMenuUI extends Table {
    private int previous_maze_id;

    public VictoryMenuUI(Player player, int maze_id) {
        this.previous_maze_id = maze_id;
        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.35f));
        this.padRight(Value.percentWidth(0.35f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));

        if (player == null) {
            this.add(new Label(Translator.translate("égalité ..."), Graphics.GUI.getSkin(), "default")).row();
        } else {
            this.add(new Label(Translator.translate("VICTOIRE !"), Graphics.GUI.getSkin(), "default")).row();
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

        b = new TextButton(Translator.translate("Rejouer"), skin);
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameMultiScreen(Maze.importMaze("maze_" + previous_maze_id), previous_maze_id));
            }
        });
        this.add(b).growX().space(spacing).row();

        b = new TextButton(Translator.translate("Menu multijoueur"), skin);
        b.addListener(new ScreenChangeListener(MultiMenuScreen.class));
        this.add(b).growX().space(spacing).row();

        b = new TextButton(Translator.translate("Menu principal"), skin);
        b.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(b).growX();
    }
}
