package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;

public class ActiveEnemy extends Enemy {
    //attributes

    public ActiveEnemy() {

    }

    /**
     * constructor
     * @param position_x
     * @param position_y
     * @return ActiveEnemy
     */
    public ActiveEnemy(int position_x, int position_y) {
        super(position_x, position_y);
        strength = Constants.config_file.getAttribute("activeEnemy_strength");
        life = Constants.config_file.getAttribute("activeEnemy_life");
        sprite = Graphics.Sprites.get("chort_idle_anim");
        // way = plus long chemin;
    }


}
