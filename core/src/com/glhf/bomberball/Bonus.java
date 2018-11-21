package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Bonus extends GameObject {

    // constructor
    public Bonus(int position_x, int position_y, Texture appearance) {
        super(position_x, position_y, appearance);
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
