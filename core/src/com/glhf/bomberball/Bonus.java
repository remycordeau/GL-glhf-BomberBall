package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class Bonus extends GameObject{
    //attributes
    protected String name;

    //contructor
    public Bonus(int position_x, int position_y, Texture appearance) {
        super(position_x, position_y, appearance, 1);
    }

    //getter
    public String getName(){
        return name;
    }
}
