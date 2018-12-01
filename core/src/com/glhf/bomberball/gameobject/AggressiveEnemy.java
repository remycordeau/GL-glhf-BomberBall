package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;

public class AggressiveEnemy extends Enemy {
    //attributes
    private int begin_position_x;
    private int begin_position_y;

    public AggressiveEnemy(ArrayList<Constants.moves> way) {
        super();
        life = Constants.config_file.getIntAttribute("aggressiveEnemy_life");
        strength = Constants.config_file.getIntAttribute("aggressiveEnemy_strength");
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
