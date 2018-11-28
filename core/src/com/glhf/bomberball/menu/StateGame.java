package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

public class StateGame extends State {
    private Maze maze;
    private MazeDrawer mazeDrawer;

    public StateGame() {
        super("Game");
    }

    public State loadMaze(String filename){
        maze = Maze.fromJsonFile(filename);

        mazeDrawer = new MazeDrawer(maze, 0f,1f, 0f, 1f);

        return this;
    }

    public void draw() {
        mazeDrawer.drawMaze();
    }

}
