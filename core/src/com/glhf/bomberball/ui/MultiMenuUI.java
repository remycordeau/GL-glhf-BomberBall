package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

    public MultiMenuUI(MultiMenuScreen screen) {
        this.screen = screen;
        this.padTop(Value.percentHeight(0.75f));
        this.setFillParent(true);

        initializeButtons();

        maze_preview = new MazeDrawer(screen.maze, 0.25f, 0.75f,  1/3f, 1f, MazeDrawer.Fit.BEST);
        this.add(maze_preview);
    }

    public void initializeButtons(){
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

        this.add(previousMapButton).grow();
        this.add(nextMapButton).grow();
        this.row();
        this.add(playButton).grow();
        this.add(cancelButton).grow();
    }
}
