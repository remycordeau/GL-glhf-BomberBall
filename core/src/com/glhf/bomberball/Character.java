package com.glhf.bomberball;

public abstract class Character extends GameObject {
    //attributes
    private int position_x;
    private int position_y;
    private int life;
    private int number_move_remaining;
    private String sprite_path;
    public abstract boolean collide(Character e);
}
