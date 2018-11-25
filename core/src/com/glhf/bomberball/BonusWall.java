package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class BonusWall extends DestructibleWall {

    //attributes

    private  Bonus bonus;

    // constructor

    protected BonusWall(int position_x, int position_y, Texture appearance,Bonus bonus, int life) {
        super(position_x, position_y, appearance, life);
        this.bonus=bonus;
    }
}
