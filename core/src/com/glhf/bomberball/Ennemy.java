package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Ennemy extends Character {
    //attributes
    private int strengh;
    @Override
    public boolean collide(Character e) {
        return false;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
