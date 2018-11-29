package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class IndestructibleWall extends Wall {

    public IndestructibleWall()
    {
        this.sprite = Graphics.get("wall");
    }

    // constructor
    public IndestructibleWall(int position_x, int position_y, int life) {
        super(position_x, position_y,life);
        this.sprite = Graphics.get("wall");
    }

    // an indestructibleWall cannot be broken
    @Override
    public void getDamage(int damage){
    }
}
