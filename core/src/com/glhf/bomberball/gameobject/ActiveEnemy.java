package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class ActiveEnemy extends Enemy {
    //attributes

    public ActiveEnemy() {
        sprite = Graphics.Sprites.get("chort_idle_anim");
    }

    // constructor
    public ActiveEnemy(int position_x, int position_y, int life) { // temporary, create a file with parameter
        super(position_x, position_y, life);
        strength = 1;
        //appearance = Textures.get("ActiveEnemy");
        // way = plus long chemin;
    }


}
