package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Graphics;

public class Bonus extends GameObject {

    public enum Type {
        SPEED,
        BOMB_RANGE,
        BOMB_NUMBER
    }

    private Type type;

    public Bonus() {
        super();
    }

    public Bonus(Type type) {
        super(1);
        this.type = type;
    }

    @Override
    public void initialize() {
        switch (type) {
            case SPEED:
                sprite = Graphics.Sprites.get("arrowSpeed");
                break;
            case BOMB_RANGE:
                sprite = Graphics.Sprites.get("flame");
                break;
            case BOMB_NUMBER:
                sprite = Graphics.Sprites.get("bomb");
                break;
        }
    }

    public Type getType(){
        return this.type;
    }

    @Override
    public boolean isWalkable() { return true; }

    public void applyEffect(Player player) {
        switch (type) {
            case SPEED:
                player.bonus_moves++;
                break;
            case BOMB_RANGE:
                player.bonus_bomb_range++;
                break;
            case BOMB_NUMBER:
                player.bonus_bomb_number++;
                break;
        }
    }
}