package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public abstract class Enemy extends Character {
    //attributes
    protected int strength;
    protected ArrayList<Integer> way;

    // constructor
    protected Enemy(int position_x, int position_y, Texture appearance) { // temporary, create a file with parameter
        super(position_x, position_y, appearance);
    }

    public void touchPlayer(Player player){
        player.getDamage(strength);
    }

    public abstract void followWay();
}
