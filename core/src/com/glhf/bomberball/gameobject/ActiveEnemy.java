package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.maze.MazeTransversal;

public class ActiveEnemy extends Enemy {

    public ActiveEnemy(String skin, int life, int initial_moves, int strength) {
        super(skin, life, initial_moves, strength);
    }

    public ActiveEnemy(){
        super();
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
