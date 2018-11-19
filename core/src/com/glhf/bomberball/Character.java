package com.glhf.bomberball;

public abstract class Character extends GameObject {
    //attributes
    protected int position_x;
    protected int position_y;
    protected int life;
    protected int number_move_remaining;
    protected String sprite_path;
    public abstract void getDamage(int damage);
    public abstract void moveRight();
    public abstract void moveLeft();
}
