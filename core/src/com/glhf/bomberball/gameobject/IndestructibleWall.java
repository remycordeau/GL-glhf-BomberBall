package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;

public class IndestructibleWall extends Wall {

    public IndestructibleWall() {
        super();
        this.sprite = Graphics.Sprites.get("wall");
    }

    /**
     * Override of the GameObject's getDamage method : an indestructible can't be broken
     * @param damage
     */
    @Override
    public void getDamage(int damage){ }

    @Override
    public TextureAtlas.AtlasRegion getSprite() {
        if (this.cell != null) {
            Cell down = this.cell.getAdjacentCell(Directions.DOWN);
            if (down != null && down.hasInstanceOf(IndestructibleWall.class)) {
                return Graphics.Sprites.get("wall_top");
            }
        }
        return this.sprite;
    }

    @Override
    public GameObject clone() {
        IndestructibleWall o = new IndestructibleWall();
        o.sprite = this.sprite;
        return o;
    }
}
