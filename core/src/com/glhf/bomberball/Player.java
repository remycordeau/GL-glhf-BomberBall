package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Character {
    private int number_bomb_remaining;

    // constructor
    public Player(){ // temporary, create a file with parameter
        super(position_x, position_y, appearance);
        life=1;
        number_bomb_remaining=1;
        number_move_remaining=5;
        //sprite_path
    }

    // getters and setters
    public int getNumberBombRemaining() {
        return number_bomb_remaining;
    }

    public void setNumberBombRemaining(int number_bomb_remaining) {
        this.number_bomb_remaining = number_bomb_remaining;
    }

}
