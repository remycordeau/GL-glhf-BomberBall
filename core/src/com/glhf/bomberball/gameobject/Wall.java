package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;

public abstract class Wall extends GameObject {

    protected Wall() {
        super();
        this.life = Constants.config_file.getIntAttribute("wall_life");
    }
}
