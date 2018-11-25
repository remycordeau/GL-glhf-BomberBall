package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.Hashtable;

public class Player extends Character {
    //attributes
    private int number_bomb_remaining;
    private int number_initial_bombs;
        //bonus owned
    private Hashtable<Bonus, Integer> bonus_owned;
    private NumberBombBoost numberBombBoost;
    private BombRangeBoost bombRangeBoost;
    private SpeedBoost speedBoost;


    // constructor
    public Player(int position_x, int position_y, Texture appearance, int life) { // temporary, create a file with parameter
        super(position_x, position_y, appearance, life);
        number_initial_bombs=1;
        //initialisation with no bonus (numberBombBoost, bombRangeBoost, speedBoost =0)
        bonus_owned.put(numberBombBoost, 0);
        bonus_owned.put(bombRangeBoost,0);
        bonus_owned.put(speedBoost,0);
    }

    // this method initiate the begin of a new turn
    @Override
    public void initiateTurn(){
        //numberBomb playable = initial numberBomb + number of Bonus that gives more bombs
        number_bomb_remaining= number_initial_bombs+ bonus_owned.get(numberBombBoost);
        number_move_remaining = number_initial_moves + bonus_owned.get(speedBoost);
    }

    public void dropBomb(int drop_position_x, int drop_position_y){
        number_bomb_remaining-=1;
        //if(Math.abs(position_x-drop_position_x)< bonus_owned.get(Object key_element)  and Math.abs(position_y-drop_position_y)<){

        //}
    }

    public void lootBonus(Bonus bonus) {
        if (this.bonus_owned.contains(bonus)) {
            this.bonus_owned.put(bonus, bonus_owned.get(bonus) + 1);
        } else {
            this.bonus_owned.put(bonus, 1);
        }
    }
}
