package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;

public class ActiveEnemy extends Enemy {
    //attributes

    public ActiveEnemy(String skin) {
        super(skin, 5);
        this.strength = 1;
        sprite = Graphics.Sprites.get("chort_idle_anim");
        //TODO set the animation of the active enemy
        // way = plus long chemin;
    }


}
