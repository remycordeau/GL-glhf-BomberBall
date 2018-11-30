package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.Config;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class StateGame extends State {
    protected Maze maze;
    protected MazeDrawer mazeDrawer;

    public StateGame(String name, String maze_filename) {
        super(name);
        loadMaze(maze_filename);
    }

    public void loadMaze(String filename) {
        maze = Maze.fromJsonFile(filename);
        mazeDrawer = new MazeDrawer(maze, 0f,1f, 0f, 1f);
    }

    public void draw() {
        mazeDrawer.drawMaze();
    }
}
