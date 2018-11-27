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

    public State loadMaze_old(String filename){
        maze = Maze.fromJsonFile(filename);
        batch = new SpriteBatch();
        float width = Constants.BOX_WIDTH * maze.getWidth();
        float height = Constants.BOX_HEIGHT * maze.getHeight();
        if(width/height < Constants.APP_WIDTH/(float)Constants.APP_HEIGHT){
            width = height/Constants.APP_HEIGHT*Constants.APP_WIDTH;
        }else{
            height = width/Constants.APP_WIDTH*Constants.APP_HEIGHT;
        }

        OrthographicCamera cam = new OrthographicCamera(width, height);
        cam.translate(maze.getWidth()/2f*Constants.BOX_WIDTH, -maze.getHeight()/2f*Constants.BOX_HEIGHT+Constants.BOX_HEIGHT);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        return this;
    }

    public State loadMaze(String filename){
        maze = Maze.fromJsonFile(filename);
        batch = new SpriteBatch();

        float height_percent = 0.6f; // Pourcentage de la hauteur prise par le labyrinthe
        float width = Constants.BOX_WIDTH * maze.getWidth();
        float height = Constants.BOX_HEIGHT * maze.getHeight();
        float ratio = (float)Constants.APP_WIDTH / (float)Constants.APP_HEIGHT;
        float scaling = 1f / height_percent;

        OrthographicCamera cam = new OrthographicCamera(width * ratio * scaling, height * scaling);
        cam.translate(width / 2f, height / 2f); // Centre le labyrinthe
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        mazeDrawer = new MazeDrawer(maze, batch);

        return this;
    }

    public void draw() {
        mazeDrawer.drawMaze();
    }

}
