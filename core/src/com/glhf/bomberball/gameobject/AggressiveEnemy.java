package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;

public class AggressiveEnemy extends Enemy {
    //attributes
    private int begin_position_x;
    private int begin_position_y;


    /**
     * constructor
     * @param position_x x axis initial position of the enemy
     * @param position_y y axis initial position of the enemy
     * @param way
     * @return AggressiveEnemy
     */
    public AggressiveEnemy(int position_x, int position_y, ArrayList<Constants.moves> way) {
        super(position_x, position_y);
        this.begin_position_x=position_x;
        this.begin_position_y=position_y;
        life = Constants.config_file.getAttribute("aggressiveEnemy_life");
        strength = Constants.config_file.getAttribute("aggressiveEnemy_strength");
        this.way = way;
        //this.sprite = Textures.getAtlasRegion("AggressiveEnemy");
        //TODO set the animation of the aggressive enemy
    }

    /**
     * when the player is near the enemy, the enemy will follow the min way to the player
     * @param way minimal way between the hunted player and the hunter enemy
     */
    public void huntPlayer(ArrayList<Constants.moves> way) {
        // follow the minimal way to the player
        setWay(way);
        followWay();
    }


}
