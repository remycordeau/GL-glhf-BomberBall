package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;

public abstract class Character extends GameObject {

    protected int initial_moves = 5;
    protected String skin = "knight_m";

    protected transient int moves_remaining;

    protected transient Animation<TextureAtlas.AtlasRegion> animation;

    public Character(String skin, int life, int initial_moves) {
        super(life);
        this.skin = skin;
        this.initial_moves = initial_moves;
        this.moves_remaining = initial_moves;
        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        setAnimation("idle");
    }

    public int getMovesRemaining() {
        return moves_remaining;
    }

    /**
     * set the animation of the character
     * @param animation_name animation name
     */
    protected void setAnimation(String animation_name)
    {
        animation = new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(skin + "/" + animation_name), Animation.PlayMode.LOOP);
    }

    /**
     * Initiate attribute number_move_remaining at the beginning of a turn
     */
    public void initiateTurn() { moves_remaining = initial_moves; }

    @Override
    public TextureAtlas.AtlasRegion getSprite()
    {
        return animation.getKeyFrame(Game.time_elapsed);
    }

    @Override
    public boolean isWalkable(){
        return true;
    }
}
