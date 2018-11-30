package com.glhf.bomberball.gameobject;

public abstract class Character extends GameObject {
    //attributes
    protected int number_move_remaining;
    protected int number_initial_moves;

    public Character() {

    }

    // constructor
    protected Character(int position_x, int position_y) { // temporary, create a file with parameter
        super(position_x, position_y);
        this.number_initial_moves = 5;
    }

    // method initiate turn
    public void initiateTurn(){
        number_move_remaining=number_initial_moves;
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

    public void move(int dx, int dy){
        super.move(dx, dy);
        number_move_remaining --; // Math.abs(dx - this.position_x) + Math.abs(position_y - this.position_y);
    }

    // call this method only if number_move_remaining is >=0 after the move, mouse
    public void moveAt(int position_x, int position_y){
        number_move_remaining -= Math.abs(position_x - this.position_x) + Math.abs(position_y - this.position_y);
        setPositionX(position_x);
        setPositionY(position_y);
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
