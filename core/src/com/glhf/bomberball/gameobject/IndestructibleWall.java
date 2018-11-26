package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.Wall;

public class IndestructibleWall extends Wall {

    // constructor
    protected IndestructibleWall(int position_x, int position_y, int life) {
        super(position_x, position_y,life);
        //apperance
    }

    // an indestructibleWall cannot be broken
    @Override
    public void getDamage(int damage){
    }
}
