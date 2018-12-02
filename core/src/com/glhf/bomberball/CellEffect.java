package com.glhf.bomberball;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class CellEffect {

    private AtlasRegion sprite;

    public CellEffect(String sprite)
    {
        this.sprite = Graphics.Sprites.get(sprite);
    }

    public AtlasRegion getSprite() {
        return sprite;
    }
}
