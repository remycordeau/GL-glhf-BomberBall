package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.Cell;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.menu.DIRECTIONS;

import java.util.ArrayList;

public class Bomb extends GameObject {
    // attributes
    private int damage;
    private int range;

    public Bomb()
    {

    }

    /**
     * constructor
     * @param range number of squares reached by the bomb in the four directions ( north, east, sout, west)
     * @return Bomb
     */
    public Bomb(int range) {
        // initially, bomb inflict 1 getDamage
        this.damage = Constants.config_file.getIntAttribute("bomb_damage");
        this.range = range;
        this.life = 1;
        sprite = Graphics.Sprites.get("bomb");
    }

    public void explode() {
        cell.explode(null, damage, 0);
        for (DIRECTIONS dir : DIRECTIONS.values()) {
            Cell adjacent_cell = cell.getAdjacentCell(dir);
            if (adjacent_cell != null) {
                adjacent_cell.explode(dir, damage, range);
            }
        }
        cell.removeGameObject(this);
    }

    @Override
    public void getDamage(int damage)
    {

    }
}
