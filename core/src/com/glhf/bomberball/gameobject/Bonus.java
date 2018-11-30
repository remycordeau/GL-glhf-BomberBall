package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.GameObject;

public abstract class Bonus extends GameObject {
    //attributes
    protected String name;

    /**
     *
     * @param position_x x axis initial position
     * @param position_y y axis initial position
     */
    public Bonus(int position_x, int position_y) {
        super(position_x, position_y);
        life = 1;
    }

    /**
     *
     * @return Name of the bonus
     */
    public String getName(){
        return name;
    }
}
