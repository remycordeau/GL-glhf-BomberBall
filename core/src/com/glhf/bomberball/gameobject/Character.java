package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

public abstract class Character extends GameObject {
    //attributes
    protected int number_move_remaining;
    protected int number_initial_moves;

    public Character() {

    }

    /**
     * constructor
     * @param position_x x axis initial position
     * @param position_y y axis initial position
     */
    protected Character(int position_x, int position_y) {
        super(position_x, position_y);
        this.number_initial_moves = Constants.config_file.getAttribute("number_initial_move");
    }

    /**
     * Initiate attribute number_move_remaining at the beginning of a turn
     */
    public void initiateTurn(){
        number_move_remaining=number_initial_moves;
    }

    /**
     * move function Right
     */
    public void moveRight(){
        position_x+=1;
        number_move_remaining-=1;
    }

    /**
     * move function Left
     */
    public void moveLeft(){
        position_x-=1;
        number_move_remaining-=1;
    }

    /**
     * move function Up
     * origin of the labyrinthe top left corner
     */
    public void moveUp(){
        position_y-=1;
        number_move_remaining-=1;
    }

    /**
     * move function Down
     */
    public void moveDown(){
        position_y+=1;
        number_move_remaining-=1;
    }

    /**
     * move function, give number of move by x and by then move
     * @param dx number of move by x
     * @param dy number of move by y
     */
    public void move(int dx, int dy){
        super.move(dx, dy);
        number_move_remaining -= Math.abs(dx) + Math.abs(dy);
    }

    // call this method only if number_move_remaining is >=0 after the move, mouse
    public void moveAt(int position_x, int position_y){
        number_move_remaining -= Math.abs(position_x - this.position_x) + Math.abs(position_y - this.position_y);
        setPositionX(position_x);
        setPositionY(position_y);
    }

    /**
     *
     * @return playerLife
     */
    public int getLife() {
        return life;
    }

    /**
     *
     * @return number of moves remaining
     */
    public int getNumberMoveRemaining() {
        return number_move_remaining;
    }

    /**
     * set a value for the attribute life
     * @param life value of life wanted
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * set a value for the attribute nomber of move remaining
     * @param number_move_remaining
     */
    public void setNumberMoveRemaining(int number_move_remaining) {
        this.number_move_remaining = number_move_remaining;
    }
}
