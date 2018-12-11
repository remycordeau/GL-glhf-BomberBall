package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;

public class AggressiveEnemy extends Enemy {

    private int begin_position_x;
    private int begin_position_y;

    public AggressiveEnemy(String skin, int life, int initial_moves, int strength) {
        super(skin, life, initial_moves, strength);
        //this.way = way;
    }

    /**
     * when the player is near the enemy, the enemy will follow the min way to the player
     * @param way minimal way between the hunted player and the hunter enemy
     */
//    public void huntPlayer(ArrayList<Constants.moves> way) {
//        // follow the minimal way to the player
//        setWay(way);
//        followWay();
//    }
}
