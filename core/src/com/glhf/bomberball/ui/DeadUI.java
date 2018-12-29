package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Bomb;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.screens.GameStoryScreen;
import com.glhf.bomberball.screens.StoryMenuScreen;

public class DeadUI extends Table {

    private Label dead;
    private TextButton replay, back_story_menu,ragequit;
    private StoryMenuScreen screen;
    private int maze_id;

    public DeadUI(StoryMenuScreen screen, int maze_id) {

        this.setFillParent(true);
        this.screen = screen;
        this.maze_id = maze_id;
        addButtons();
    }

    private void addButtons() {

        dead = new Label("Wasted !", Graphics.GUI.getSkin());
        dead.setFontScale(2f,2f);
        dead.setColor(Color.RED);
        this .add(dead).spaceBottom(Value.percentHeight(0.9f)).row();

        replay = new TextButton("Retry level",Graphics.GUI.getSkin());
        replay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(new GameStoryScreen(screen, Maze.importMaze("maze_" + maze_id),screen.getMazeId()));
            }
        });
        this.add(replay).spaceTop(Value.percentHeight(0.9f)).row();

        back_story_menu = new TextButton("Back to level selection",Graphics.GUI.getSkin());
        back_story_menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Bomberball.changeScreen(screen);
            }
        });
        this.add(back_story_menu).spaceTop(Value.percentHeight(0.9f)).row();

        ragequit = new TextButton("Ragequit ?",Graphics.GUI.getSkin());
        ragequit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        this.add(ragequit).spaceTop(Value.percentHeight(0.9f)).row();

    }
}
