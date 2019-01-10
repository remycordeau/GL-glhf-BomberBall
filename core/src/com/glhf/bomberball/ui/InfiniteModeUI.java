package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.screens.*;

public class InfiniteModeUI extends Table {

    private InfiniteModeScreen screen;
    private Label label;
    private int highscore;
    private GameSoloConfig config;
    private Maze maze;

    public InfiniteModeUI() {
        config = new GameSoloConfig();
        maze = Maze.importMaze("maze_" + 1);
        highscore = config.highscore;

        this.setFillParent(true);
        this.addButtons();
    }

    private void addButtons(){

        //Title
        label = new Label(Translator.translate("Mode Infini"), Graphics.GUI.getSkin(), "Title");
        label.setAlignment(Align.center);
        label.setFontScale(1.5f, 1.5f);
        this.add(label).padBottom(Value.percentHeight(0.8f)).row();

        //CheckBoxes
        CheckBox box1;
        CheckBox box2;
        CheckBox box3;
        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.5f);

        box1 = new CheckBox("Sans Bonus", skin);
        box2 = new CheckBox("Nombre de tours limite", skin);
        box3 = new CheckBox("Cartes aléatoires", skin);

        TextButton back;
        back = new TextButton(Translator.translate("Retour"), skin);
        back.addListener(new ScreenChangeListener(SoloMenuScreen.class));

        TextButton play;
        play = new TextButton(Translator.translate("Play"), skin);
        play.addListener(new ScreenChangeListener(SoloMenuScreen.class));

        Label lab;
        lab = new Label(Translator.translate("Highscore :") + this.highscore, skin, "default");

        HorizontalGroup horizontal = new HorizontalGroup();
        horizontal.align(Align.center);
        horizontal.addActor(back);
        horizontal.space(25);
        horizontal.addActor(play);
        horizontal.space(25);
        horizontal.addActor(lab);

        //Adding to the screen1
        this.add(box1).space(spacing).row();
        this.add(box2).space(spacing).row();
        this.add(box3).space(spacing).row();

        this.add(horizontal).padTop(Value.percentHeight(0.8f));

    }
}
