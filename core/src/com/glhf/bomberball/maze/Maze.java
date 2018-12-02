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
    private Vector2 position_end;
    private Cell[][] cells;
    private static Gson gson;
    private long seed; //défini les variations des textures

    /**
     * Constructor for the Maze class
     */
    public Maze() {
        if(gson==null) {
            createGson();
        }
    }

    /**
     * Constructor of Maze
     * Generate a random Maze with a specific size
     * @param h hauteur
     * @param w largeur
     */
    public Maze(int h, int w) {
        title = "Classic";
        height = h;
        width = w;
        position_start = new Vector2[4];
        position_start[0]= new Vector2(0,0);
        position_start[1]= new Vector2(0,h-1);
        position_start[2]= new Vector2(w-1,0);
        position_start[3]= new Vector2(w-1,h-1);
        position_end = new Vector2(6,5);
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
                if (Math.random() < 0.1)
                    cells[x][y].addGameObject(new DestructibleWall());
                if (x % 2 == 1 && y % 2 == 1)
                    cells[x][y].addGameObject(new IndestructibleWall());
            }
        }
        init();
    }

    public void init()
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
            players[i] = new Player(players_skins[i], config.player_inital_moves, config.bomb_inital_number, config.bomb_inital_range);
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

    /**
     * Inititiate a Maze from a Json file
     * @param filename the name of the Json file
     * @return Return an instance of maze corresponding to the Json file
     */
    public static Maze fromJsonFile(String filename) {
        if(gson==null)createGson();
        try {
            Maze maze = gson.fromJson(new FileReader(new File(Constants.PATH_MAZE+filename)), Maze.class);
            maze.init();
            return maze;
        } catch (FileNotFoundException e) { throw new RuntimeException("ERROR : "+e.getMessage()); }
    }

    /**
     * Create a Json file from an instance of Maze
     * @param filename the name of the Json file
     */
    public void toJsonFile(String filename)
    {
        if(gson==null)createGson();
        try {
            Writer writer = new FileWriter(new File(Constants.PATH_MAZE + filename));
            writer.write(this.toJson());
            writer.close();
            System.out.println(this.toJson());
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Inititiate a Maze from a Json formatted String
     * @param jsonString the Json formatted String
     * @return Return an instance of maze corresponding to the Json formatted String
     */
    public static Maze fromJson(String jsonString) {
        return gson.fromJson(jsonString, Maze.class);
    }

    /**
     * Transform an instance of maze to a Json formatted String
     * @return a Json formatted String corresponding to the instance of Maze
     */
    public String toJson() {
        return gson.toJson(this);
    }

    private static void createGson() {
        gson = new GsonBuilder()
                .registerTypeAdapter(GameObject.class, new MazeTypeAdapter())
                .setPrettyPrinting()
                .create();
    }
}
