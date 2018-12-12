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
import com.sun.org.apache.bcel.internal.generic.ALOAD;

public class MultiMenuUI extends Table {

    MultiMenuScreen screen;

    MazeDrawer maze_preview;

    public MultiMenuUI(MultiMenuScreen screen) {
        this.screen = screen;
        this.padTop(Value.percentHeight(0.2f));
        this.setFillParent(true);
        initializeButtons();
        maze_preview = new MazeDrawer(screen.maze, 0.25f, 0.75f,  1/2f, 1f, MazeDrawer.Fit.BEST);
        this.add(maze_preview);
    }

    public void initializeButtons(){
        Value spacing_map_button = Value.percentHeight(0.5f);

        // SELECTION OF THE MAP

        Table selectMap = new Table();

        this.add(selectMap).align(Align.center).spaceBottom(spacing_map_button).grow();
        this.row();
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
        selectMap.add(previousMapButton).spaceRight(0.5f);
        selectMap.add(nextMapButton).spaceRight(0.5f);

        //CREATING A PREVIEW FOR THE PLAYERS
        AnimationActor p1 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("knight_m/idle"), Animation.PlayMode.LOOP));
        AnimationActor p2 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("knight_f/idle"), Animation.PlayMode.LOOP));
        AnimationActor p3 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("elf_f/idle"), Animation.PlayMode.LOOP));
        AnimationActor p4 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("wizzard_m/idle"), Animation.PlayMode.LOOP));
        p1.mustMove(true);  p2.mustMove(true);  p3.mustMove(true);  p4.mustMove(true);
    // Changer les horizontal group par table
        Table selectPlayer = new Table();

        // BOUTONS POUR LANCER LE JEU
        VerticalGroup buttons = new VerticalGroup();

        TextButton playButton = new TextButton("Jouer", Graphics.GUI.getSkin());
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameMultiScreen(screen.maze));
            }
        });

        TextButton cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        buttons.addActor(playButton);
        buttons.addActor(cancelButton);

        //Adding an image for each player
        selectPlayer.add(p1).grow();
        selectPlayer.add(p2).grow();
        selectPlayer.add(buttons).grow();
        selectPlayer.add(p3).grow();
        selectPlayer.add(p4).grow();
        selectPlayer.align(Align.center);
        //selectPlayer.expand();
        this.add(selectPlayer).grow();
        this.row();

    }
}
