package com.glhf.bomberball;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class CellEffect {

    protected transient Animation<AtlasRegion> animation;

    public CellEffect(String animation_name)
    {
        animation = new Animation<AtlasRegion>(0.07f, Graphics.Anims.get("cell/" + animation_name), Animation.PlayMode.LOOP_PINGPONG);
    }

    public AtlasRegion getSprite() {
        return animation.getKeyFrame(Game.time_elapsed);
    }
}
