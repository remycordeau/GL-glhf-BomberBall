package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

public abstract class Wall extends GameObject {


    protected Wall()
    {

    }

    /**
     * constructor
     * @param position_x x axis initial position
     * @param position_y y axis initial position
     */
    protected Wall(int position_x, int position_y) {
        super(position_x, position_y);
        this.life= Constants.config_file.getIntAttribute("wall_life");
    }
}
