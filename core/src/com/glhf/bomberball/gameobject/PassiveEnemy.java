package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;


public class PassiveEnemy extends Enemy {

    // constructor
    public PassiveEnemy(int position_x, int position_y, int life, ArrayList<Constants.moves> way) { // temporary, create a file with parameter
        super(position_x, position_y, life);
        strength = 1;
        this.way = way;
        //appearance = Textures.get("PassiveEnemy");
    }
}
