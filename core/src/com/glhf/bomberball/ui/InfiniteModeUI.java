package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.config.AppConfig;
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
        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.25f));
        this.padRight(Value.percentWidth(0.25f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));

        config = new GameSoloConfig();
        maze = Maze.importMaze("maze_" + 1);
        highscore = config.highscore;

        this.setFillParent(true);
        this.addButtons();
    }

    private void addButtons(){

        //Title
        label = new Label(Translator.translate("Infinite Mode"), Graphics.GUI.getSkin(), "Title");
        label.setAlignment(Align.center);
        label.setFontScale(1.7f, 1.7f);
        this.add(label).row();


        //CheckBoxes
        CheckBox box1;
        CheckBox box2;
        CheckBox box3;
        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.5f);

        box1 = new CheckBox("Sans Bonus", skin);
        box2 = new CheckBox("Nombre de tours limite", skin);
        box3 = new CheckBox("Cartes aléatoires", skin);


        //Settings CheckBoxes
        box1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(((CheckBox)actor).isChecked()){

                }
                else{

                }
                /*com.badlogic.gdx.Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
                AppConfig config = AppConfig.get();
                if(((CheckBox)actor).isChecked())
                    Gdx.graphics.setFullscreenMode(displayMode);
                else
                    Bomberball.resizeWindow(config.resolution);
                config.fullscreen = ((CheckBox)actor).isChecked();
                config.exportConfig();*/
            }
        });


        //Other buttons
        TextButton back;
        back = new TextButton(Translator.translate("Back"), skin);
        back.addListener(new ScreenChangeListener(SoloMenuScreen.class));

        TextButton play;
        play = new TextButton(Translator.translate("Play"), skin);
        play.addListener(new ScreenChangeListener(SoloMenuScreen.class));


        //Current Highscore
        Label lab;
        lab = new Label(Translator.translate("Highscore :") + this.highscore, skin, "default");

        HorizontalGroup horizontal = new HorizontalGroup();
        horizontal.align(Align.center);
        horizontal.addActor(back);
        horizontal.space(25);
        horizontal.addActor(play);
        horizontal.space(25);
        horizontal.addActor(lab);

        //Adding to the screen
        this.add(box1).space(spacing).row();
        this.add(box2).space(spacing).row();
        this.add(box3).space(spacing).row();

        this.add(horizontal).padTop(Value.percentHeight(0.8f));

    }
}
