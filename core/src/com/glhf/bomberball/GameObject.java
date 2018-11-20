package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class GameObject {
    //attributes
    protected int position_x;
    protected int position_y;
    protected Texture appearance;

    // getters and setters
    public int getPositionX() {
        return position_x;
    }

    public int getPositionY() {
        return position_y;
    }

    public Texture getTexture(){
        return appearance;
    }

    public void setPositionX(int position_x) {
        this.position_x = position_x;
    }

    public void setPositionY(int position_y) {
        this.position_y = position_y;
    }
}
