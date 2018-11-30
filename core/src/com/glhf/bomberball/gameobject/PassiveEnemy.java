package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;


public class PassiveEnemy extends Enemy {

    // constructor
    public PassiveEnemy(int position_x, int position_y, ArrayList<Constants.moves> way) {
        super(position_x, position_y);
        life = Constants.config_file.getAttribute("passiveEnemy_life");
        strength = Constants.config_file.getAttribute("passiveEnemy_strength");
        this.way = way;
        //appearance = Textures.get("PassiveEnemy");
    }
}
