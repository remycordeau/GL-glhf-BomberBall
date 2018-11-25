package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class AgressiveEnemy extends Enemy {
    //attributes
    private int begin_position_x;
    private int begin_position_y;

    // constructor

    public AgressiveEnemy(int position_x, int position_y, int life, ArrayList<moves> way, int begin_position_x, int begin_position_y) { // temporary, create a file with parameter
        super(position_x, position_y, life);
        this.begin_position_x=begin_position_x;
        this.begin_position_y=begin_position_y;
        strength = 1;
        this.way = way;
        //appearance
    }

    public void huntPlayer(Player player) {
        // follow the minimal way to the player
    }

    public void returnToWay(Maze map) {
        // return to its way
    }
}
