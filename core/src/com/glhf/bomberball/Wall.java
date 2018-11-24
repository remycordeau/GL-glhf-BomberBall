package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class Wall extends GameObject{

    // constructor

    protected Wall(int position_x, int position_y, Texture appearance) {
        super(position_x, position_y, appearance);
    }
}
