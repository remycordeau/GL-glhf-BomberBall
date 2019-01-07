package com.glhf.bomberball.utils;

import com.glhf.bomberball.maze.cell.Cell;

import java.util.ArrayList;

public class Tree {

    private Node root;

    public Tree(Cell initial_cell){
        this.root = new Node(null, initial_cell);
    }

    private class Node{
        private ArrayList<Node> ancestors;
        // Node[0] for UP, 1 for RIGHT, 2 for DOWN, 3 for LEFT
        private Node[] sons = new Node[4];
        private Cell matching_cell;


        public Node(ArrayList<Node> ancestors, Cell matching_cell){
            this.ancestors = ancestors;
            this.matching_cell = matching_cell;
        }

        public Directions get_direction_from_ancestor(Cell ancestor){
            for(Directions d : Directions.values()){
                if(ancestor.getAdjacentCell(d) == matching_cell ){
                    return d;
                }
            }
            return null;
        }

        public Cell getMatching_cell(){
            return matching_cell;
        }

        public ArrayList<Directions> get_longest_way(){
            ArrayList<Directions> current_longest_way = new ArrayList<Directions>();
            current_longest_way.add(get_direction_from_ancestor(ancestors.get(ancestors.size()-1).getMatching_cell()));
            ArrayList<Directions> current_counting;
            boolean stop_condition = sons[0]==null && sons[1]==null && sons[2]==null && sons[3]==null;
            if(!stop_condition){
                for(int i=0; i<4; i++){
                    current_counting = sons[i].get_longest_way();
                    if(current_counting.size() >= current_longest_way.size()){
                        current_longest_way = current_counting;
                    }
                }
            }
            return current_longest_way;
        }
    }
    //TODO : construire le tree
    //TODO: move to enemy

    /*public Tree construct_ways(){
        int i;
        for(i=0; i<4; i++){ // because, all cells have 4 adjacent cells
           // stop condition no walkable adjacent cell or all adjacent way in acestors
        }
    }*/

}
