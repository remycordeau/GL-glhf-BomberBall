package com.glhf.bomberball.ui;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.glhf.bomberball.Bomberball;

public class AnimationActor extends Image {
    private Animation<TextureAtlas.AtlasRegion> animation;
    private boolean move;

    public AnimationActor(Animation<TextureAtlas.AtlasRegion> animation) {
        super(animation.getKeyFrame(Bomberball.time_elapsed));
        this.animation = animation;
        this.move = false;
    }

    public void mustMove(boolean move) {
        this.move = move;
    }

    @Override
    public void act(float delta) {
        if (move) {
            this.setDrawable(new SpriteDrawable(new Sprite(animation.getKeyFrame(Bomberball.time_elapsed))));
        }
    }
}
