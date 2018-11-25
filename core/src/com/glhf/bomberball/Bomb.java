package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends GameObject {
    // attributes
    private int damage;
    //constructor
    public Bomb(int position_x, int position_y, Texture appearance, int life) {
        super(position_x, position_y, appearance, life);
        // initially, bomb inflict 1 damage
        this.damage=1;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
