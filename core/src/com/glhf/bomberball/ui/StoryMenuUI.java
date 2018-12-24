package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.*;

public class StoryMenuUI extends Table {

    private TextButton back_button, next_level_button, previous_level_button,play_button;
    private Label label;
    private Table level_selection;
    private MazeDrawer level_preview;
    private StoryMenuScreen screen;
    private Table buttons;


    public StoryMenuUI(StoryMenuScreen screen){

        super();
        this.screen = screen;
        this.setFillParent(true);
        level_selection = new Table();
        addButtons();
        level_preview = new MazeDrawer(screen.maze, 0.25f, 0.75f,  0.5f, 0.75f, MazeDrawer.Fit.BEST);
        this.add(level_preview);
    }

    /**
     * initializes and adds all the buttons to the table. Also adds listeners to buttons if necessary
     */

    private void addButtons() {

        label = new Label("Level Selection",Graphics.GUI.getSkin(),"Title");
        label.setAlignment(Align.center);
        label.setFontScale(1.7f,1.7f);
        this.add(label).row();


        previous_level_button = new TextButton("<",Graphics.GUI.getSkin());
        previous_level_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.previousMaze();
                level_preview.setMaze(screen.maze);
            }
        });
        level_selection.add(previous_level_button);

        next_level_button = new TextButton(">",Graphics.GUI.getSkin());
        next_level_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextMaze();
                level_preview.setMaze(screen.maze);
            }
        });
        level_selection.add(next_level_button).spaceLeft(Value.percentHeight(8f));

        this.add(level_selection).align(Align.center).grow().row();

        buttons = new Table();
        play_button = new TextButton("Play Selected Level",Graphics.GUI.getSkin());
        play_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameMultiScreen(screen.maze,screen.getMazeId()));
            }
        });
        buttons.add(play_button).row();

        back_button = new TextButton("Back to main menu",Graphics.GUI.getSkin());
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        back_button.getLabel().setFontScale(0.8f,0.8f);
        buttons.add(back_button).spaceTop(Value.percentHeight(1f));
        this.add(buttons);
    }
}


