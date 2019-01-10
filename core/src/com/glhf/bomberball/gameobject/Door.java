package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class Door extends GameObject {

    public Door(){
        super();
    }

    /**
     * Override of the GameObject's getDamage method : the door can't be broken
     * @param damage
     */
    @Override
    public void getDamage(int damage){}

    @Override
    public boolean isWalkable() { return true; }

    @Override
    public void initialize() {
        sprite = Graphics.Sprites.get("doors_leaf_closed");
    }

}
