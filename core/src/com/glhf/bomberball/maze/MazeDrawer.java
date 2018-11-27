package com.glhf.bomberball.maze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Textures;
import com.glhf.bomberball.gameobject.GameObject;

/*

*/
public class MazeDrawer {

    private Maze maze;
    private SpriteBatch batch;
    private int maze_width;
    private int maze_height;
    private int cell_width;
    private int cell_height;

    public MazeDrawer(Maze maze, SpriteBatch batch)
    {
        this.maze = maze;
        this.batch = batch;
        maze_width = maze.getWidth();
        maze_height = maze.getHeight();
        cell_width = Constants.BOX_WIDTH;
        cell_height = Constants.BOX_HEIGHT;
    }

    public void drawMaze()
    {
        batch.begin();
        drawFloor();
        drawBackWall();
        drawObjects();
        drawSideWalls();
        drawFrontWall();
        batch.end();
    }

    private void drawObjects()
    {
        for(int y = 0; y < maze_height; y++) {
            for (int x = 0; x < maze_width; x++) {
                int posX = x * Constants.BOX_WIDTH;
                int posY = y * Constants.BOX_HEIGHT;
                Texture texture = Textures.get("floor_1");
                batch.draw(texture, posX, posY);
                GameObject gameobject = maze.getGameObjectAt(x, y);
                if(gameobject != null) {
                    gameobject.draw(batch);
                }
            }
        }
    }

    private void drawSideWalls()
    {
        int y_pos;
        Texture texture_l = Textures.get("left_wall");
        Texture texture_r = Textures.get("right_wall");

        for (int y = 0; y < maze_height; y++)
        {
            y_pos = y * cell_width;
            batch.draw(texture_l, -cell_width, y_pos);
            batch.draw(texture_r, cell_width * maze_width, y_pos);
        }
    }

    private void drawBackWall()
    {
        int y_pos = cell_height * maze_height;
        int x_pos;
        Texture texture = Textures.get("back_wall");
        for (int x = 0; x < maze_width; x++)
        {
            x_pos = x * cell_width;
            batch.draw(texture, x_pos, y_pos);
        }

        texture = Textures.get("left_back_wall");
        batch.draw(texture, -cell_width, y_pos);
        texture = Textures.get("right_back_wall");
        batch.draw(texture, cell_width * maze_width, y_pos);
    }

    private void drawFrontWall()
    {
        int y_pos = -2 * cell_height;
        int x_pos;
        Texture texture = Textures.get("front_wall");
        for (int x = 0; x < maze_width; x++)
        {
            x_pos = x * cell_width;
            batch.draw(texture, x_pos, y_pos);
        }

        texture = Textures.get("left_front_wall");
        batch.draw(texture, -cell_width, y_pos);
        texture = Textures.get("right_front_wall");
        batch.draw(texture, cell_width * maze_width, y_pos);
    }

    private void drawFloor()
    {
        Texture texture = Textures.get("floor_1");
        int x_pos;
        int y_pos;
        for(int y = 0; y < maze_height; y++) {
            for (int x = 0; x < maze_width; x++) {
                x_pos = x * cell_width;
                y_pos = y * cell_height;
                batch.draw(texture, x_pos, y_pos);
            }
        }
    }
}
