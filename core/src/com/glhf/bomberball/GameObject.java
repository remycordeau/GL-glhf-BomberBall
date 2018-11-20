package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class GameObject {
    //attributes
    protected int position_x;
    protected int position_y;
    protected Texture appearance;
    public Texture getTexture(){
        return appearance;
    }
}
