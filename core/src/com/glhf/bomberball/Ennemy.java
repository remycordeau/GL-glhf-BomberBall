package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Ennemy extends Character {
    //attributes
    private int strengh;

    public void touchPlayer(Player player){
        player.getDamage(strengh);
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
