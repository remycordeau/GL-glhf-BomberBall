package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class GameObject {
    private Texture appearance;
    public Texture getTexture(){
        return appearance;
    }
}
