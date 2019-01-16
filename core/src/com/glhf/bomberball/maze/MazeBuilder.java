package com.glhf.bomberball.maze;

import com.glhf.bomberball.config.GameInfiniteConfig;
import com.glhf.bomberball.config.GameStoryConfig;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.gameobject.Bonus.Type;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.Directions;
import com.glhf.bomberball.utils.VectorInt2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class MazeBuilder {
    private static LinkedHashMap<Class<? extends Wall>, Double> availableWall;
    private static Maze maze;
    private static Random rand = new Random();
    private static Cell cellDoor;
    private static double probFreeCase;
    private static int nb_ennemies = 10;
    private static GameInfiniteConfig config;

    public static Maze createInfinityMaze(){
        config = GameInfiniteConfig.get();
        probFreeCase = config.probFreeCase;

        maze = new Maze();

        maze.title = "Classic";
        maze.height = 5 + (int) (Math.random()*3)*2;
        maze.width = 7 + (int) (Math.random()*3)*2;

        maze.spawn_positions = new ArrayList<>();
        maze.spawn_positions.add(new VectorInt2(0, rand.nextInt(maze.height)));

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
        initialiseEnemies();


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

    private static void initialiseEnemies() {
        for(int i = 0; i< nb_ennemies; i++){
            Cell cell;
            int x;
            int y;
            do {
                x = rand.nextInt(maze.width);
                y = rand.nextInt(maze.height);
                cell = maze.cells[x][y];
            }while (!cell.isEmpty() || maze.spawn_positions.get(0).equals(x,y));
            float r = rand.nextFloat();
            if (r < 0.7) {
                ArrayList<Directions> ways = MazeTransversal.getRandomPath(cell);
                cell.addGameObject(new PassiveEnemy("skelet", config.passiveEnemy_life, config.passiveEnemy_moves, config.passiveEnemy_strength, ways));
            } else if (r < 0.9) {
                cell.addGameObject(new ActiveEnemy("swampy", config.activeEnemy_life, config.activeEnemy_moves, config.activeEnemy_strength));
            } else if (r < 1) {
                cell.addGameObject(new AggressiveEnemy("wogol", config.aggressiveEnemy_life, config.aggressiveEnemy_moves, config.aggressiveEnemy_strength, 5));
            }
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
        } catch (InstantiationException | IllegalAccessException e) {
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
