package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.gameobject.Bonus.Type;
import com.glhf.bomberball.maze.cell.Cell;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class MazeBuilder {
    private static LinkedHashMap<Class<? extends Wall>, Double> availableWall;
    private static Maze maze;
    private static Random rand = new Random();
    private static Cell cellDoor;
    private static double probFreeCase;

    public static Maze createInfinityMaze(){
        probFreeCase = GameSoloConfig.get("config_game_solo").probFreeCase;

        maze = new Maze();

        maze.title = "Classic";
        maze.height = 7 + (int) (Math.random()*3)*2;
        maze.width = 9 + (int) (Math.random()*3)*2;

        maze.spawn_positions = new ArrayList<Vector2>();
        maze.spawn_positions.add(new Vector2(0, rand.nextInt(maze.height)));

        //TODO: ajout des ennemis à refaire
        maze.enemy_spawn_positions = new ArrayList<>();
//        maze.enemy_spawn_positions.add(new Vector2(0, 0));

        GameSoloConfig config = GameSoloConfig.get();
        availableWall = new LinkedHashMap<>();
        availableWall.put(IndestructibleWall.class, 4.0);
        availableWall.put(DestructibleWall.class, 4.0);
        if(config.bonus_activated){
            availableWall.put(BonusWall.class, 1.0);
        }

        maze.cells = new Cell[maze.width][maze.height];
        initialiseCells();
        cellDoor = maze.getCellAt(maze.width-1,rand.nextInt(maze.height));
        maze.cells[cellDoor.getX()][cellDoor.getY()].addGameObject(new Door());
        initialiseWalls();


        return maze;
    }

    private static void initialiseCells() {
        normalizeProba();

        for (int x = 0; x < maze.width; x++) {
            for (int y = 0; y < maze.height; y++) {
                maze.cells[x][y] = new Cell(x, y);
            }
        }
        maze.initialize();
    }

    private static void initialiseWalls() {
        normalizeProba();

        for (int x = 0; x < maze.width; x++) {
            for (int y = 0; y < maze.height; y++) {
                if(maze.spawn_positions.get(0).dst(x,y)<=1) continue;
                if(x == cellDoor.getX() && (y == cellDoor.getY())){
                }
                else{
                     if(Math.random() < probFreeCase ) {
                         maze.cells[x][y].addGameObject(randomWall());
                     }
                }
            }
        }
        Cell originPos = maze.getCellAt(0, 0);
        if(!MazeTransversal.isReachableCell(originPos, cellDoor)){
            System.out.println("new Maze");
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
                    if(o instanceof BonusWall){
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
