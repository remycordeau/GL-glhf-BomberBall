package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;

import java.util.Hashtable;

public class Player extends Character {
    //attributes
    private int number_bomb_remaining;
    private int number_initial_bombs;
    private int initial_bomb_range;
    //bonus owned
    private Hashtable<String, Integer> bonus_owned;


    /**
     * constructor
     * @param position_x x axis initial position
     * @param position_y y axis initial position
     * @param player_skin path to the player sprites
     */
    public Player(int position_x, int position_y, String player_skin) {
        super(position_x, position_y);
        life = Constants.config_file.getIntAttribute("player_life");
        number_initial_bombs = Constants.config_file.getIntAttribute("number_initial_bomb");
        initial_bomb_range = Constants.config_file.getIntAttribute("initial_bomb_range");
        bonus_owned = new Hashtable<String, Integer>();
        bonus_owned.put("NumberBombBoost", 0);
        bonus_owned.put("SpeedBoost", 0);
        setAnimation(player_skin+"/idle");
    }


    /**
     * sprite getter
     * @return the sprite of the player
     */
    @Override
    public AtlasRegion getSprite()
    {
        return animation.getKeyFrame(Game.time_elapsed);
    }

    /**
     * initiate the turn of the player, initialize the number of bombs and the number of moves
     */
    @Override
    public void initiateTurn() {
        number_bomb_remaining = number_initial_bombs + bonus_owned.get("NumberBombBoost");
        number_move_remaining = number_initial_moves + bonus_owned.get("SpeedBoost");
    }

    /**
     *
     * @return number_bomb_remaining
     */
    public int getNumberBombRemaining() {
        return number_bomb_remaining;
    }

    /**
     *
     * @param number_bomb_remaining
     */
    public void setNumberBombRemaining(int number_bomb_remaining) {
        this.number_bomb_remaining = number_bomb_remaining;
    }

    /**
     * The player create a bomb and put it on the square given
     * @param drop_position_x x axis position of the bomb
     * @param drop_position_y y axis position of the bomb
     * @return a new Bomb
     */
    public Bomb dropBomb(int drop_position_x, int drop_position_y){
        number_bomb_remaining-=1;
        return new Bomb(drop_position_x, drop_position_y, initial_bomb_range + bonus_owned.get("BombRangeBoost"));
    }

    /**
     * The player loot the bonus and add it to its bonus_owned attribute
     * @param bonus the bonus to loot
     */
    public void lootBonus(Bonus bonus) {
        if (this.bonus_owned.contains(bonus.getName())) {
            this.bonus_owned.put(bonus.getName(), bonus_owned.get(bonus.getName()) + 1);
        } else {
            this.bonus_owned.put(bonus.getName(), 1);
        }
    }
}
