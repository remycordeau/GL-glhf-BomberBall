package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.utils.Timer;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeTransversal;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.HunterNode;
import com.glhf.bomberball.utils.Node;

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
        this.way = this.longestWayMovesSequence(Enemy.constructWay(this.getCell()));
    }

    @Override
    public void updateAI() {
        if(cell!=null){// equivalent to isAlive()
            this.way = this.longestWayMovesSequence(Enemy.constructWay(this.getCell()));
        }
    }

    @Override
    public void followWay() {
        ArrayList<Cell> reachableCells = MazeTransversal.getReacheableCellsInRange(cell, hunting_range);
        ArrayList<GameObject> gos;
        for (Cell c : reachableCells) {
            gos = c.getGameObjects();
            for (GameObject go : gos) {
                if (go instanceof Player) {
                    way = shortestWay(this.cell, go.getCell());
                    actual_move = 0;
                }
            }
        }

        if (moves_remaining > 0 && !way.isEmpty()) {
            this.move(way.get(actual_move));
            actual_move = (actual_move+1)%way.size();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    followWay();
                }
            }, 0.1f);
        }
    }


    public ArrayList<Directions> shortestWay(Cell start, Cell target) {
        //TODO: à faire
        return null;
    }
}
