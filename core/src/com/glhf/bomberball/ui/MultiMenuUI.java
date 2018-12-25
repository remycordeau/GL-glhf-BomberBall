package com.glhf.bomberball.ui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.GameMultiScreen;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.MultiMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;

public class MultiMenuUI extends Table {

    MultiMenuScreen screen;
    MazeDrawer maze_preview;
    ImageButton p1;
    ImageButton p2;
    ImageButton p3;
    ImageButton p4;


    public MultiMenuUI(MultiMenuScreen screen) {
        this.screen = screen;
        this.setFillParent(true);
        this.initialize();

    }

    public void initialize()
    {
        this.padTop(Value.percentHeight(0.2f));
        initializeButtons();
        initializeMazePreview();
    }

    public void update()
    {
        System.out.println("Update !");
        this.clear();
        this.initialize();}

    public void initializeMazePreview ()
    {
        maze_preview = new MazeDrawer(screen.maze, 0.25f, 0.75f,  0.5f, 1f, MazeDrawer.Fit.BEST);
        this.add(maze_preview);
    }

    public void initializeButtons(){
        System.out.println("Initialization of the Multi Menu");
        // CREATION OF BUTTONS FOR THE CREATION OF THE MAP
        TextButton nextMapButton = new TextButton(">", Graphics.GUI.getSkin());
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
            }
        });

        //ADDING THE BUTTONS TO THE TABLE
        Table selectMap = new Table();
        selectMap.add(previousMapButton);
        selectMap.add(nextMapButton).spaceLeft(Value.percentHeight(5f));
        this.add(selectMap).align(Align.center).spaceBottom(Value.percentHeight(0.9f)).grow();
        this.row();

        // BOUTONS POUR LANCER LE JEU
        VerticalGroup buttons = new VerticalGroup();

        TextButton playButton = new TextButton("Jouer", Graphics.GUI.getSkin());
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameMultiScreen(screen.maze, screen.getMazeId()));
            }
        });

        //ADDING THE BUTTONS TO THE TABLE
        TextButton cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        buttons.addActor(playButton);
        buttons.addActor(cancelButton);


        //CREATING A PREVIEW FOR THE PLAYERS
        p1 = new ImageButton(new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(screen.Playable[screen.p1_id] + "/idle"), Animation.PlayMode.LOOP)).getDrawable());
        p1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP1();
                update();
            }
        });

        p2 = new ImageButton(new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(screen.Playable[screen.p2_id] + "/idle"), Animation.PlayMode.LOOP)).getDrawable());
        p2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP2();
                update();
            }
        });

        p3 = new ImageButton(new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(screen.Playable[screen.p3_id] + "/idle"), Animation.PlayMode.LOOP)).getDrawable());
        p3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP3();
                update();
            }
        });

        p4 = new ImageButton(new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(screen.Playable[screen.p4_id] + "/idle"), Animation.PlayMode.LOOP)).getDrawable());
        p4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextP4();
                update();
            }
        });

        //The next line is only to animate the players
        //p1.mustMove(true);  p2.mustMove(true);  p3.mustMove(true);  p4.mustMove(true);
        //Adding an image for each player to the table
        Table selectPlayer = new Table();
        selectPlayer.add(p1).grow();
        selectPlayer.add(p2).grow();
        selectPlayer.add(buttons).grow();
        selectPlayer.add(p3).grow();
        selectPlayer.add(p4).grow();
        this.add(selectPlayer).grow();
    }


}
