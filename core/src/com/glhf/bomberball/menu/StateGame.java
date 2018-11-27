package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

public class StateGame extends State {
    private Maze maze;
    private MazeDrawer mazeDrawer_0;
    private MazeDrawer mazeDrawer_1;

    public StateGame() {
        super("Game");
    }

    public State loadMaze(String filename){
        maze = Maze.fromJsonFile(filename);

        mazeDrawer_0 = new MazeDrawer(maze, 0f,0.75f, 0f, 0.75f);
        mazeDrawer_1 = new MazeDrawer(maze, 0.75f, 1f, 0.75f, 1f);

        return this;
    }

    public void draw() {
        mazeDrawer_0.drawMaze();
        mazeDrawer_1.drawMaze();
    }

}
