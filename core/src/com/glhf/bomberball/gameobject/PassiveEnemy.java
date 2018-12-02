package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;


public class PassiveEnemy extends Enemy {

    /**
     * constructor
     * @param way the way the enemy will follow
     */
    public PassiveEnemy( ArrayList<Constants.moves> way, String skin) {
        super(skin, 5);
        this.life = life;
        this.strength = strength;
        this.way = way;
        //TODO set the animation of the passive enemy
    }
}
