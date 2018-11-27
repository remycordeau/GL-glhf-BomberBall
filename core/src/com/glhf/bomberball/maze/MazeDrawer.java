package com.glhf.bomberball.maze;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Textures;
import com.glhf.bomberball.gameobject.GameObject;

/*
    Class MazeDrawer

    Vue du modèle Maze. Affiche le labyrinthe à l'écran.
*/
public class MazeDrawer {

    private Maze maze;
    private SpriteBatch batch;
    private int maze_width;
    private int maze_height;

    private float x_pos_offset;
    private float y_pos_offset;
    private float x_padding = 2.0f;
    private float y_padding = 4.0f;

    public MazeDrawer(Maze maze, float x_minp, float x_maxp, float y_minp, float y_maxp)
    {
        this.maze = maze;
        maze_width = maze.getWidth();
        maze_height = maze.getHeight();

        x_pos_offset = Constants.BOX_WIDTH;
        y_pos_offset = 2 * Constants.BOX_HEIGHT;

        setupBatch(x_minp, x_maxp, y_minp, y_maxp);
    }

    private void setupBatch(float x_minp, float x_maxp, float y_minp, float y_maxp)
    {
        batch = new SpriteBatch();

        float dx = x_maxp - x_minp;
        float dy = y_maxp - y_minp;

        float width = Constants.BOX_WIDTH * (maze.getWidth() + x_padding);
        float height = Constants.BOX_HEIGHT * (maze.getHeight() + y_padding);
        float ratio = (float)Constants.APP_WIDTH / (float)Constants.APP_HEIGHT;
        float scaling = 1f / dy;

        // batch width => width * scaling * ratio * dx;
        // batch height => height * scaling * dy;

        float width_scaled = width * ratio * scaling;
        float height_scaled = height * scaling;

        OrthographicCamera cam = new OrthographicCamera(width_scaled, height_scaled);
        float x_offset = 0.5f * dx * width_scaled * (1 - 1/ratio);
        cam.translate(width_scaled * (0.5f - x_minp) - x_offset, height_scaled * (0.5f - y_minp));
        cam.update();
        batch.setProjectionMatrix(cam.combined);
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

    private void drawObjects() {
        for(int y = 0; y < maze_height; y++) {
            for (int x = 0; x < maze_width; x++) {
                GameObject gameobject = maze.getGameObjectAt(x, y);
                if(gameobject != null) {
                    drawTextureInCell(gameobject.getApperance(), x, y);
                    //System.out.println("(" + x + "," + y + ")");
                }
            }
        }
    }

    private void drawSideWalls()
    {
        Texture texture_l = Textures.get("left_wall");
        Texture texture_r = Textures.get("right_wall");
        for (int y = 0; y < maze_height; y++)
        {
            drawTextureInCell(texture_l, -1, y);
            drawTextureInCell(texture_r, maze_width, y);
        }
    }

    private void drawBackWall()
    {
        Texture texture = Textures.get("back_wall");
        for (int x = 0; x < maze_width; x++)
            drawTextureInCell(texture, x, maze_height);

        drawTextureInCell(Textures.get("left_back_wall"), -1, maze_height);
        drawTextureInCell(Textures.get("right_back_wall"), maze_width, maze_height);
    }

    private void drawFrontWall()
    {
        Texture texture = Textures.get("front_wall");
        for (int x = 0; x < maze_width; x++) {
            drawTextureInCell(texture, x, -2);
        }
        drawTextureInCell(Textures.get("left_front_wall"), -1, -2);
        drawTextureInCell(Textures.get("right_front_wall"), maze_width, -2);
    }

    private void drawFloor()
    {
        Texture texture = Textures.get("floor_1");
        for(int y = 0; y < maze_height; y++) {
            for (int x = 0; x < maze_width; x++) {
               drawTextureInCell(texture, x, y);
            }
        }
    }

    private void drawTextureInCell(Texture texture, int cell_x, int cell_y)
    {
        Vector2 p = cellToBatchPos(cell_x, cell_y);
        batch.draw(texture, p.x, p.y);
    }

    private Vector2 cellToBatchPos(int cell_x, int cell_y)
    {
        float x = (cell_x * Constants.BOX_WIDTH) + x_pos_offset;
        float y = (cell_y * Constants.BOX_HEIGHT) + y_pos_offset;
        return new Vector2(x, y);
    }
}
