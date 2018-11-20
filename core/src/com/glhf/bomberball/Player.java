package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Character {
    private int number_bomb_remaining;

    // constructor
    public Player(){ // temporary, create a file with parameter
        position_x=0;
        position_y=0;
        life=1;
        number_bomb_remaining=1;
        number_move_remaining=5;
        //sprite_path
    }
}
