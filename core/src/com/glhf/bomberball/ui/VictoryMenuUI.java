package com.glhf.bomberball.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.Audio;
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
            TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture("core/assets/graphics/background/VictoryMenu.png")));
            this.setBackground(texture);
        } else {
            this.add(new Label(Translator.translate("VICTOIRE !"), Graphics.GUI.getSkin(), "default")).row();
            AnimationActor player_animation = new AnimationActor(player.getAnimation());
            player_animation.mustMove(true);
            this.add(player_animation).grow().row();
            TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture("core/assets/graphics/background/VictoryMenu.png")));
            this.setBackground(texture);
            Audio.VICTORY.play();
        }

        addButtons();
    }

    private void addButtons() {
        TextButton b;
        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.20f);

        b = new TextButton(Translator.translate("Replay"), skin);
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

        b = new TextButton(Translator.translate("Back to main menu"), skin);
        b.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(b).growX();
    }
}
