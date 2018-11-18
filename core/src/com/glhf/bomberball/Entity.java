package com.glhf.bomberball;

public abstract class Entity extends GameObject {
    //attributes
    private int life;
    private int number_move_remaining;
    private String sprite_path;
    public abstract boolean collide(Entity e);
}
