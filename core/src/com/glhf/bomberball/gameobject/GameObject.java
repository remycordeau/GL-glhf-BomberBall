package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.Directions;

public abstract class GameObject {

    protected int life = 1;

    protected transient AtlasRegion sprite;
    protected transient Cell cell;
    
    //animation de deplacement
    private transient Vector2 offsetVec;
    private transient int frames_left;

    protected GameObject(int life) {
        this.life = life;
        offsetVec = new Vector2();
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
        boolean moved;
        if (dest_cell != null && dest_cell.isWalkable()) {
            this.startAnimation(dest_cell);
            cell.removeGameObject(this);
            dest_cell.addGameObject(this);
            this.interactWithCell(dest_cell);
            moved = true;
        }
        else
            moved = false;
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
    
    //============== animation =================
    
    private void startAnimation(Cell dest_cell){
        offsetVec.x = (cell.getX()-dest_cell.getX()) / (float) Constants.NB_ANIMATION_FRAMES;
        offsetVec.y = (cell.getY()-dest_cell.getY()) / (float) Constants.NB_ANIMATION_FRAMES;
        frames_left = Constants.NB_ANIMATION_FRAMES;
    }

    public Vector2 getOffset() {
        if(frames_left>0) frames_left--;
        return new Vector2().mulAdd(offsetVec, frames_left);
    }
}
