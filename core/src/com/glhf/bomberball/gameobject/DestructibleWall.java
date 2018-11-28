package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Textures;
import com.glhf.bomberball.gameobject.Wall;

public class DestructibleWall extends Wall {

    // constructor
    public DestructibleWall()
    {
        this.appearance = Textures.get("box");
    }

    public DestructibleWall(int position_x, int position_y, int life) {
        super(position_x, position_y, life);
        appearance = Textures.get("box");
    }
}
