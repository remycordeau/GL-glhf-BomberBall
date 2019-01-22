package com.glhf.bomberball.maze;

import com.glhf.GdxTestRunner;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class MazeTransversalTest {

    private Maze mazeVide10x10;
    private Maze mazeVide15x10;

    @BeforeClass
    public static void create(){
        Graphics.load();
        Translator.load("fr");

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
    /*
    @Test
    public void getReacheableCells() {

        ArrayList<Cell> list;
        list = MazeTransversal.getReacheableCells(mazeVide10x10.getCellAt(0,0));
        assertEquals(100, list.size());

        list = MazeTransversal.getReacheableCells(mazeVide15x10.getCellAt(0,0));
        assertEquals(150, list.size());

        Cell cell = mazeVide10x10.getCellAt(0,1);
        cell.addGameObject(new IndestructibleWall());
        list = MazeTransversal.getReacheableCells(mazeVide10x10.getCellAt(0,0));
        assertEquals(99, list.size());

        cell.removeGameObjects();
        cell.addGameObject(new DestructibleWall());
        list = MazeTransversal.getReacheableCells(mazeVide10x10.getCellAt(0,0));
        assertEquals(99, list.size());

        cell.removeGameObjects();
        cell.addGameObject(new Bomb(1,1));
        list = MazeTransversal.getReacheableCells(mazeVide10x10.getCellAt(0,0));
        assertEquals(99, list.size());

        cell.removeGameObjects();
        cell.addGameObject(new BonusWall(new Bonus(Type.SPEED)));
        list = MazeTransversal.getReacheableCells(mazeVide10x10.getCellAt(0,0));
        assertEquals(99, list.size());
    }
    */

    @Test
    public void isReachableCell() {
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(0,0), mazeVide10x10.getCellAt(9,9)));
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(0,0), mazeVide10x10.getCellAt(0,9)));
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(0,0), mazeVide10x10.getCellAt(0,0)));
        assertTrue(MazeTransversal.isReachableCell(mazeVide10x10.getCellAt(9,9), mazeVide10x10.getCellAt(9,0)));
    }

    @Test
    public void getRandomPath() {
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
    public void depth_graph_transversal() {
        MazeTransversal.depth_graph_transversal(mazeVide10x10.getCellAt(0,0),4);
    }

    @Test
    public void compareTwoNodes() {
    }

    @Test
    public void shortestPath() {
    }
}