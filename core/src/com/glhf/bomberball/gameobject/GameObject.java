package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.Directions;

public abstract class GameObject {

    protected int life = 1;

    protected transient AtlasRegion sprite;
    protected transient Cell cell;

    protected GameObject(int life) {
        this.life = life;
    }

    public AtlasRegion getSprite() { return this.sprite; }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void initialize() { }

    /**
     * modification of the life of the gameObject
     * @param damage
     */
    public void getDamage(int damage){
        life -= damage;
        if (life <= 0) {
            this.dispose();
        }
    }

    public void dispose() {
        cell.removeGameObject(this);
    }

    /**
     * to know if an object has been destroyed or not
     * @return boolean if the gameObject is Alive
     */
    public boolean isAlive() {
        return life > 0;
    }

    public boolean isWalkable() { return false; }

    public boolean move(Directions dir)
    {
        return moveToCell(getCell().getAdjacentCell(dir));
    }

    public boolean moveToCell(Cell dest_cell)
    {
        boolean moved = false;
        if (dest_cell == null) {
            moved = false;
        }
        else if (dest_cell.isWalkable()) {
            cell.removeGameObject(this);
            dest_cell.addGameObject(this);
            this.interactWithCell(dest_cell);
            moved = true;
        }
        return moved;
    }

    public void interactWithCell(Cell cell) { }

    public void setCell(Cell cell)
    {
        this.cell = cell;
    }

    public Cell getCell()
    {
        if (cell != null) {
            return this.cell;
        } else {
            throw new RuntimeException("GameObject's cell is null");
        }
    }
}
