package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class DestructibleWall extends Wall {

    // constructor
    public DestructibleWall()
    {
        this.sprite = Graphics.Sprites.get("crate");
    }

    public DestructibleWall(int position_x, int position_y, int life) {
        super(position_x, position_y, life);
        sprite = Graphics.Sprites.get("crate");
    }
}
