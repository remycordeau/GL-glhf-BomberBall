package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.GameObject;

public class Bonus extends GameObject {

    protected String name;

    public Bonus(String name) {
        super(1);
        this.name = name;
        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        this.sprite = Graphics.Sprites.get("flask_big_green");
    }

    /**
     *
     * @return Name of the bonus
     */
    public String getName(){
        return name;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
