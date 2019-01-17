package com.glhf.bomberball.gameobject;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.Node;

import java.util.ArrayList;
import java.util.LinkedList;

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
        if (moves_remaining > 0 && !way.isEmpty()) {
            this.move(way.get(actual_move));
            actual_move = (actual_move+1)%way.size();
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    followWay();
                }
            }, 0.1f);
        }
    }


    @Override
    public void interactWithCell(Cell cell) {
        ArrayList<GameObject> goList = cell.getGameObjects();
        for (GameObject go : goList) {
            if (go instanceof Player) {
                go.getDamage(strength);
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////

    public abstract void createAI();

    public abstract void updateAI();

    /**
     * this method gives the "root" of a kind of tree which represents the different available ways from an initial position
     * @param initial_position
     * @return Node
     */
    /*public static Node constructWays(Cell initial_position) {
        Node ways = new Node(null, initial_position);
        LinkedList<Node> to_construct = new LinkedList<>();
        Node current_node;
        Node son;
        ArrayList<Cell> family;
        Cell current_adjacent_cell;
        //add the root to to_construct
        to_construct.add(ways);
        //constructing the tree
        while (to_construct.size() > 0) {
            current_node = to_construct.poll(); //get the next element to construct
            //create sons and adding them to to_construct
            for (Directions d : Directions.values()) {
                current_adjacent_cell = current_node.getMatching_cell().getAdjacentCell(d);
                family = current_node.getAncestors();
                //adding son
                if(current_adjacent_cell==null
                        || !current_adjacent_cell.isWalkable()
                        || (family !=null && family.contains(current_adjacent_cell))
                        || (family != null && family.size() > 30)){
                    current_node.setSons(null, d);
                }
                else{
                    //creating a son
                    family = family == null ? new ArrayList<>() : new ArrayList<>(family);
                    family.add(current_node.getMatching_cell());
                    son = new Node(family, current_adjacent_cell);
                    current_node.setSons(son, d);
                    //adding son to to_construct
                    to_construct.add(son);
                }
            }
        }
        return ways;
    }*/

    public Node constructWay(Cell initial_position) {
        Node way = new Node(null, null, initial_position);
        LinkedList<Node> to_construct = new LinkedList<>();
        Node current_node;
        Node son;
        ArrayList<Cell> family;
        Cell current_adjacent_cell;
        to_construct.add(way);
        while (to_construct.size() > 0) {
            current_node = to_construct.poll();
            for (Directions d : Directions.values()) {
                current_adjacent_cell = current_node.getMatching_cell().getAdjacentCell(d);
                family = current_node.getAncestors();
                if(current_adjacent_cell == null
                    || !current_adjacent_cell.isWalkable()
                    || (family != null && family.contains(current_adjacent_cell))) {
                    current_node.setSons(null, d);
                    current_node.sonIsSet();
                } else {
                    family = family == null ? new ArrayList<>() : new ArrayList<>(family);
                    family.add(current_node.getMatching_cell());
                    son = new Node(family, current_node, current_adjacent_cell);
                    current_node.setSons(son, d);
                    to_construct.add(son); //TODO: ajouter à une certaine place pour parcourire en profondeur
                }
            }
        }
        return way;
    }

    /**
     * this method gives the longest way that the active enemy will follow, chose the longest path
     * @param initial_node
     * @return ArrayList<Cell> a path
     */
    public ArrayList<Cell> longestWay(Node initial_node){
        ArrayList<Cell> longest = new ArrayList<>();
        ArrayList<Cell> current = new ArrayList<>();
        if(initial_node != null) {
            for(int i=0; i<4; i++){
                current = longestWay(initial_node.getSons(i));
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
    public ArrayList<Directions> longestWayMovesSequence(Node initial_node){
        ArrayList<Directions> moves_sequence = new ArrayList<>();
        ArrayList<Directions> moves_sequence_miror = new ArrayList<>();
        ArrayList<Cell> longest_way = new ArrayList<>();
        longest_way = this.longestWay(initial_node);
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
