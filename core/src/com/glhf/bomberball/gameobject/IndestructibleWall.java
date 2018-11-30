package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class IndestructibleWall extends Wall {

    /**
     * Constructor for the IndestructibleWall class
     */
    public IndestructibleWall()
    {
        this.sprite = Graphics.Sprites.get("wall");
    }

    /**
     * Constructor for the IndestructibleWall class
     * @param position_x
     * @param position_y
     */
    public IndestructibleWall(int position_x, int position_y) {
        super(position_x, position_y);
        this.sprite = Graphics.Sprites.get("wall");
    }

    /**
     * Override of the GameObject's getDamage method : an indestructible can't be broken
     * @param damage
     */
    @Override
    public void getDamage(int damage){}
}
