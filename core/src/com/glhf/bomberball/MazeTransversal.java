package com.glhf.bomberball;

import com.glhf.bomberball.maze.Cell;

import java.util.ArrayList;
import java.util.LinkedList;

public class MazeTransversal {

    /**
     * Returns all cells whitin a specified range from a cell
     * @param cell_origin
     * @param range
     * @return cells whithin range of cell_origin
     * Breadth First Search algorithm (BFS) stopping at depth range
     */
    public static ArrayList<Cell> getCellsInRange(Cell cell_origin, int range) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        LinkedList<Cell> active_queue = new LinkedList<Cell>();
        LinkedList<Cell> inactive_queue = new LinkedList<Cell>();
        int depth = 0;
        cells.add(cell_origin);
        active_queue.add(cell_origin);
        // Invariant : Distance to all cells in the active queue is depth
        while (depth < range) {
            while (!active_queue.isEmpty()) {
                Cell c = active_queue.poll();
                for (Cell other : c.getAdjacentCells()) {
                    if (!cells.contains(other)) {
                        inactive_queue.add(other);
                        cells.add(other);
                    }
                }
            }
            depth++;
            active_queue = inactive_queue;
            inactive_queue = new LinkedList<Cell>();
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
        ArrayList<Cell> cells = new ArrayList<Cell>();
        LinkedList<Cell> queue = new LinkedList<Cell>();
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

}
