package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
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
    private GameObject[][] tab;
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
        position_start[0]= new Vector2(1,1);
        position_start[1]= new Vector2(1,10);
        position_start[2]= new Vector2(12,1);
        position_start[3]= new Vector2(12,10);
        position_end = new Vector2(6,5);
        tab = new GameObject[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.random() < 0.1)
                    tab[x][y] = new DestructibleWall(x, y);
                if (x % 2 == 1 && y % 2 == 1)
                    tab[x][y] = new IndestructibleWall(x, y);
            }
        }
        tab[0][0] = new Bomb(0,0,1);
        tab[0][1] = new ActiveEnemy(0, 1);
        nb_player_max = Constants.config_file.getIntAttribute("nb_player_max");
    }

    /**
     * Moves a GameObject with a relative delta
     * @param gameObject
     * @param dx
     * @param dy
     */
    public void moveGameObject(GameObject gameObject, int dx, int dy)
    {
        tab[gameObject.getPositionX()][gameObject.getPositionY()] = null;
        gameObject.move(dx, dy);
        tab[gameObject.getPositionX()][gameObject.getPositionY()] = gameObject;
    }



    /**
     * Puts a Bomb at a specified position in the Maze and adds it to the bombs ArrayList
     * @param b
     */
    public void addBomb(Bomb b)
    {
        tab[b.getPositionX()][b.getPositionY()] = b;
        bombs.add(b);
    }

    /**
     * returns a GameObject at a specified poition in the maze
     * @param cell_x
     * @param cell_y
     * @return null if there's no GameObject at the specified position, else it returns the GameObject
     */
    public GameObject getGameObjectAt(int cell_x, int cell_y)
    {
        GameObject gameObject = null;
        try {
            gameObject = tab[cell_x][cell_y];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Cell (" + cell_x + ", " + cell_y + ") out of bounds");
        }
        return gameObject;
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
            tab[(int) pos.x][(int) pos.y] = players[i];
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
            tab[bomb.getPositionX()][bomb.getPositionY()] = null;
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
        GameObject gameObject = getGameObjectAt(cell_x, cell_y);
        return gameObject == null || gameObject.isWalkable();
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
     * Removes a given GameObject in the maze if it hasn't any life left
     * @param gameObject
     */
    public void handleGameObjectDamage(GameObject gameObject)
    {
        if (gameObject != null && !gameObject.isAlive()) {
            tab[gameObject.getPositionX()][gameObject.getPositionY()] = null;
        }
    }


    public static Maze fromJsonFile(String filename) {
        if(gson==null)createGson();
        try {
            return gson.fromJson(new FileReader(new File(Constants.PATH_MAZE+filename)), Maze.class);
        } catch (FileNotFoundException e) { throw new RuntimeException("ERROR : "+e.getMessage()); }
    }

    public void toJsonFile(String filename)
    {
        try {
            Writer writer = new FileWriter(new File(Constants.PATH_MAZE + filename));
            writer.write(this.toJson());
            writer.close();
            System.out.println(this.toJson());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static Maze fromJson(String str) {
        return gson.fromJson(str, Maze.class);
    }

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
