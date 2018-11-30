package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.GameObject;

public abstract class Wall extends GameObject {


    protected Wall()
    {

    }
    // constructor

    protected Wall(int position_x, int position_y) {
        super(position_x, position_y);
        this.life= Constants.config_file.getAttribute("wall_life");
    }
}
