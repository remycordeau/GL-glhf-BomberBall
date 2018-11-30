package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class DestructibleWall extends Wall {

    /**
     * Constructor for the DestructibleWall class
     */
    public DestructibleWall()
    {
        this.sprite = Graphics.Sprites.get("crate");
    }

    /**
     * Constructor for the DestructibleWall class
     * @param position_x
     * @param position_y
     */
    public DestructibleWall(int position_x, int position_y) {
        super(position_x, position_y);
        sprite = Graphics.Sprites.get("crate");
    }
}
