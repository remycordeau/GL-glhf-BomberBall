package com.glhf.bomberball.screens;

import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.ui.StoryMenuUI;

public class StoryMenuScreen extends MenuScreen {

    public Maze maze;
    private int maze_id = 0;
    private GameSoloConfig config;

    public StoryMenuScreen(){
        super();
        config = GameSoloConfig.get();
        maze = Maze.importMaze("maze_" + maze_id);
        addUI(new StoryMenuUI(this));
    }

    /**
     * Change the maze to the next one in the maze list
     */
    public void nextMaze(){
        maze_id = (maze_id + 1) % GameConfig.maze_count;
        maze = Maze.importMaze("maze_" + maze_id);
    }

    /**
     * Change the maze to the previous one in the maze list
     */
    public void previousMaze(){
        maze_id = (GameConfig.maze_count + maze_id - 1) % GameConfig.maze_count;
        maze = Maze.importMaze("maze_" + maze_id);
    }

    /**
     * selects the maze of the specified parameter
     * @param i
     */
    public void getMaze(int i)
    {
        maze_id = i;
        maze = Maze.importMaze("maze_" + maze_id);
    }

    /**
     * informs if the specified level is locked or not
     * @param i
     * @return true if the level is unlocked, false otherwise
     */
    public boolean isLevelUnlocked(int i){
        return config.level_unlocked[i];
    }

    // getters and setters

    public int getMazeId(){
        return maze_id;
    }

    public int getMazeCount(){ return GameConfig.maze_count; }

    /**
     * sets the state of a specified level to unlocked
     * @param i
     */
    public void setLevelUnlocked(int i){
        config.level_unlocked[i] = true;
    }

}
