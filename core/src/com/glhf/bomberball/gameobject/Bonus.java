package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.GameObject;

public abstract class Bonus extends GameObject {
    //attributes
    protected String name;

    public Bonus() {
        super();
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
