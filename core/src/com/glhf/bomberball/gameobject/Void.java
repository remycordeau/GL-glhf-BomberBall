package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Void extends GameObject {
    public Void(int position_x, int position_y) {
        super(position_x, position_y, 1);
    }

    @Override
    public void draw(SpriteBatch batch){

    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void getDamage(int i) {
    }
}
