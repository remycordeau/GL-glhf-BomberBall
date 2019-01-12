package com.glhf.bomberball.utils;

import com.glhf.bomberball.maze.cell.Cell;

public class HunterNode {
    //attributes
    int x,y,cost,heuristic;

    public HunterNode(Cell cell){
        this.x = cell.getX();
        this.y = cell.getY();
        this.cost = 0;
        this.heuristic = 0;
    }

    public int getHeuristic() {
        return heuristic;
    }
}
