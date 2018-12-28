package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Bomb;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.screens.GameMultiScreen;
import com.glhf.bomberball.screens.GameStoryScreen;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.StoryMenuScreen;
//import sun.java2d.windows.GDIRenderer;

public class EndLevelUI extends Table {

    private TextButton next,replay_level,back_menu_button,back_level_button;
    private Label victory, unlocked;
    private Table buttons;
    private static StoryMenuScreen screen;
    private int previous_level;
    private int next_level;


    public EndLevelUI(StoryMenuScreen screen,int maze_id){
        super();
        this.setFillParent(true);
        this.screen = screen;
        this.previous_level = maze_id;
        this.next_level = previous_level +1;
        if(next_level < screen.getMazeCount()){ // if we're not at the last level
            screen.setLevelUnlocked(next_level); // unlocks next level
            StoryMenuUI.unlockLevel(next_level + 1); //updates the ui with the unlocked level
            addButtons();
        }
        else{ //TODO implement end of story mode screen and death screen and resolve the fact that when we go back to main menu, the progression is erased (due to the new)
            //Bomberball.changeScreen(new EndStoryScreen());
        }
    }

    private void addButtons() {

        // labels
        victory = new Label("Congratulations ! You've succeeded level "+Integer.toString(previous_level + 1)+" !",Graphics.GUI.getSkin());
        unlocked = new Label("Level "+Integer.toString(next_level + 1)+" unlocked !",Graphics.GUI.getSkin());
        this.add(victory).spaceBottom(Value.percentHeight(0.9f)).row();
        this.add(unlocked).spaceBottom(Value.percentHeight(1.5f)).row();

        //buttons
        buttons = new Table();
        next = new TextButton("Play next level", Graphics.GUI.getSkin());
        next.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(next_level<8){
                    screen.nextMaze();
                    Bomberball.changeScreen(new GameStoryScreen(screen,screen.maze,screen.getMazeId()));
                }
            }
        });
        buttons.add(next).spaceTop(Value.percentHeight(0.2f)).row();

        replay_level = new TextButton("Replay level",Graphics.GUI.getSkin());
        replay_level.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameStoryScreen(screen, Maze.importMaze("maze_" + previous_level),screen.getMazeId()));
            }
        });
        buttons.add(replay_level).spaceTop(Value.percentHeight(0.2f)).row();

        back_level_button = new TextButton("Back to level selection",Graphics.GUI.getSkin());
        back_level_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(screen);
            }
        });
        buttons.add(back_level_button).spaceTop(Value.percentHeight(0.2f)).row();

        back_menu_button = new TextButton("Back to title menu",Graphics.GUI.getSkin());
        back_menu_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new MainMenuScreen());
            }
        });
        buttons.add(back_menu_button).spaceTop(Value.percentHeight(0.2f)).row();
        this.add(buttons);
    }

    public static StoryMenuScreen getUpdatedStoryScreen(){
        return screen;
    }
}
