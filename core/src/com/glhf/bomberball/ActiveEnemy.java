package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class ActiveEnemy extends Enemy {
    //attributes

    // constructor
    public ActiveEnemy(int position_x, int position_y, Texture appearance) { // temporary, create a file with parameter
        super(position_x, position_y, appearance);
        strength = 1;
        // way = plus long chemin;
    }

    @Override
    public void followWay() {

    }
}
