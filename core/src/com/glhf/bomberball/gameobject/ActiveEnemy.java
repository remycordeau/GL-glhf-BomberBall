package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;

public class ActiveEnemy extends Enemy {
    //attributes

    public ActiveEnemy() {
        super();
        strength = Constants.config_file.getIntAttribute("activeEnemy_strength");
        life = Constants.config_file.getIntAttribute("activeEnemy_life");
        sprite = Graphics.Sprites.get("chort_idle_anim");
        //TODO set the animation of the active enemy
        // way = plus long chemin;
    }


}
