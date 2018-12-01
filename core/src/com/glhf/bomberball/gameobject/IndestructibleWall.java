package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class IndestructibleWall extends Wall {

    public IndestructibleWall() {
        this.sprite = Graphics.Sprites.get("wall");
    }

    /**
     * Override of the GameObject's getDamage method : an indestructible can't be broken
     * @param damage
     */
    @Override
    public void getDamage(int damage){}
}
