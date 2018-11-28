package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Textures;

import java.util.ArrayList;

public class AgressiveEnemy extends Enemy {
    //attributes
    private int begin_position_x;
    private int begin_position_y;


    // constructor

    public AgressiveEnemy(int position_x, int position_y, int life, ArrayList<Constants.moves> way, int begin_position_x, int begin_position_y) { // temporary, create a file with parameter
        super(position_x, position_y, life);
        this.begin_position_x=begin_position_x;
        this.begin_position_y=begin_position_y;
        strength = 1;
        this.way = way;
        //appearance
        this.appearance = Textures.get("AgressiveEnemy");
    }

    public void huntPlayer(ArrayList<Constants.moves> way) {
        // follow the minimal way to the player
        setWay(way);
        followWay();
    }


}
