package com.glhf.bomberball.gameobject;

public class Player extends Character {
    private int number_bomb_remaining;



    // getters and setters
    public int getNumberBombRemaining() {
        return number_bomb_remaining;
    }

    public void setNumberBombRemaining(int number_bomb_remaining) {
        this.number_bomb_remaining = number_bomb_remaining;
    }

}
