package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.GameObject;

public abstract class Bonus extends GameObject {
    //attributes
    protected String name;

    //contructor
    public Bonus(int position_x, int position_y) {
        super(position_x, position_y, 1);
    }

    //getter
    public String getName(){
        return name;
    }
}
