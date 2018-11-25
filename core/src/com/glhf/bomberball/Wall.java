package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class Wall extends GameObject{

    // constructor

    protected Wall(int position_x, int position_y, int life) {
        super(position_x, position_y,life);
        this.life=1;
    }
}
