package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends GameObject {

    //constructor
    public Bomb(int position_x, int position_y, Texture appearance) {
        super(position_x, position_y, appearance);
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
