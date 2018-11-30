package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.*;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class Maze {

    private String title;
    private int height;
    private int width;
    private Vector2[] position_start;
    private Vector2 position_end;
    private Cell[][] cells;
    private static Gson gson;
    private long seed; //défini les variations des textures
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>(); // contains all the bombs in the maze
    private int nb_player_max;



    /**
     * Constructor for the Maze class
     */
    public Maze() {
        if(gson==null) {
            createGson();
        }
        nb_player_max = Constants.config_file.getIntAttribute("nb_player_max");
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
        position_start[0]= new Vector2(0,1);
        position_start[1]= new Vector2(0,h-1);
        position_start[2]= new Vector2(w-1,0);
        position_start[3]= new Vector2(w-1,h-1);
        position_end = new Vector2(6,5);
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y);
                if (Math.random() < 0.1)
                    cells[x][y].addGameObject(new DestructibleWall(x, y));
                if (x % 2 == 1 && y % 2 == 1)
                    cells[x][y].addGameObject(new IndestructibleWall(x, y));
            }
        }
        nb_player_max = Constants.config_file.getIntAttribute("nb_player_max");
    }

    /**
     * Moves a GameObject with a relative delta
     * @param gameObject
     * @param dx
     * @param dy
     */
    public void moveGameObject(GameObject gameObject, int dx, int dy) {
        cells[gameObject.getPositionX()][gameObject.getPositionY()].removeGameObject(gameObject);
        gameObject.move(dx, dy);
        cells[gameObject.getPositionX()][gameObject.getPositionY()].addGameObject(gameObject);
    }


    /**
     * Puts a Bomb at a specified position in the Maze and adds it to the bombs ArrayList
     * @param bomb
     */
    public void addBomb(Bomb bomb)
    {
        cells[bomb.getPositionX()][bomb.getPositionY()].addGameObject(bomb);
        bombs.add(bomb);
    }

    /**
     * returns a GameObject at a specified poition in the maze
     * @param cell_x
     * @param cell_y
     * @return null if there's no GameObject at the specified position, else it returns the GameObject
     */
    public ArrayList<GameObject> getGameObjectsAt(int cell_x, int cell_y)
    {
            return getCellAt(cell_x, cell_y).getObjects();
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
     * Getter for nb_player_max variable
     * @return nb_playermax
     */
    public int getNb_player_max() {
        return nb_player_max;
    }

    /**
     * Créer les joueurs dans le labyrynthe aux positions de départs
     * @return une liste des instances de classe des joueurs créés
     */
    public Player[] spawnPlayers() {
        Player[] players = new Player[nb_player_max];
        String[] players_skins = {Constants.config_file.getStringAttribute("player1_skin"), Constants.config_file.getStringAttribute("player2_skin"), Constants.config_file.getStringAttribute("player3_skin"), Constants.config_file.getStringAttribute("player4_skin")};
        for (int i = 0; i < nb_player_max; i++) {
            Vector2 pos = position_start[i];
            players[i] = new Player((int) pos.x, (int) pos.y, players_skins[i]);
            cells[(int) pos.x][(int) pos.y].addGameObject(players[i]);
        }
        return players;
    }

    /**
     * Detonates all the bombs in the Maze and thus delete them from the bombs ArrayList
     */
    public void processEndTurn()
    {
        for (Bomb bomb : bombs) {
            bomb.explode(this);
            cells[bomb.getPositionX()][bomb.getPositionY()].removeGameObject(bomb);
        }
        bombs.clear();
    }

    /**
     * Returns if a given cell is walkable
     * @param cell_x
     * @param cell_y
     * @return
     */
    public boolean isWalkable(int cell_x, int cell_y)
    {
        if (!isCellInBounds(cell_x, cell_y)) {
            return false;
        }
        for(GameObject gameObject : getGameObjectsAt(cell_x, cell_y)){
            if(!gameObject.isWalkable())
                return false;
        }
        return true;
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
            return gson.fromJson(new FileReader(new File(Constants.PATH_MAZE+filename)), Maze.class);
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

    public Cell getCellAt(int x, int  y) {
        try {
            return cells[x][y];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Cell (" + x + ", " +  y + ") out of bounds");
        }
        return null;
    }
}
