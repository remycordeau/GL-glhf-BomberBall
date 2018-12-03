package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.maze.Cell;
import com.glhf.bomberball.menu.Directions;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;
import java.util.Hashtable;

public class Player extends Character {

    private int initial_bomb_number;
    private int initial_bomb_range;

    private transient int bombs_remaining;

    private transient Hashtable<String, Integer> bonus_owned;

    public Player(String player_skin,
                  int life,
                  int initial_moves,
                  int initial_bomb_number,
                  int initial_bomb_range)
    {
        super(player_skin, life, initial_moves);
        this.initial_bomb_number = initial_bomb_number;
        this.initial_bomb_range = initial_bomb_range;

        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        bonus_owned = new Hashtable<String, Integer>();
        bonus_owned.put("NumberBombBoost", 0);
        bonus_owned.put("SpeedBoost", 0);
        bonus_owned.put("BombRangeBoost", 0);
    }

    public void setInitialBombNumber(int initial_bomb_number) {
        this.initial_bomb_number = initial_bomb_number;
    }

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
        initial_moves += bonus_owned.get("SpeedBoost");
        bombs_remaining = initial_bomb_number + bonus_owned.get("NumberBombBoost");
        moves_remaining = initial_moves;
    }

    @Override
    public boolean move(Directions dir)
    {
        if (moves_remaining > 0 && super.move(dir)) {
            moves_remaining--;
            return true;
        }
        return false;
    }

    /**
     * The player create a bomb and put it on the square given
     * @param dir
     * @return a new Bomb
     */
    public void dropBomb(Directions dir) {
        if (bombs_remaining > 0) {
            Cell dest_cell = cell.getAdjacentCell(dir);
            if (dest_cell != null && dest_cell.isWalkable()) {
                bombs_remaining--;
                Bomb bomb = new Bomb(1, initial_bomb_range + bonus_owned.get("BombRangeBoost"));
                dest_cell.addGameObject(bomb);
            }
        }
    }

    /**
     * The player loot the bonus and add it to its bonus_owned attribute
     * @param bonus the bonus to loot
     */
    public void lootBonus(Bonus bonus) {
        bonus_owned.put(bonus.getName(), bonus_owned.get(bonus.getName()) + 1);
    }

    public void interactWithCell(Cell cell) {
        for (Bonus b : cell.getInstancesOf(Bonus.class)) {
            cell.removeGameObject(b);
            this.lootBonus(b);
        }
    }

    @Override
    public boolean isWalkable(){
        return true;
    }
}

