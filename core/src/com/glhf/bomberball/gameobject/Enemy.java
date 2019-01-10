package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.Node;

import java.util.ArrayList;

public abstract class Enemy extends Character {

    protected int strength = 1;

    //protected transient  int actual_move; //transcient to deserialize
    protected transient ArrayList<Directions> way;

    protected int actual_move; //current index of path followed

    protected Enemy(String skin, int life, int initial_moves, int strength) {
        super(skin, life, initial_moves);
        this.strength = strength;
    }

    /**
     * set damage to a player that the enemy touch
     * @param player the player touched by the ennemy
     */
    public void touchPlayer(Player player){
        player.getDamage(strength);
    }

    /**
     * give a way to follow to the enemy
     * @param way
     */
    public void setWay(ArrayList<Directions> way) {
       this.way = way;
    }

    /**
     * the enemy has to follow the way he receveid
     */
    public void followWay() {
        this.move(way.get(actual_move));
        actual_move += 1;
    }

    public abstract void createAI();

    /**
     * this method gives the "root" of a kind of tree which represents the different available ways from an initial position
     * @param ancestors
     * @param initial_position
     * @return Node
     */
    public static Node construct_ways(ArrayList<Cell> ancestors, Cell initial_position){
        int i;
        Node ways = new Node(ancestors, initial_position); // the node doesn't have ancestors
        Directions direction = Directions.UP;
        Cell current_adjacent_cell;
        //if ancestor is null create en empty family
        ArrayList<Cell> family = (ancestors==null ? new ArrayList<>() : new ArrayList<>(ancestors));
        family.add(ways.getMatching_cell());
        for(i=0; i<4; i++){ // because, all cells have 4 adjacent cells
            // stop condition no walkable adjacent cell or all adjacent way in acestors
            switch (i){
                case 0 : direction = Directions.UP;
                    break;
                case 1 : direction = Directions.RIGHT;
                    break;
                case 2 : direction = Directions.DOWN;
                    break;
                case 3 : direction = Directions.LEFT;
                    break;
            }
            current_adjacent_cell = ways.getMatching_cell().getAdjacentCell(direction);
            //stop condition
            if(current_adjacent_cell==null
                    || !current_adjacent_cell.isWalkable()
                    || (ways.getAncestors()!=null && ways.getAncestors().contains(current_adjacent_cell))){
                ways.setSons(null, i);
            }
            else{
                ways.setSons(construct_ways(family, current_adjacent_cell), i);
            }
        }
        return ways;
    }

    /**
     * this method gives the longest way that the active enemy will folow, chose the longest path
     * @param initial_node
     * @return ArrayList<Cell> a path
     */
    public ArrayList<Cell> longest_way(Node initial_node){
        ArrayList<Cell> longest = new ArrayList<>();
        ArrayList<Cell> current = new ArrayList<>();
        if(initial_node != null) {
            for(int i=0; i<4; i++){
                current = longest_way(initial_node.getSons(i));
                if (current != null && current.size() > longest.size()) {
                    longest = current;
                }
            }
            longest.add(0, initial_node.getMatching_cell());
        }
        return longest;
    }

    /**
     * gives the sequence of moves that the active enemy has to follow, if its a cycle from the initial position, then
     * does the cycle or revert his own path otherwise.
     * @param initial_node
     * @return ArrayList<Directions>
     */
    public ArrayList<Directions> longest_way_moves_sequence(Node initial_node){
        ArrayList<Directions> moves_sequence = new ArrayList<>();
        ArrayList<Directions> moves_sequence_miror = new ArrayList<>();
        ArrayList<Cell> longest_way = new ArrayList<>();
        longest_way = this.longest_way(initial_node);
        int longest_way_size = longest_way.size();
        Directions next_direction;
        Directions last_direction;
        for(int i=0; i< longest_way_size-1; i++){
            next_direction = longest_way.get(i).getCellDir(longest_way.get(i+1));
            moves_sequence.add(next_direction);
            moves_sequence_miror.add(0, Directions.values()[(next_direction.ordinal()+2)%4]);
        }
        last_direction = longest_way.get(longest_way_size-1).getCellDir(initial_node.getMatching_cell());
        if(longest_way.get(longest_way_size-1).getCellDir(initial_node.getMatching_cell()) != null){
            moves_sequence.add(last_direction);
        }
        else{
            moves_sequence.addAll(moves_sequence_miror);
        }
        return moves_sequence;
    }
}
