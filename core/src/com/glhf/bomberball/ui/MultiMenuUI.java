package com.glhf.bomberball.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.audio.Audio;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.GameMultiScreen;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.MultiMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;

public class MultiMenuUI extends Table {

    private MultiMenuScreen screen;
    private MazeDrawer maze_preview;

    //TODO : Comprendre pourquoi lors de la réinitialisation, la table change de forme

    public MultiMenuUI(MultiMenuScreen screen) {
        this.screen = screen;
        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture("core/assets/graphics/background/MultiMenu.png")));
        this.setBackground(texture);
        this.initialize();
    }

    private void initialize()
    {
        System.out.println("Initialization of the Multi Menu");
        this.setFillParent(true);
        this.padTop(Value.percentHeight(0.2f));
        initializeButtons();
        initializeMazePreview();
    }

    private void update()
    {
        System.out.println("Update !");
        this.clear();
        initialize();
    }

    private void initializeMazePreview ()
    {
        maze_preview = new MazeDrawer(screen.maze, 0.25f, 0.75f,  0.5f, 1f, MazeDrawer.Fit.BEST);
        this.add(maze_preview);
    }

    private void initializeButtons(){
        // CREATION OF BUTTONS FOR THE CREATION OF THE MAP
        TextButton nextMapButton = new AudioButton(">", Graphics.GUI.getSkin());
        nextMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextMaze();
                maze_preview.setMaze(screen.maze);
            }
        });

        TextButton previousMapButton = new TextButton("<", Graphics.GUI.getSkin());
        previousMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.previousMaze();
                maze_preview.setMaze(screen.maze);
                Audio.CLICK_BUTTON.play();
            }
        });

        //ADDING THE BUTTONS TO THE TABLE
        Table selectMap = new Table();
        selectMap.add(previousMapButton);
        selectMap.add(nextMapButton).spaceLeft(Value.percentHeight(10f));
        this.add(selectMap).align(Align.center).spaceBottom(Value.percentHeight(0.9f)).grow();
        this.row();

        // BUTTON TO LOAD THE GAME
        Table buttons = new Table();

        TextButton playButton = new AudioButton(Translator.translate("Play"), Graphics.GUI.getSkin());
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameMultiScreen(screen.maze, screen.getMazeId()));
            }
        });
        // BUTTON TO CHOOSE A RANDOM MAZE

        TextButton randomMapButton = new AudioButton(Translator.translate("Random Maze"), Graphics.GUI.getSkin());
        randomMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.randomMaze();
                maze_preview.setMaze(screen.maze);
            }
        });
        // BUTTON TO EXIT THE MENU
        TextButton cancelButton = new AudioButton(Translator.translate("Back"), Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));

        //ADDING THE BUTTONS TO THE TABLE
        Value spacing = Value.percentHeight(0.2f);
        buttons.add(playButton).space(spacing).row();
        buttons.add(randomMapButton).space(spacing).row();
        buttons.add(cancelButton).space(spacing);


        //CREATING A PREVIEW FOR THE PLAYERS


        AnimationActor p1 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(MultiMenuScreen.playable[MultiMenuScreen.p1_id]+"/idle"), Animation.PlayMode.LOOP));
        p1.mustMove(true);
        TextButton Bp1 = new AudioButton("P1", Graphics.GUI.getSkin());
        Bp1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP1();
                update();
            }
        });
        Table Vp1 = new Table();
        Vp1.add(p1).grow();
        Vp1.row();
        Vp1.add(Bp1).growX();

        AnimationActor p2 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(MultiMenuScreen.playable[MultiMenuScreen.p2_id]+"/idle"), Animation.PlayMode.LOOP));
        p2.mustMove(true);
        TextButton Bp2 = new TextButton("P2", Graphics.GUI.getSkin());
        Bp2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP2();
                update();
                Audio.CLICK_BUTTON.play();
            }
        });
        Table Vp2 = new Table();
        Vp2.add(p2).grow();
        Vp2.row();
        Vp2.add(Bp2).growX();

        AnimationActor p3 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(MultiMenuScreen.playable[MultiMenuScreen.p3_id]+"/idle"), Animation.PlayMode.LOOP));
        p3.mustMove(true);
        TextButton Bp3 = new AudioButton("P3", Graphics.GUI.getSkin());
        Bp3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP3();
                update();
            }
        });
        Table Vp3 = new Table();
        Vp3.add(p3).grow();
        Vp3.row();
        Vp3.add(Bp3).growX();

        AnimationActor p4 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(MultiMenuScreen.playable[MultiMenuScreen.p4_id]+"/idle"), Animation.PlayMode.LOOP));
        p4.mustMove(true);
        TextButton Bp4 = new AudioButton("P4", Graphics.GUI.getSkin());
        Bp4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP4();
                update();
            }
        });
        Table Vp4 = new Table();
        Vp4.add(p4).grow();
        Vp4.row();
        Vp4.add(Bp4).growX();

        Table selectPlayer = new Table();
        selectPlayer.add(Vp1).grow();
        selectPlayer.add(Vp2).grow();
        selectPlayer.add(buttons).grow().spaceTop(Value.percentHeight(2f));
        selectPlayer.add(Vp3).grow();
        selectPlayer.add(Vp4).grow();
        this.add(selectPlayer).grow();
    }


}
