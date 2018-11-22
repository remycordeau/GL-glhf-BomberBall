package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    //attributes
    protected int position_x;
    protected int position_y;
    protected transient Texture appearance; //transient permet d'éviter d'écrire l'objet texture dans le fichier json

    // constructor
    protected GameObject(int position_x, int position_y, Texture appearance) {
        this.position_x = position_x;
        this.position_y = position_y;
        this.appearance = appearance;
    }

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
