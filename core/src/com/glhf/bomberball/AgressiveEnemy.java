package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class AgressiveEnemy extends Enemy {
    //attributes

    // constructor
    public AgressiveEnemy(int position_x, int position_y, int life, ArrayList<moves> way) { // temporary, create a file with parameter
        super(position_x, position_y, life);
        strength = 1;
        this.way = way;
        //appearance
    }

    public void huntPlayer(Player player) {
        // follow the minimal way to the player
    }

    public void returnToWay() {
        // return to its way
    }
}
