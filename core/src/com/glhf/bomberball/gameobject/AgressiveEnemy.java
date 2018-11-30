package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

import java.util.ArrayList;

public class AgressiveEnemy extends Enemy {
    //attributes
    private int begin_position_x;
    private int begin_position_y;


    // constructor

    public AgressiveEnemy(int position_x, int position_y, ArrayList<Constants.moves> way, int begin_position_x, int begin_position_y) {
        super(position_x, position_y);
        this.begin_position_x=begin_position_x;
        this.begin_position_y=begin_position_y;
        life = Constants.config_file.getAttribute("aggressiveEnemy_life");
        strength = Constants.config_file.getAttribute("aggressiveEnemy_strength");
        this.way = way;
        //this.sprite = Textures.getAtlasRegion("AgressiveEnemy");
    }

    public void huntPlayer(ArrayList<Constants.moves> way) {
        // follow the minimal way to the player
        setWay(way);
        followWay();
    }


}
