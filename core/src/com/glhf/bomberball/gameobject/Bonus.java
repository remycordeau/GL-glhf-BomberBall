package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.gameobject.GameObject;

public abstract class Bonus extends GameObject {

    protected String name;

    public Bonus(String name) {
        super(1);
        this.name = name;
    }

    /**
     *
     * @return Name of the bonus
     */
    public String getName(){
        return name;
    }
}
