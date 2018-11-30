package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;


public class PassiveEnemy extends Enemy {

    /**
     * constructor
     * @param position_x x axis initial position
     * @param position_y y axis initial position
     * @param way the way the enemy will follow
     */
    public PassiveEnemy(int position_x, int position_y, ArrayList<Constants.moves> way) {
        super(position_x, position_y);
        life = Constants.config_file.getIntAttribute("passiveEnemy_life");
        strength = Constants.config_file.getIntAttribute("passiveEnemy_strength");
        this.way = way;
        //TODO set the animation of the passive enemy
    }
}
