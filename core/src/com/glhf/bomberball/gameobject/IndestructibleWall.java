package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Textures;
import com.glhf.bomberball.gameobject.Wall;

public class IndestructibleWall extends Wall {

    // constructor
    public IndestructibleWall(int position_x, int position_y, int life) {
        super(position_x, position_y,life);
        appearance = Textures.get("wall");
    }

    // an indestructibleWall cannot be broken
    @Override
    public void getDamage(int damage){
    }
}
