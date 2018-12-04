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
    protected MazeDrawer maze_drawer;

    public StateGame(String name, String maze_filename) {
        super(name);
        loadMaze(maze_filename,0f,1f,0f,1f);
    }

    public void loadMaze(String filename, float w_minp, float w_maxp, float h_minp, float h_maxp) {
        //maze = new Maze(11, 13);
        maze = Maze.fromJsonFile(filename);
        //maze.toJsonFile("maze_0.json");
        maze_drawer = new MazeDrawer(maze, w_minp, w_maxp, h_minp, h_maxp, MazeDrawer.Fit.BEST);
    }

    public void draw() {
        super.draw();
        maze_drawer.drawMaze();
    }
}
