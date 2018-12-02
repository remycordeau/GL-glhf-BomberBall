package com.glhf.bomberball.menu;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

public abstract class StateGame extends State {
    protected Maze maze;
    protected MazeDrawer mazeDrawer;

    public StateGame(String name, String maze_filename) {
        super(name);
        loadMaze(maze_filename);
    }

    public void loadMaze(String filename) {
        //maze = new Maze(11, 13);
        maze = Maze.fromJsonFile(filename);
        //maze.toJsonFile("maze_0.json");
        mazeDrawer = new MazeDrawer(maze, 0f, 1f, 0f, 1f, MazeDrawer.Fit.BEST);
    }

    public void draw() {
        mazeDrawer.drawMaze();
    }
}
