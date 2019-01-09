package com.glhf.bomberball.screens;

import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.Enemy;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.SoloUI;

import java.util.ArrayList;

public class GameStoryScreen extends GameScreen {

    private GameSoloConfig config;
    private ArrayList<Character> characters;
    private ArrayList<Cell> selected_cells = new ArrayList<>();
    private int maze_id;
    private StoryMenuScreen screen;

    public GameStoryScreen(StoryMenuScreen screen, Maze maze, int maze_id) {
        //super(maze);
        super(new Maze(10,10));
        this.maze_id = maze_id;
        this.screen = screen;

        config = new GameSoloConfig();
        //maze.applyConfig(config);
        //current_player = maze.spawnPlayer(config);
        //setSelectEffect();


        addUI(new SoloUI(current_player,this));
        addUI(maze_drawer);

        //current_player.initiateTurn();      //after the UI because initiateTurn notify the ui
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
        Player winner = null;
        if(!current_player.isAlive()){
            Bomberball.changeScreen(new DeadScreen(screen,maze_id));
        }
        else{
            if(maze_id + 1 < screen.getMazeCount()){
                Bomberball.changeScreen(new EndLevelScreen(screen,this.maze_id));
                return;
            }
            if(maze_id + 1 == screen.getMazeCount()){ // if the current_player has completed the last level
                Bomberball.changeScreen(new EndStoryScreen(screen,this.maze_id));
                return;
            }
        }

        /*current_player.initiateTurn();
        setMoveEffect();
        setMoveMode();
        input_handler.lock(false);*/
    }
}
