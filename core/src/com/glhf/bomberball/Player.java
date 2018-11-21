package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.Hashtable;

public class Player extends Character {
    private int number_bomb_remaining;
    private Hashtable<Bonus, Integer> bonus_owned;


    // constructor
    public Player(int position_x, int position_y, Texture appearance) { // temporary, create a file with parameter
        super(position_x, position_y, appearance);
        number_bomb_remaining=1;
    }

    public void dropBomb(int drop_position_x, int drop_position_y){
        number_bomb_remaining-=1;
        //if(Math.abs(position_x-drop_position_x)< bonus_owned.get(Object key_element)  and Math.abs(position_y-drop_position_y)<){

        //}
    }
}
