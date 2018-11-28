package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Textures;

public class Player extends Character {
    private int number_bomb_remaining;

    // constructor
    public Player(int position_x, int position_y, int life) { // temporary, create a file with parameter
        super(position_x, position_y, life);
        appearance = Textures.get("box");
    }


    // getters and setters
    public int getNumberBombRemaining() {
        return number_bomb_remaining;
    }

    public void setNumberBombRemaining(int number_bomb_remaining) {
        this.number_bomb_remaining = number_bomb_remaining;
    }

}
