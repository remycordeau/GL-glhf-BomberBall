package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.gameobject.Void;
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
    private transient ArrayList<Bomb> bombs = new ArrayList<Bomb>(); //transient permet d'éviter d'écrire l'objet bomb dans le fichier json

    public Maze() {
        if(gson==null) {
            createGson();
        }
    }

    /**
     *
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
        tab = new GameObject[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x % 2 == 1 && y % 2 == 1)
                    tab[x][y] = new IndestructibleWall(x, y, 1);
                else if (Math.random() < 0.1)
                    tab[x][y] = new DestructibleWall(x, y, 1);
                else
                    tab[x][y] = new Void(x, y);
            }
        }
    }

    /**
     * cette fonction échange deux game objets
     * @param gameObject l'objet qui va être dépacer de (dx, dy)
     * @param dx deplacement en x
     * @param dy deplacement en y
     */
    public void moveGameObject(GameObject gameObject, int dx, int dy) {
        int x = gameObject.getPositionX(), y = gameObject.getPositionY();
        tab[x][y] = tab[x+dx][y+dy];
        tab[x][y].move(-dx, -dy);
        gameObject.move(dx, dy);
        tab[x+dx][y+dy] = gameObject;
    }

    public void putBomb(Bomb bomb)
    {
        int cell_x = bomb.getPositionX();
        int cell_y = bomb.getPositionY();
        try {
            if(tab[cell_x][cell_y] instanceof Void) {
                tab[cell_x][cell_y] = bomb;
                bombs.add(bomb);
            }
        } catch (ArrayIndexOutOfBoundsException e){ }
    }

    public GameObject getGameObjectAt(int cell_x, int cell_y)
    {
        GameObject gameObject = new Void(cell_x, cell_y);
        try {
            gameObject = tab[cell_x][cell_y];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Cell (" + cell_x + ", " + cell_y + ") out of bounds");
        }
        return gameObject;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Créer les joueurs dans le labyrynthe aux positions de départs
     * @param life correspond à la vie initial de chacun des joueur
     * @return une liste des instances de classe des joueurs créés
     */
    public Player[] spawnPlayers(int life) {
        Player[] players = new Player[4];
        String[] players_skins = {"knight_m", "knight_f", "elf_m", "wizzard_f"};
        for (int i = 0; i < Constants.NB_PLAYER_MAX; i++) {
            Vector2 pos = position_start[i];
            players[i] = new Player((int) pos.x, (int) pos.y, players_skins[i]);
            tab[(int) pos.x][(int) pos.y] = players[i];
        }
        return players;
    }

    public void processEndTurn()
    {
        for (Bomb bomb : bombs) {
            bomb.explode(this);
            tab[bomb.getPositionX()][bomb.getPositionY()] = new Void(bomb.getPositionX(),bomb.getPositionY());
        }
        bombs.clear();
    }

    public boolean isWalkable(int cell_x, int cell_y)
    {
        if (!isCellInBounds(cell_x, cell_y)) {
            return false;
        }
        GameObject gameObject = getGameObjectAt(cell_x, cell_y);
        return gameObject.isWalkable();
    }

    private boolean isCellInBounds(int cell_x, int cell_y)
    {
        return cell_x >= 0 && cell_x < width && cell_y >= 0 && cell_y < height;
    }

    public void handleGameObjectDamage(GameObject gameObject)
    {
        if (!gameObject.isAlive()) {
            tab[gameObject.getPositionX()][gameObject.getPositionY()] = new Void(gameObject.getPositionX(),gameObject.getPositionY());
        }
    }

    // destruction of GameObject when dead
    public void handleDestruction(){
        int i, j;
        GameObject gameobject;
        for(i=0; i>-getHeight(); i--) {
            for(j=0; j<getWidth(); j++) {
                gameobject = getGameObjectAt(i,j);
                if(!gameobject.isAlive()){
                    tab[i][j]= new Void(i,j);
                }
            }
        }
    }

    public static Maze fromJsonFile(String filename) {
        if(gson==null)createGson();
        try {
            return gson.fromJson(new FileReader(new File(Constants.PATH_MAZE+filename)), Maze.class);
        } catch (FileNotFoundException e) { throw new RuntimeException("ERROR : "+e.getMessage()); }
    }

    public void toJsonFile(String filename){
        if(gson==null)createGson();
        try {
            Writer writer = new FileWriter(new File(Constants.PATH_MAZE + filename));
            writer.write(this.toJson());
            writer.close();
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
