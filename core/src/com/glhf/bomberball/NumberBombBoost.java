package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class NumberBombBoost extends GameObject implements Bonus{
    private int numberBombBoost;
    //constructor
    public NumberBombBoost(int position_x, int position_y, Texture appearance, int life){
        super(position_x, position_y, appearance, life);
        numberBombBoost=0;
    }
}
