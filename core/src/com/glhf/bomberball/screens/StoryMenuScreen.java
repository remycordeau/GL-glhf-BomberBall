package com.glhf.bomberball.screens;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.ui.StoryMenuUI;

public class StoryMenuScreen extends MenuScreen {

    public Maze maze;
    private int maze_id = 0;
    private final int maze_count = 7; //number of levels in the story mode
    private boolean[] level_unlocked; //inform if the level is locked or not

    public StoryMenuScreen(){
        super();
        level_unlocked = new boolean[maze_count];
        level_unlocked[maze_id] = true; // first level always unlocked
        level_unlocked[maze_id + 1] = true; // second level unlocked
        for(int i = 2;i < maze_count;i++)
        {
            level_unlocked[i] = false; // default : all the other levels are locked
        }
        maze = Maze.importMaze("maze_" + maze_id);
        addUI(new StoryMenuUI(this));
    }

    /**
     * Change the maze to the next one in the maze list
     */
    public void nextMaze(){
        maze_id = (maze_id + 1) % maze_count;
        maze = Maze.importMaze("maze_" + maze_id);
    }

    /**
     * Change the maze to the previous one in the maze list
     */
    public void previousMaze(){
        maze_id = (maze_count + maze_id - 1) % maze_count;
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
        return level_unlocked[i];
    }

    // getters and setters

    public int getMazeId(){
        return maze_id;
    }

    public int getMazeCount(){ return this.maze_count; }

    /**
     * sets the state of a specified level to unlocked
     * @param i
     */
    public void setLevelUnlocked(int i){
        level_unlocked[i] = true;
    }

}
