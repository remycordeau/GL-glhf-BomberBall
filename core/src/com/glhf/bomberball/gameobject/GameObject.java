package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.maze.Cell;
import com.glhf.bomberball.menu.DIRECTIONS;

import java.io.Serializable;

public abstract class GameObject {
    //attributes
    protected int position_x;
    protected int position_y;
    protected transient AtlasRegion sprite; //transient permet d'éviter d'écrire l'objet texture dans le fichier json
    protected int life;

    protected transient Cell cell;

    protected GameObject() {
    }

    /**
     * constructor
     * @param position_x x axis initial position
     * @param position_y y axis initial position
     */
    protected GameObject(int position_x, int position_y) {
        this.position_x = position_x;
        this.position_y = position_y;
    }

    /**
     *
     * @return actual x axis position of the gameObject
     */
    public int getPositionX() {
        return position_x;
    }

    /**
     *
     * @return actual y axis position of the gameObject
     */
    public int getPositionY() {
        return position_y;
    }

    /**
     * This function add a Sprite to the SpriteBatch batch
     * @param batch the SpriteBatch in which it will add a Sprite
     */
    public void draw(SpriteBatch batch){
        batch.draw(sprite, position_x* Constants.BOX_WIDTH, position_y*Constants.BOX_HEIGHT);
    }

    /**
     * set a value for the x axis position
     * @param position_x
     */
    public void setPositionX(int position_x) {
        this.position_x = position_x;
    }

    /**
     * set a value for the y axis position
     * @param position_y
     */
    public void setPositionY(int position_y) {
        this.position_y = position_y;
    }

    /**
     * modification of the life of the gameObject
     * @param damage
     */
    public void getDamage(int damage){
        life-=damage;
    }

    public AtlasRegion getSprite() { return this.sprite; }

    /**
     * to know if an object has been destroyed or not
     * @return boolean if the gameObject is Alive
     */
    public boolean isAlive() {
        return life > 0;
    }

    public boolean isWalkable() { return false; }

    public void move(int dx, int dy) {
        position_x += dx;
        position_y += dy;
    }

    public boolean moveDir(DIRECTIONS dir)
    {
        return moveToCell(cell.getAdjacentCell(dir));
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
            moved = true;
        }
        return moved;
    }

    public void setCell(Cell cell)
    {
        this.cell = cell;
    }
}
