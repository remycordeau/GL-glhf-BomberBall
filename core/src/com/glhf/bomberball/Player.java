package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.util.Hashtable;

public class Player extends Entity {
    private int number_bomb_remaining;
    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
