package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glhf.bomberball.Constants;

import java.io.Serializable;

public abstract class GameObject {
    //attributes
    protected int position_x;
    protected int position_y;
    protected transient Texture appearance; //transient permet d'éviter d'écrire l'objet texture dans le fichier json
    protected int life;

    // constructor
    protected GameObject(int position_x, int position_y, int life) {
        this.position_x = position_x;
        this.position_y = position_y;
        this.life= life;
    }

    // getters and setters
    public int getPositionX() {
        return position_x;
    }

    public int getPositionY() {
        return position_y;
    }

    public void draw(SpriteBatch batch){
        batch.draw(appearance, position_x* Constants.BOX_WIDTH, position_y*Constants.BOX_HEIGHT);
    }

    public void setAppearance(Texture texture){
        this.appearance = texture;
    }

    public void setPositionX(int position_x) {
        this.position_x = position_x;
    }

    public void setPositionY(int position_y) {
        this.position_y = position_y;
    }

    public void getDamage(int damage){
        life-=damage;
    }

    // is the Object alive ?
    public Boolean isAlive() {
        return life <= 0;
    }

}
