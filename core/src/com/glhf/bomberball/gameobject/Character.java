package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;

public abstract class Character extends GameObject {
    //attributes
    protected int number_move_remaining;
    protected int number_initial_moves;
    protected Animation<TextureAtlas.AtlasRegion> animation;

    public Character() {
        this.number_initial_moves = Constants.config_file.getIntAttribute("number_initial_move");
    }

    /**
     * set the animation of the character
     * @param animation_str path to the animation
     */
    protected void setAnimation(String animation_str)
    {
        animation = new Animation<TextureAtlas.AtlasRegion>(0.15f, Graphics.Anims.get(animation_str), Animation.PlayMode.LOOP);
    }

    // method initiate turn
    /**
     * Initiate attribute number_move_remaining at the beginning of a turn
     */
    public void initiateTurn(){
        number_move_remaining = number_initial_moves;
    }

    /**
     *
     * @return playerLife
     */
    public int getLife() {
        return life;
    }

    /**
     *
     * @return number of moves remaining
     */
    public int getNumberMoveRemaining() {
        return number_move_remaining;
    }

    /**
     * set a value for the attribute life
     * @param life value of life wanted
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * set a value for the attribute nomber of move remaining
     * @param number_move_remaining
     */
    public void setNumberMoveRemaining(int number_move_remaining) {
        this.number_move_remaining = number_move_remaining;
    }
}
