package com.glhf.bomberball.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.glhf.bomberball.Graphics;

public class Button extends Rectangle {
    //Attributes
    TextureAtlas.AtlasRegion sprite;


    public Button(int x, int y, int width, int height, String s){
        super(x, y, width, height);
        sprite = Graphics.GUI.get(s);
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(sprite, x, y);
        batch.end();
    }
}
