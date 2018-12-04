package com.glhf.bomberball.maze;

import com.glhf.bomberball.CellEffect;
import com.glhf.bomberball.Game;
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

    private transient CellEffect cell_effect;

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

    public CellEffect getCellEffect() {
        return this.cell_effect;
    }


    public void initialize(Cell cell_right, Cell cell_up, Cell cell_left, Cell cell_down)
    {
        this.adjacent_cells = new Cell[] {cell_right, cell_up, cell_left, cell_down};
        for (GameObject o : objects) {
            o.setCell(this);
            o.initialize();
        }
    }

    public Cell getAdjacentCell(Directions dir)
    {
        return adjacent_cells[dir.ordinal()];
    }

    /**
     * @return List of all adjacent cells
     * Discards null adjacent cells
     */
    public ArrayList<Cell> getAdjacentCells()
    {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (Directions dir : Directions.values()) {
            Cell c = getAdjacentCell(dir);
            if (c != null) {
                cells.add(c);
            }
        }
        return cells;
    }

    /**
     * Damages all GameObjects in cell
     * @param damage getDamage amount to apply
     */
    public void getDamage(int damage)
    {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>(objects);
        for (GameObject o : gameObjects) {
            o.getDamage(damage);
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
        boolean propagate = this.isWalkable();
        getDamage(damage);
        if (propagate) {
            if (range > 1 && dir != null) {
                Cell adjacent_cell = getAdjacentCell(dir);
                if (adjacent_cell != null) {
                    adjacent_cell.explode(dir, damage, range - 1);
                }
            }
        }
    }

    /**
     * Explodes all the bombs in the cell
     */
    public void processEndTurn()
    {
        for (Bomb b : this.getInstancesOf(Bomb.class)) {
            b.explode();
        }
    }

    /**
     * Search in cell for gameObjects instances of specified class.
     * @param c Class to extract
     * @param <T>
     * @return All gameObjects in cell, instances of c
     */
    public <T extends GameObject> ArrayList<T> getInstancesOf(Class<T> c) {
        ArrayList<T> instances = new ArrayList<T>();
        for(GameObject o : objects){
            if(c.isInstance(o)){
                instances.add(c.cast(o));
            }
        }
        return instances;
    }

    public void setSelectEffect() {
        cell_effect = new CellEffect("cell_select");
    }

    public void removeEffect() {
        cell_effect = null;
    }
}
