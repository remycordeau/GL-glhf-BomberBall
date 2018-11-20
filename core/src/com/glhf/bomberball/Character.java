package com.glhf.bomberball;

public abstract class Character extends GameObject {
    //attributes
    protected int position_x;
    protected int position_y;
    protected int life;
    protected int number_move_remaining;
    protected String sprite_path; // texture or string ?

    public void getDamage(int damage){
        life -= damage;
    }

    //move fonctions
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

    //getters et setters

    public int getPositionX() {
        return position_x;
    }

    public int getPositionY() {
        return position_y;
    }

    public int getLife() {
        return life;
    }

    public int getNumberMoveRemaining() {
        return number_move_remaining;
    }

    public String getSpritePath() {
        return sprite_path;
    }

    public void setPositionX(int position_x) {
        this.position_x = position_x;
    }

    public void setPositionY(int position_y) {
        this.position_y = position_y;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setNumberMoveRemaining(int number_move_remaining) {
        this.number_move_remaining = number_move_remaining;
    }


    public void setSpritePath(String sprite_path) {
        this.sprite_path = sprite_path;
    }



    // call this method only if number_move_remaining is >=0 after the move
    public void move(int position_x, int position_y){
        this.setPositionX(position_x);
        this.setPositionY(position_y);
        number_move_remaining -= Math.abs(position_x - this.position_x) + Math.abs(position_y - this.position_y);
    }
}
