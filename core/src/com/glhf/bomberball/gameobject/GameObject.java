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
    protected transient AtlasRegion sprite; //transient permet d'éviter d'écrire l'objet texture dans le fichier json
    protected int life;

    protected transient Cell cell;

    protected GameObject() {
    }

    /**
     *
     * @return actual x axis position of the gameObject
     */
    public int getX() {
        return cell.getX();
    }

    /**
     *
     * @return actual y axis position of the gameObject
     */
    public int getY() {
        return cell.getY();
    }

    /**
     * modification of the life of the gameObject
     * @param damage
     */
    public void getDamage(int damage){
        life -= damage;
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

    public boolean move(DIRECTIONS dir)
    {
        try {
        return moveToCell(cell.getAdjacentCell(dir)); }
        catch (NullPointerException e)
        {
            System.out.println(cell);
            System.err.println("err");
        }
        return false;
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

    public Cell getCell()
    {
        return this.cell;
    }
}
