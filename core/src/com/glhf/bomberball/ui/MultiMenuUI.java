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

        Value spacing_map_button = Value.percentHeight(0.3f);

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

        TextButton playButton = new TextButton("Jouer", Graphics.GUI.getSkin());
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameMultiScreen(screen.maze));
            }
        });

        TextButton cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        //TODO : Réussir à charger des images
        //AnimationActor p1 = new AnimationActor(new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get("animations/elf_f"), Animation.PlayMode.LOOP));
        Image p1 = new Image(Graphics.Sprites.get("ui_heart_full"));
        Image p2 = new Image(Graphics.Sprites.get("ui_heart_full"));
        Image p3= new Image(Graphics.Sprites.get("ui_heart_full"));
        Image p4 = new Image(Graphics.Sprites.get("ui_heart_full"));


        this.add(previousMapButton).align(Align.center).spaceBottom(spacing_map_button);
        this.add(nextMapButton).align(Align.center).spaceBottom(spacing_map_button);
        this.row();
        //Adding an image for each player
        this.add(p1).space(Value.percentHeight(0.2f)); this.add(p2).space(Value.percentHeight(0.2f)); this.add(p3).space(Value.percentHeight(0.2f)); this.add(p4).space(Value.percentHeight(0.2f));
        this.row();
        this.add(playButton).grow();
        this.add(cancelButton).grow();
    }
}
