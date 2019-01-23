package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.config.GameInfiniteConfig;
import com.glhf.bomberball.config.GameStoryConfig;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.screens.*;
import com.glhf.bomberball.utils.ScreenChangeListener;

public class InfiniteMenuUI extends MenuUI {

    private final GameInfiniteConfig config;
    private InfiniteModeScreen screen;
    private int highscore;
    private Maze mazex;

    public InfiniteMenuUI(InfiniteModeScreen screen) {
        this.screen = screen;
        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.25f));
        this.padRight(Value.percentWidth(0.25f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));


        config = GameInfiniteConfig.get();
        mazex = screen.maze;
        highscore = config.highscore;

        this.setFillParent(true);
        this.addButtons();
    }

    private void addButtons(){

        //Title
        Label label = new Label(Translator.translate("Infinite Mode"), Graphics.GUI.getSkin(), "Title");
        label.setAlignment(Align.center);
        label.setFontScale(1.7f, 1.7f);
        this.add(label).row();


        //CheckBoxes
        CheckBox box1;
        CheckBox box2;
        CheckBox box3;
        CheckBox box4;

        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.5f);

        box1 = new CheckBox("Sans Bonus", skin);
        box4 = new CheckBox("Sans caisse", skin);
        box2 = new CheckBox("Nombre de tours limite", skin);
        box3 = new CheckBox("Cartes aléatoires", skin);
        box1.setChecked(!config.bonus_activated);
        box4.setChecked(!config.destructible_wall_available);
        //box2.setChecked(config.nombre_de_tour??);
        //box3.setChecked(config.carte_alea??);
        box2.setDisabled(true);
        box3.setDisabled(true);


        //Settings CheckBoxes
        box1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfiniteConfig config = GameInfiniteConfig.get();
                config.bonus_activated = !((CheckBox)actor).isChecked();
                config.exportConfig();
            }
        });

        box4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfiniteConfig config = GameInfiniteConfig.get();
                config.destructible_wall_available = !((CheckBox)actor).isChecked();
                config.exportConfig();
            }
        });

        //Other buttons
        TextButton back;
        back = new AudioButton(Translator.translate("Back"), skin);
        back.addListener(new ScreenChangeListener(SoloMenuScreen.class));

        TextButton play;
        play = new AudioButton(Translator.translate("Play"), Graphics.GUI.getSkin());
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameInfiniteScreen(screen));
            }
        });


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
        this.add(box4).space(spacing).row();
        this.add(box2).space(spacing).row();
        this.add(box3).space(spacing).row();

        this.add(horizontal).padTop(Value.percentHeight(0.8f));

    }
}
