package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.gameobject.*;
import com.google.gson.*;

import java.io.*;

public class Maze {

    private String title;
    private int height;
    private int width;
    private Vector2[] position_start;
    private Vector2 position_end;
    private GameObject[][] tab;
    private static Gson gson;
    private long seed; //défini les variations des textures

    public Maze() {
        if(gson==null)createGson();
    }

    public GameObject getGameObjectAt(int pos_x,int pos_y)
    {
        return tab[pos_x][pos_y];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static Maze fromJsonFile(String filename) {
        if(gson==null)createGson();
        try {
            return gson.fromJson(new FileReader(new File(Constants.PATH_MAZE+filename)), Maze.class);
        } catch (FileNotFoundException e) { throw new RuntimeException("ERROR : "+e.getMessage()); }
    }

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
                    tab[x][y] = new DestructibleWall(x, y, 1);
                if (x % 2 == 1 && y % 2 == 1)
                    tab[x][y] = new IndestructibleWall(x, y, 1);
            }
        }
        tab[0][0] = new Bomb(0,0,1);
        tab[0][1] = new ActiveEnemy(0, 1, 1);
        if(gson==null)createGson();
    }

    public void toJsonFile(String filename) {
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

    /**
     * Créer les joueurs dans le labyrynthe aux positions de départs
     * @param life correspond à la vie initial de chacun des joueur
     * @return une liste des instances de classe des joueurs créés
     */
    public Player[] spawnPlayers(int life) {
        Player[] players = new Player[4];
        for (int i = 0; i < Constants.NB_PLAYER_MAX; i++) {
            Vector2 pos = position_start[i];
            players[i] = new Player((int) pos.x, (int) pos.y, life);
            tab[(int) pos.x][(int) pos.y] = players[i];
        }
        return players;
    }

    // destruction of GameObject when dead
    public void handleDestruction(){
        int i, j;
        for(i=0; i>-getHeight(); i--){
            for(j=0; j<getWidth(); j++){
                if(! getGameObjectAt(i,j).isAlive()){
                    tab[i][j]=null;
                }
            }
        }
    }
}
