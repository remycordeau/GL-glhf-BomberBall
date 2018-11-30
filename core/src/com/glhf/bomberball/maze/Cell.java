package com.glhf.bomberball.maze;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.GameObject;
import com.glhf.bomberball.gameobject.Wall;

import java.util.ArrayList;

/**
 * Maze's main component, represents a cell in a maze.
 */
public class Cell {

    private int x;
    private int y;
    private Maze maze;

    /**
     * Objects in cell
     */
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

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    /**
     * Damages all GameObjects in cell
     * @param damage getDamage amount to apply
     */
    public void getDamage(int damage)
    {
        for (int i=0; i<objects.size(); ) {
            GameObject o = objects.get(i);
            o.getDamage(damage);
            if (o.isAlive()) {
                i++;
            } else{
                objects.remove(o);
            }
        }
    }

    /**
     * Add an object in the cell
     * @param gameObject object to add
     */
    public void addGameObject(GameObject gameObject)
    {
        objects.add(gameObject);
    }

    /**
     * Moves an object to another cell
     * @param object object in cell to move
     * @param dest_cell destination cell
     */
    public void moveObjectTo(GameObject object, Cell dest_cell)
    {
        /*if (objects.contains(object)) {
            object.move(dest_cell);
        } else {
            System.err.println("Cannot move object not in cell");
        }*/
    }

    public void draw(SpriteBatch batch)
    {
        for (GameObject o : objects) {
            batch.draw(o.getSprite(), x * Constants.BOX_WIDTH, y * Constants.BOX_HEIGHT);
        }
    }

    public void removeGameObject(GameObject gameObject) {
        objects.remove(gameObject);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public boolean hasGameObjectInstanceOf(Class c) {
        for(GameObject gameObject : objects){
            if(c.isInstance(gameObject)){
                return true;
            }
        }
        return false;
    }
}
