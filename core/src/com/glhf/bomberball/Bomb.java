package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends GameObject {
    // attributes
    private int damage;
    private int range;
    //constructor
    public Bomb(int position_x, int position_y, Texture appearance, int range) {
        super(position_x, position_y, appearance, 1);
        // initially, bomb inflict 1 damage
        this.damage=1;
        this.range=range;
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    //method explode
    public void explode(){

    }
}
