package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.GameObject;

public abstract class Wall extends GameObject {


    protected Wall()
    {

    }
    // constructor

    protected Wall(int position_x, int position_y, int life) {
        super(position_x, position_y,life);
        this.life=1;
    }
}
