package com.glhf.bomberball.maze;

import com.glhf.bomberball.gameobject.Bomb;
import com.glhf.bomberball.gameobject.GameObject;
import com.glhf.bomberball.menu.Directions;

import java.util.ArrayList;

/**
 * Maze's main component, represents a cell in a maze.
 */
public class Cell {

    private int x;
    private int y;
    private transient Cell[] adjacent_cells;

    /**
     * Objects in cell
     */
    private ArrayList<GameObject> objects;

    /**
     * Cell constructor
     * @param x position x in maze
     * @param y position y in maze
     */
    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
        objects = new ArrayList<GameObject>();
        adjacent_cells = new Cell[Directions.values().length];
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void init(Cell cell_right, Cell cell_up, Cell cell_left, Cell cell_down)
    {
        this.adjacent_cells = new Cell[] {cell_right, cell_up, cell_left, cell_down};
        for (GameObject o : objects) {
            o.setCell(this);
        }
    }

    public Cell getAdjacentCell(Directions dir)
    {
        return adjacent_cells[dir.ordinal()];
    }

    /**
     * Damages all GameObjects in cell
     * @param damage getDamage amount to apply
     */
    public void getDamage(int damage)
    {
        for (int i = 0; i < objects.size(); ) {
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
     * Add a GameObject to a Cell
     * @param gameObject the GameObject to be inserted
     */
    public void addGameObject(GameObject gameObject)
    {
        gameObject.setCell(this);
        objects.add(gameObject);
    }

    /**
     * Remove a GameObject from a Cell
     * @param gameObject the GameObject to be removed
     */
    public void removeGameObject(GameObject gameObject) {
        gameObject.setCell(null);
        objects.remove(gameObject);
    }

    /*
    public void draw(SpriteBatch batch)
    {
    for (GameObject o : objects) {
    batch.draw(o.getSprite(), x * Constants.BOX_WIDTH, y * Constants.BOX_HEIGHT);
    }
    }*/

    /**
     * Fonction non nécéssaire si la classe Cell s'affiche elle même
     * TODO remove this function
     * @return objects
     */
    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public boolean isWalkable()
    {
        for (GameObject o : objects) {
            if (!o.isWalkable()) {
                return false;
            }
        }
        return true;
    }

    public Directions getCellDir(Cell cell)
    {
        for (Directions dir : Directions.values()) {
            if (getAdjacentCell(dir) == cell) {
                return dir;
            }
        }
        return null;
    }

    public void explode(Directions dir, int damage, int range)
    {
        getDamage(damage);
        if (range > 1 && dir != null) {
            Cell adjacent_cell = getAdjacentCell(dir);
            if (adjacent_cell != null) {
                adjacent_cell.explode(dir, damage, range - 1);
            }
        }
    }

    public void processEndTurn()
    {
        Bomb bomb = (Bomb)getGameObjectInstanceOf(Bomb.class);
        if (bomb != null) {
            bomb.explode();
        }
    }

    /**
     * Search in the Cell if a GameObject is instance of a specific Class
     * @param c the class to check
     * @return true if an object is instance of c
     */
    public GameObject getGameObjectInstanceOf(Class c) {
        for(GameObject gameObject : objects){
            if(c.isInstance(gameObject)){
                return gameObject;
            }
        }
        return null;
    }
}
