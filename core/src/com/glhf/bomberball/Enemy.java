package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Entity{
    //attributes
    private int strengh;
    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
