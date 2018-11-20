package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ennemy extends Character {
    //attributes
    protected int strength;

    public void touchPlayer(Player player){
        player.getDamage(strength);
    }

}
