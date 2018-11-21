package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public abstract class Character extends GameObject {
    //attributes
    protected int life;
    protected int number_move_remaining;

    // constructor
    public Character(int position_x, int position_y, Texture appearance) { // temporary, create a file with parameter
        super(position_x, position_y, appearance);
        this.life = 1;
        this.number_move_remaining = 5;
    }

    public void getDamage(int damage){
        life -= damage;
    }

    //move functions
    public void moveRight(){
        position_x+=1;
        number_move_remaining-=1;
    }

    public void moveLeft(){
        position_x-=1;
        number_move_remaining-=1;
    }

    //origin top left corner
    public void moveUp(){
        position_y-=1;
        number_move_remaining-=1;
    }

    public void moveDown(){
        position_y+=1;
        number_move_remaining-=1;
    }

    // call this method only if number_move_remaining is >=0 after the move
    public void move(int position_x, int position_y){
        this.setPositionX(position_x);
        this.setPositionY(position_y);
        number_move_remaining -= Math.abs(position_x - this.position_x) + Math.abs(position_y - this.position_y);
    }



    //getters et setters
    public int getLife() {
        return life;
    }

    public int getNumberMoveRemaining() {
        return number_move_remaining;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setNumberMoveRemaining(int number_move_remaining) {
        this.number_move_remaining = number_move_remaining;
    }
}
