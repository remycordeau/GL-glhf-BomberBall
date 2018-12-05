package com.glhf.bomberball.menu;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

public abstract class StateGame extends State {

    protected Maze maze;
    protected MazeDrawer mazeDrawer;

    public StateGame(String maze_name) {
        loadMaze(maze_name);
    }

    public void loadMaze(String maze_name) {
        //maze = new Maze(11, 13);
        //maze.export(maze_name);
        maze = Maze.importMaze(maze_name);
        mazeDrawer = new MazeDrawer(maze, 0f, 1f, 0f, 1f, MazeDrawer.Fit.BEST);
    }

    public void draw() {
        mazeDrawer.drawMaze();
    }
}
