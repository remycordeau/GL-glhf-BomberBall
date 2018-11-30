package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;

import java.util.Hashtable;

import static com.badlogic.gdx.graphics.g2d.Animation.*;

public class Player extends Character {
    //attributes
    private int number_bomb_remaining;
    private int number_initial_bombs;
    private int initial_bomb_range;
    //bonus owned
    private Hashtable<String, Integer> bonus_owned;

    private Animation<AtlasRegion> animation;

    // constructor
    public Player(int position_x, int position_y, String player_skin) {
        super(position_x, position_y);
        life = Constants.config_file.getAttribute("player_life");
        number_initial_bombs = Constants.config_file.getAttribute("number_initial_bomb");
        initial_bomb_range = Constants.config_file.getAttribute("initial_bomb_range");
        bonus_owned = new Hashtable<String, Integer>();
        setAnimation(player_skin+"/idle");
    }

    private void setAnimation(String animation_str)
    {
        animation = new Animation<AtlasRegion>(0.15f, Graphics.Anims.get(animation_str), PlayMode.LOOP);
    }

    @Override
    public AtlasRegion getSprite()
    {
        return animation.getKeyFrame(Game.time_elapsed);
    }

    // this method initiate the begin of a new turn
    @Override
    public void initiateTurn() {
        if (bonus_owned.contains("NumberBombBoost")) {
            number_bomb_remaining = number_initial_bombs + bonus_owned.get("NumberBombBoost");
        } else {
            number_bomb_remaining = number_initial_bombs;
        }
        if (bonus_owned.contains("SpeedBoost")) {
            number_move_remaining = number_initial_moves + bonus_owned.get("SpeedBoost");
        } else {
            number_move_remaining = number_initial_moves;
        }
    }

    // getters and setters
    public int getNumberBombRemaining() {
        return number_bomb_remaining;
    }

    public void setNumberBombRemaining(int number_bomb_remaining) {
        this.number_bomb_remaining = number_bomb_remaining;
    }

    // to use bombs
    public Bomb dropBomb(int drop_position_x, int drop_position_y){
        number_bomb_remaining-=1;
        return new Bomb(drop_position_x, drop_position_y, initial_bomb_range + bonus_owned.get("BombRangeBoost"));
    }
    //to loot bonus
    public void lootBonus(Bonus bonus) {
        if (this.bonus_owned.contains(bonus.getName())) {
            this.bonus_owned.put(bonus.getName(), bonus_owned.get(bonus.getName()) + 1);
        } else {
            this.bonus_owned.put(bonus.getName(), 1);
        }
    }
}
