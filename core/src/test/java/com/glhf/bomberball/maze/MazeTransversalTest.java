package com.glhf.bomberball.maze;

import com.glhf.GdxTestRunner;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.VectorInt2;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//@RunWith(GdxTestRunner.class)
public class MazeTransversalTest {

    private Maze mazeVide10x10;
    private Maze mazeVide15x10;

    @BeforeClass
    public static void create(){
//        Graphics.load();
//        Translator.load("fr");
    }
    
    @Before
    public void setUp() throws Exception {
        mazeVide10x10 = new Maze(10,10);
        mazeVide15x10 = new Maze(15,10);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getReacheableCellsInRange() {
    }

    @Test
    public void getReacheableCells() {

        ArrayList<Cell> list;
        list = MazeTransversal.getReacheableCells(mazeVide10x10.getCellAt(0,0));
        assertEquals(100, list.size());

        list = MazeTransversal.getReacheableCells(mazeVide15x10.getCellAt(0,0));
        assertEquals(150, list.size());
    }

    @Test
    public void isReachableCell() {
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(0,0), mazeVide10x10.getCellAt(9,9)));
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(0,0), mazeVide10x10.getCellAt(0,9)));
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(0,0), mazeVide10x10.getCellAt(0,0)));
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(9,9), mazeVide10x10.getCellAt(9,0)));

        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(9,9), null));
    }

    @Test
    public void getRandomPath() {
        ArrayList<Directions> path = MazeTransversal.getRandomPath(mazeVide10x10.getCellAt(0, 0));
        VectorInt2 diff = new VectorInt2(0,0);
        for(Directions d : path){
            switch (d){
                case UP: diff.y++; break;
                case DOWN: diff.y--; break;
                case LEFT: diff.x--; break;
                case RIGHT: diff.x++; break;
            }
        }
        assertEquals(diff, new VectorInt2(0,0));//the path should be a loop
    }

    @Test
    public void constructWay() {
    }

    @Test
    public void longestWay() {
    }

    @Test
    public void longestWayMovesSequence() {
    }

    @Test
    public void shortestPath() {
    }
}