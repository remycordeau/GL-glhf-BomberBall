package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public abstract class Ennemy extends Character {
    //attributes
    protected int strength;
    protected ArrayList<Integer> way;

    public void touchPlayer(Player player){
        player.getDamage(strength);
    }

    public abstract void followWay();
}
