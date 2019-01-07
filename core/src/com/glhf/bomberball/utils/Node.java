package com.glhf.bomberball.utils;

import com.glhf.bomberball.maze.cell.Cell;

import java.util.ArrayList;

public class Node {

        private ArrayList<Cell> ancestors;
        // Node[0] for UP, 1 for RIGHT, 2 for DOWN, 3 for LEFT
        private Node[] sons;
        private Cell matching_cell;


        public Node(ArrayList<Cell> ancestors, Cell matching_cell){
            this.ancestors = ancestors;
            this.matching_cell = matching_cell;
            this.sons = new Node[4];
        }

        public void setSons(Node son, int index){
            this.sons[index] = son;
        }

        public ArrayList<Cell> getAncestors(){
            return ancestors;
        }

        public Node getSons(int index){return  sons[index];}

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
            current_longest_way.add(get_direction_from_ancestor(ancestors.get(ancestors.size()-1)));
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

