package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class ActiveEnemy extends Enemy {
    //attributes

    // constructor
    public ActiveEnemy(int position_x, int position_y, Texture appearance, int life) { // temporary, create a file with parameter
        super(position_x, position_y, appearance, life);
        strength = 1;
        // way = plus long chemin;
    }

    public void setWay(ArrayList<moves> way) {
        this.way = way;
    }
}
