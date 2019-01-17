package com.glhf.bomberball.maze;

import com.glhf.bomberball.gameobject.IndestructibleWall;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;

import java.lang.reflect.Array;
import java.util.*;

public class MazeTransversal{

    private static Random rand = new Random();

    /**
     * Returns all reacheable cells whitin a specified range from a cell
     * @param cell_origin
     * @param range
     * @return reacheable cells whithin range of cell_origin
     * Breadth First Search algorithm (BFS) stopping at depth range
     */
    public static ArrayList<Cell> getReacheableCellsInRange(Cell cell_origin, int range) {
        ArrayList<Cell> cells = new ArrayList<>();
        LinkedList<Cell> active_queue = new LinkedList<>();
        LinkedList<Cell> inactive_queue = new LinkedList<>();
        int depth = 0;
        cells.add(cell_origin);
        active_queue.add(cell_origin);
        // Invariant : Distance to all cells in the active queue is depth
        while (depth < range) {
            while (!active_queue.isEmpty()) {
                Cell c = active_queue.poll();
                for (Cell other : c.getAdjacentCells()) {
                    if (!cells.contains(other) && other.isWalkable()) {
                        inactive_queue.add(other);
                        cells.add(other);
                    }
                }
            }
            depth++;

            active_queue = inactive_queue;
            inactive_queue = new LinkedList<>();
        }
        return cells;
    }

    /**
     * Returns all cells reachable from a cell
     * @param cell_origin
     * @return cells reachable from cell_origin
     * Breadth First Search algorithm (BFS)
     */
    public static ArrayList<Cell> getReacheableCells(Cell cell_origin) {
        ArrayList<Cell> cells = new ArrayList<>();
        LinkedList<Cell> queue = new LinkedList<>();
        cells.add(cell_origin);
        queue.add(cell_origin);
        while (!queue.isEmpty()) {
            Cell c = queue.poll();
            for (Cell other : c.getAdjacentCells()) {
                if (!cells.contains(other)) {
                    queue.add(other);
                    cells.add(other);
                }
            }
        }
        return cells;
    }

    public static boolean isReachableCell(Cell cell_origin, Cell cell_final){
        ArrayList<Cell> cells = new ArrayList<>();
        LinkedList<Cell> queue = new LinkedList<>();
        cells.add(cell_origin);
        queue.add(cell_origin);
        while (!queue.isEmpty()) {
            Cell c = queue.poll();
            if (c.getX() == cell_final.getX() && c.getY() == cell_final.getY()) {
                return true;
            }
            for (Cell other : c.getAdjacentCells()) {
                if(other.getInstancesOf(IndestructibleWall.class).size() == 0){
                    if (!cells.contains(other)) {
                        queue.add(other);
                        cells.add(other);
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<Directions> getRandomPath(Cell cell) {
        ArrayList<Directions> path = new ArrayList<>();
        for(int i=0; i<10; i++){
            List<Directions> dirs = Arrays.asList(Directions.values());
            Collections.shuffle(dirs);
            Cell adjCell=null;
            for(Directions dir : dirs) {
                adjCell = cell.getAdjacentCell(dir);
                if (adjCell!=null && adjCell.isWalkable()){
                    path.add(dir);
                    break;
                }
            }
            if(adjCell==null || !adjCell.isWalkable()){//if all adjacent cell are not walkable
                return path;//return empty path
            }
            cell=adjCell;
        }
        //need to add the return path
        ArrayList<Directions> return_path = new ArrayList<>(path);
        Collections.reverse(return_path);
        for(int i=0; i<return_path.size(); i++){
            return_path.set(i, return_path.get(i).opposite());
        }
        path.addAll(return_path);
        return path;
    }
}
