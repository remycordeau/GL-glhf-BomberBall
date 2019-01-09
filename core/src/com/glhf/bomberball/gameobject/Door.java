package com.glhf.bomberball.gameobject;

public class Door extends GameObject {

    public Door(){
        super(); //a door cannot be killed
    }

    /**
     * Override of the GameObject's getDamage method : the door can't be broken
     * @param damage
     */
    @Override
    public void getDamage(int damage){}
}
