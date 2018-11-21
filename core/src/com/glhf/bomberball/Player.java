package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Character {
    private int number_bomb_remaining;

    // constructor
    public Player(int position_x, int position_y, Texture appearance) { // temporary, create a file with parameter
        super(position_x, position_y, appearance);
        number_bomb_remaining=1;
    }
}
