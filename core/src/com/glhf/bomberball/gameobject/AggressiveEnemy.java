package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.HunterNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class AggressiveEnemy extends Enemy {

    private int hunting_range;

    public AggressiveEnemy(String skin, int life, int initial_moves, int strength, int hunting_range) {
        super(skin, life, initial_moves, strength);
        // active mode when created
        this.hunting_range = hunting_range;
    }

    @Override
    public void createAI() {
        this.way = MazeTransversal.longestWayMovesSequence(MazeTransversal.constructWay(this.getCell()));
    }

    @Override
    public void updateAI() {
        if(cell!=null){// equivalent to isAlive()
            this.way = MazeTransversal.longestWayMovesSequence(MazeTransversal.constructWay(this.getCell()));
        }
    }
}
