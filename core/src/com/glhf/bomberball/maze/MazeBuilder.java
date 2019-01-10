package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.gameobject.Bonus.Type;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.VectorInt2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

public class MazeBuilder {
    private static LinkedHashMap<Class<? extends Wall>, Double> availableWall;
    private static Maze maze;
    private static Random rand = new Random();
    private static Cell posDoor;
    private static double probFreeCase;

    public static Maze createInfinityMaze(){
        probFreeCase = 1.0/3;

        maze = new Maze();

        maze.title = "Classic";
        maze.height = 9 + (int) (Math.random()*2)*2;
        maze.width = 7 + (int) (Math.random()*2)*2;

        ArrayList<Vector2> spawn_positions = new ArrayList<Vector2>();
        spawn_positions.add(new Vector2(0, rand.nextInt(maze.height)));
        maze.spawn_positions=spawn_positions;

        //TODO: ajout des ennemis à refaire
        maze.enemy_spawn_positions = new ArrayList<>();
        maze.enemy_spawn_positions.add(new Vector2(0, 0));

        GameSoloConfig config = GameSoloConfig.get();
        availableWall = new LinkedHashMap<>();
        availableWall.put(IndestructibleWall.class, 4.0);
        availableWall.put(DestructibleWall.class, 4.0);
        if(config.bonus_activated){
            availableWall.put(BonusWall.class, 1.0);
        }

        maze.cells = new Cell[maze.width][maze.height];
        initialiseCells();
        int yDoor = rand.nextInt(maze.height);
        maze.cells[maze.width-1][yDoor].addGameObject(new Door());
        initialiseWalls();

        maze.initialize();

        return maze;
    }

    private static void initialiseCells() {
        normalizeProba();

        for (int x = 0; x < maze.width; x++) {
            for (int y = 0; y < maze.height; y++) {
                maze.cells[x][y] = new Cell(x, y);
            }
        }
    }

    private static void initialiseWalls() {
        normalizeProba();

        for (int x = 0; x < maze.width; x++) {
            for (int y = 0; y < maze.height; y++) {
                if(maze.spawn_positions.get(0).dst(x,y)<=1) continue;
                posDoor = maze.getposDoor();
                if(x ==posDoor.getX() && (y == posDoor.getY())){
                }
                else{
                     if(Math.random() < probFreeCase ) {
                         maze.cells[x][y].addGameObject(randomWall());
                     }
                }
            }
        }

        //TODO : verification passage possible
        Cell originPos = new Cell(0, 0);
        if(maze.isReachableCell(originPos, posDoor)){
                maze = createInfinityMaze();
        }
    }

    private static Wall randomWall() {
        try {
            double range_start=0;
            double rand = Math.random();
            for(Class<? extends GameObject> c : availableWall.keySet()){
                double range_end = range_start + availableWall.get(c);
                if (rand < range_end) {
                    GameObject o = c.newInstance();
                    if(BonusWall.class.isInstance(o)){
                        Bonus b = new Bonus(Type.SPEED);
                        o = new BonusWall(b);
                    }
                    return (Wall)o;
                }
                range_start=range_end;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Error in randomWall");
    }

    private static void normalizeProba(){
        Double somme = 0.0;
        for (Double proba : availableWall.values()) {
            somme += proba;
        }
        for (Class<? extends Wall> key : availableWall.keySet()) {
            availableWall.put(key, availableWall.get(key)/somme);
        }
    }

}
