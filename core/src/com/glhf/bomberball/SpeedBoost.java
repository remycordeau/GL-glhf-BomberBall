package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class SpeedBoost extends GameObject implements Bonus{
    //attributes
    private int speedBoost;

    // constructor
    public SpeedBoost(int position_x, int position_y, Texture appearance, int life) {
        super(position_x, position_y, appearance, life);
        speedBoost=0;
    }
}
