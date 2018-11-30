package com.glhf.bomberball.maze;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.GameObject;

import java.util.ArrayList;

/**
 * Maze's main component, represents a cell in a maze.
 */
public class Cell {

    private int x;
    private int y;
    private Maze maze;

    private ArrayList<GameObject> objects;

    /**
     * Cell constructor
     * @param maze cell's maze
     * @param x position x in maze
     * @param y position y in maze
     */
    public Cell(Maze maze, int x, int y)
    {
        this.maze = maze;
        this.x = x;
        this.y = y;
        objects = new ArrayList<GameObject>();
    }

    /**
     * Damages all GameObjects in cell
     * @param damage damage amount to apply
     */
    public void damage(int damage)
    {
        for (GameObject o : objects) {
            o.getDamage(damage);
            if (!o.isAlive()) {
                objects.remove(o);
            }
        }
    }

    public void addGameObject(GameObject gameObject)
    {
        objects.add(gameObject);
    }

    public void draw(SpriteBatch batch)
    {
        for (GameObject o : objects) {
            batch.draw(o.getSprite(), x * Constants.BOX_WIDTH, y * Constants.BOX_HEIGHT);
        }
    }
}
