package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.glhf.bomberball.Config;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

public abstract class StateGame extends State {
    protected Maze maze;
    protected MazeDrawer maze_drawer;

    public StateGame(String maze_filename) {
        super();
        loadMaze(maze_filename,0f,1f,0f,1f);
    }

    public void loadMaze(String filename, float w_minp, float w_maxp, float h_minp, float h_maxp) {
//        maze = new Maze(11, 13);
//        maze.export(filename);
        maze = Maze.importMaze(filename);
        maze_drawer = new MazeDrawer(maze, w_minp, w_maxp, h_minp, h_maxp, MazeDrawer.Fit.BEST);
    }

    @Override
    public void draw() {
        super.draw();
        maze_drawer.drawMaze();
    }


    class GameInputListener extends InputListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            System.err.println("Click ! --> Gestion des inputs à faire"); //TODO Gestion des inputs à faire
            return false; //super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            System.err.println("keyDown ! --> Gestion des inputs à faire"); //TODO Gestion des inputs à faire
            return super.keyDown(event, keycode);
        }
    }
}
