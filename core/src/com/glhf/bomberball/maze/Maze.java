package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.config.Config;
import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.gameobject.*;
import com.google.gson.*;

import java.io.*;

public class Maze {

    private String title;
    private int height;
    private int width;
    private Vector2[] position_start;
    private Cell[][] cells;

    private GameConfig config;
    private static Gson gson;

    public Maze() {
        if(gson == null) {
            createGson();
        }
    }

    public Maze(int h, int w) {
        super();
        title = "Classic";
        height = h;
        width = w;
        position_start = new Vector2[4];
        position_start[0]= new Vector2(0,0);
        position_start[1]= new Vector2(0,h-1);
        position_start[2]= new Vector2(w-1,0);
        position_start[3]= new Vector2(w-1,h-1);
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
                if (x % 2 == 1 && y % 2 == 1) {
                    cells[x][y].addGameObject(new IndestructibleWall());
                }
                else if (Math.random() < 0.1) {
                    cells[x][y].addGameObject(new DestructibleWall());
                }
            }
        }
        setupCells();
    }

    public void setupCells()
    {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].init(getCellAt(x+1,y), getCellAt(x,y+1), getCellAt(x-1,y), getCellAt(x,y-1));
            }
        }
    }

    /**
     * Getter for the variable height
     * @return an integer
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for the variable width
     * @return an integer
     */
    public int getWidth() {
        return width;
    }

    /**
     * Créer les joueurs dans le labyrynthe aux positions de départs
     * @return une liste des instances de classe des joueurs créés
     */
    public Player[] spawnPlayers(GameMultiConfig config) {

        Player[] players = new Player[config.player_count];
        String[] players_skins = config.player_skins;
        for (int i = 0; i < config.player_count; i++) {
            Vector2 pos = position_start[i];
            players[i] = new Player(players_skins[i], config.player_life, config.initial_player_moves, config.initial_bomb_number, config.initial_bomb_range);
            cells[(int) pos.x][(int) pos.y].addGameObject(players[i]);
        }
        return players;
    }

    /**
     * Detonates all the bombs in the Maze and thus delete them from the bombs ArrayList
     */
    public void processEndTurn()
    {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].processEndTurn();
            }
        }
    }

    public Cell getCellAt(int x, int  y) {
        if(isCellInBounds(x, y))
            return cells[x][y];
        return null;
    }

    /**
     * tests if a given position is in the maze
     * @param cell_x
     * @param cell_y
     * @return true if the position is in the maze, else it returns false
     */
    private boolean isCellInBounds(int cell_x, int cell_y)
    {
        return cell_x >= 0 && cell_x < width && cell_y >= 0 && cell_y < height;
    }

    private static void createGson() {
        gson = new GsonBuilder()
                .registerTypeAdapter(GameObject.class, new MazeTypeAdapter())
                .setPrettyPrinting()
                .create();
    }

    public static Maze importMaze(String name) {
        if(gson==null) {
            createGson();
        }
        try {
            Maze maze = gson.fromJson(new FileReader(new File(Constants.PATH_MAZE + name + ".json")), Maze.class);
            maze.setupCells();
            return maze;
        } catch (FileNotFoundException e) { throw new RuntimeException("ERROR : "+e.getMessage()); }
    }

    public void export(String name)
    {
        if(gson == null) {
            createGson();
        }
        try {
            Writer writer = new FileWriter(new File(Constants.PATH_MAZE + name + ".json"));
            writer.write(this.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return gson.toJson(this);
    }
}
