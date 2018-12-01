package com.glhf.bomberball.maze;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.GameObject;

import javax.security.sasl.SaslServer;

/**
 * Class MazeDrawer
 * Vue du modèle Maze. Affiche le labyrinthe à l'écran.
 */
public class MazeDrawer {

    public enum Fit { BEST, WIDTH, HEIGHT }

    private Maze maze;
    private SpriteBatch batch;
    private int maze_width;
    private int maze_height;
    private OrthographicCamera camera;

    private float x_pos_offset;
    private float y_pos_offset;
    private float x_padding = 2.0f;
    private float y_padding = 4.0f;
    private Fit fit;

    public MazeDrawer(Maze maze, float x_minp, float x_maxp, float y_minp, float y_maxp, Fit fit)
    {
        this.maze = maze;
        maze_width = maze.getWidth();
        maze_height = maze.getHeight();
        this.fit = fit;

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
        float screen_ratio = (float)Constants.APP_WIDTH / (float)Constants.APP_HEIGHT;
        float maze_ratio = width / height;
        float r = screen_ratio / maze_ratio;

        float scaling = 1f;
        float x_offset = 0f;
        float y_offset = 0f;
        if (fit == Fit.BEST) {
            fit = (r > 1) ? Fit.HEIGHT : Fit.WIDTH;
        }
        if (fit == Fit.WIDTH) {
            scaling = 1 / (dx * r);
            y_offset = 0.5f + dy * height * scaling * (1 - r);
        } else { // Fit.HEIGHT
            scaling = 1 / (dy);
            x_offset = 0.5f * dx * width * scaling * (r - 1);
        }
        float width_scaled = width * scaling;
        float height_scaled = height * scaling;
        // batch_width => dx * width * scaling * r
        // batch_height => dy * height * scaling

        camera = new OrthographicCamera(width_scaled * r, height_scaled);
        camera.translate(width_scaled * r * (0.5f - x_minp) - x_offset, height_scaled * (0.5f - y_minp) - y_offset);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
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
        for(int y = maze_height - 1; y >= 0; y--) {
            for (int x = 0; x < maze_width; x++) {
                for(GameObject gameObject : maze.getCellAt(x, y).getObjects()){
                    drawTextureInCell(gameObject.getSprite(), x, y);
                }
            }
        }
    }

    private void drawFloor()
    {
        AtlasRegion sprite = Graphics.Sprites.get("floor");
        for(int y = 0; y < maze_height; y++) {
            for (int x = 0; x < maze_width; x++) {
               drawTextureInCell(sprite, x, y);
            }
        }
    }

    private void drawSideWalls()
    {
        AtlasRegion sprite_l = Graphics.Sprites.get("wall_side_mid_left");
        AtlasRegion sprite_r = Graphics.Sprites.get("wall_side_mid_right");
        for (int y = 0; y < maze_height; y++)
        {
            drawTextureInCell(sprite_l, -1, y);
            drawTextureInCell(sprite_r, maze_width, y);
        }
    }

    private void drawBackWall()
    {
        AtlasRegion sprite_top = Graphics.Sprites.get("wall_top_mid");
        AtlasRegion sprite_mid = Graphics.Sprites.get("wall_mid");
        for (int x = 0; x < maze_width; x++) {
            drawTextureInCell(sprite_top, x, maze_height + 1);
            drawTextureInCell(sprite_mid, x, maze_height);
        }

        drawTextureInCell(Graphics.Sprites.get("wall_side_top_left"), -1, maze_height + 1);
        drawTextureInCell(Graphics.Sprites.get("wall_side_mid_left"), -1, maze_height);
        drawTextureInCell(Graphics.Sprites.get("wall_side_top_right"), maze_width, maze_height + 1);
        drawTextureInCell(Graphics.Sprites.get("wall_side_mid_right"), maze_width, maze_height);
    }

    private void drawFrontWall()
    {
        AtlasRegion sprite_top = Graphics.Sprites.get("wall_top_mid");
        AtlasRegion sprite_mid = Graphics.Sprites.get("wall_mid");
        AtlasRegion sprite_bottom = Graphics.Sprites.get("edge");
        for (int x = 0; x < maze_width; x++) {
            drawTextureInCell(sprite_top, x, 0);
            drawTextureInCell(sprite_mid, x, -1);
            drawTextureInCell(sprite_bottom, x, -2);
        }
        drawTextureInCell(Graphics.Sprites.get("wall_side_front_left"), -1, -1);
        drawTextureInCell(Graphics.Sprites.get("edge_left"), -1, -2);
        drawTextureInCell(Graphics.Sprites.get("wall_side_front_right"), maze_width, -1);
        drawTextureInCell(Graphics.Sprites.get("edge_right"), maze_width, -2);
    }

    private void drawTextureInCell(AtlasRegion atlasRegion, int cell_x, int cell_y)
    {
        if (atlasRegion != null) {
            Vector2 p = cellToBatchPos(cell_x, cell_y);
            batch.draw(atlasRegion, p.x, p.y);
        }
    }

    public Vector2 screenPosToCell(int screen_x, int screen_y)
    {
        Vector3 p = new Vector3(screen_x, screen_y, 0f);
        camera.unproject(p);
        p.x = p.x - x_pos_offset;
        p.y = p.y - y_pos_offset;
        int cell_x = (int)Math.floor(p.x / Constants.BOX_WIDTH);
        int cell_y = (int)Math.floor(p.y / Constants.BOX_HEIGHT);
        return new Vector2(cell_x, cell_y);
    }

    private Vector2 cellToBatchPos(int cell_x, int cell_y)
    {
        float x = (cell_x * Constants.BOX_WIDTH) + x_pos_offset;
        float y = (cell_y * Constants.BOX_HEIGHT) + y_pos_offset;
        return new Vector2(x, y);
    }
}
