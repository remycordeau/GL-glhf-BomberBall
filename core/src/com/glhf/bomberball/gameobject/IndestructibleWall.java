package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class IndestructibleWall extends Wall {

    public IndestructibleWall()
    {
        this.sprite = Graphics.Sprites.get("wall");
    }

    // constructor
    public IndestructibleWall(int position_x, int position_y) {
        super(position_x, position_y);
        this.sprite = Graphics.Sprites.get("wall");
    }

    // an indestructibleWall cannot be broken
    @Override
    public void getDamage(int damage){
    }
}
