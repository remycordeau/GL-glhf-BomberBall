package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ennemy extends Character {
    //attributes
    private int strengh;

    public void touchPlayer(Player player){
        player.getDamage(strengh);
    }

}
