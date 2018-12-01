package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.maze.Cell;
import com.glhf.bomberball.menu.DIRECTIONS;

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
     * @param player_skin path to the player sprites
     */
    public Player(String player_skin) {
        super();
        life = Constants.config_file.getIntAttribute("player_life");
        number_initial_bombs = Constants.config_file.getIntAttribute("number_initial_bomb");
        initial_bomb_range = Constants.config_file.getIntAttribute("initial_bomb_range");
        bonus_owned = new Hashtable<String, Integer>();
        bonus_owned.put("NumberBombBoost", 0);
        bonus_owned.put("SpeedBoost", 0);
        bonus_owned.put("BombRangeBoost", 0);
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
        super.initiateTurn();
        number_initial_moves += bonus_owned.get("SpeedBoost");
        number_bomb_remaining = number_initial_bombs + bonus_owned.get("NumberBombBoost");
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

    @Override
    public boolean move(DIRECTIONS dir)
    {
        if (number_move_remaining > 0 && super.move(dir)) {
            number_move_remaining--;
            return true;
        }
        return false;
    }

    /**
     * The player create a bomb and put it on the square given
     * @param dir
     * @return a new Bomb
     */
    public void dropBomb(DIRECTIONS dir) {
        if (number_bomb_remaining > 0) {
            Cell dest_cell = cell.getAdjacentCell(dir);
            if (dest_cell != null && dest_cell.isWalkable()) {
                number_bomb_remaining--;
                Bomb bomb = new Bomb(initial_bomb_range + bonus_owned.get("BombRangeBoost"));
                dest_cell.addGameObject(bomb);
            }
        }
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

    @Override
    public boolean isWalkable(){
        return true;
    }
}
