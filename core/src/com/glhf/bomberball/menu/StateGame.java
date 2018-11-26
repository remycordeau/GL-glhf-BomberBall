package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.maze.Maze;

public class StateGame extends State {
    private Maze maze;

    public StateGame(){
        super("Game");
    }

    public State loadMaze(String filename){
        maze = Maze.fromJsonFile(filename);
        batch = new SpriteBatch();
        float width = Constants.BOX_WIDTH * maze.getWidth();
        float height = Constants.BOX_HEIGHT * maze.getHeight();
        if(width/height < Constants.APP_WIDTH/(float)Constants.APP_HEIGHT){
            width = height/Constants.APP_HEIGHT*Constants.APP_WIDTH;
        }else{
            height = width/Constants.APP_WIDTH*Constants.APP_HEIGHT;
        }
        //TODO comprendre pourquoi le Maze n'est pas centré verticalement sur l'écran
        OrthographicCamera cam = new OrthographicCamera(width, height);
        cam.translate(maze.getWidth()/2f*Constants.BOX_WIDTH, maze.getHeight()/2f*Constants.BOX_HEIGHT);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        return this;
    }

    public void draw(){
        batch.begin();
        maze.draw(batch);
        batch.end();
    }

}
