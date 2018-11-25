package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.Hashtable;

public class Player extends Character {
    //attributes
    private int number_bomb_remaining;
    private int number_initial_bombs;
        //bonus owned
    private Hashtable<String, Integer> bonus_owned;



    // constructor
    public Player(int position_x, int position_y, int life) { // temporary, create a file with parameter
        super(position_x, position_y, life);
        number_initial_bombs=1;
        //appearance= path
    }

    // this method initiate the begin of a new turn
    @Override
    public void initiateTurn(){
        //numberBomb playable = initial numberBomb + number of Bonus that gives more bombs
        number_bomb_remaining= number_initial_bombs+ bonus_owned.get("NumberBombBoost");
        number_move_remaining = number_initial_moves + bonus_owned.get("SpeedBoost");
    }

    public void dropBomb(int drop_position_x, int drop_position_y){
        number_bomb_remaining-=1;
        //if(Math.abs(position_x-drop_position_x)< bonus_owned.get(Object key_element)  and Math.abs(position_y-drop_position_y)<){

        //}
    }

    public void lootBonus(Bonus bonus) {
        if (this.bonus_owned.contains(bonus.getName())) {
            this.bonus_owned.put(bonus.getName(), bonus_owned.get(bonus.getName()) + 1);
        } else {
            this.bonus_owned.put(bonus.getName(), 1);
        }
    }
}
