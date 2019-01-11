package com.glhf.bomberball.screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeBuilder;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.SoloUI;
import com.glhf.bomberball.utils.Constants;
import com.glhf.bomberball.utils.Directions;

import java.util.ArrayList;

public class GameStoryScreen extends GameScreen {

    private GameSoloConfig config;
    private ArrayList<Enemy> enemies;
    private ArrayList<Cell> selected_cells = new ArrayList<>();
    private int maze_id;
    private StoryMenuScreen screen;

    public GameStoryScreen(StoryMenuScreen screen, Maze maze, int maze_id) {
        //super(maze);
        super(MazeBuilder.createInfinityMaze());
        this.maze_id = maze_id;
        this.screen = screen;

        config = new GameSoloConfig();
        current_player = this.maze.spawnPlayer(config);

        //enemies = this.maze.getEnemies();
        enemies = this.maze.spawnEnemies(new GameSoloConfig());
        enemies.forEach(Enemy::createAI);
        this.maze.export("testWithEnemies");

        addUI(new SoloUI(current_player, this));
        addUI(maze_drawer);

        current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
        setMoveMode();
    }

    @Override
    public void registerActionsHandlers() {
        super.registerActionsHandlers();
    }

    /**
     * gives the next current_player after a turn. If the next current_player is dead, choose the following current_player.
     */
    protected void nextPlayer() {
        //TODO: wait the execution of all tasks in the Timer
        if (!current_player.isAlive()) {
            Bomberball.changeScreen(new DeadScreen(screen, maze_id));
        }
        if (maze_id + 1 == screen.getMazeCount()) { // if the current_player has completed the last level
            Bomberball.changeScreen(new EndStoryScreen(screen, this.maze_id));
            return;
        }
        // test if the current_player reached the door
        boolean isIn = false;
        for (GameObject o : current_player.getCell().getGameObjects()) {
            if (o instanceof Door) {
                isIn = true;
            }
        }
        if (isIn) {
            Bomberball.changeScreen(new EndLevelScreen(screen, this.maze_id));
        }

        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.initiateTurn();
                enemy.followWay();
            }
        }

        if (!current_player.isAlive()) {
            Bomberball.changeScreen(new DeadScreen(screen, maze_id));
        } else {
            current_player.initiateTurn();
            setMoveEffect();
            setMoveMode();
            input_handler.lock(false);
        }
    }

    /**
     * enemies path is calculated everytime a bomb has been droped
     * @param dir
     */
    @Override
    protected void dropBomb(Directions dir) {
        super.dropBomb(dir);
        enemies.forEach(Enemy::createAI);
    }
}