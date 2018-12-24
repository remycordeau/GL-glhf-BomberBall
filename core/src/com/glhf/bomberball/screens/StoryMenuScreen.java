package com.glhf.bomberball.screens;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.ui.StoryMenuUI;

import static java.lang.Math.abs;

public class StoryMenuScreen extends MenuScreen {

    public Maze maze;
    private int maze_id = 0;
    private final int maze_count = 7;

    public StoryMenuScreen(){
        super();
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

    public int getMazeId(){
        return maze_id;
    }


}
