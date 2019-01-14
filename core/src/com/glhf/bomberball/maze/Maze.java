package com.glhf.bomberball.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.utils.Constants;
import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.maze.json.GameObjectTypeAdapter;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.utils.VectorInt2;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class Maze{

    String title;
    ArrayList<VectorInt2> spawn_positions;
    int height;
    int width;
    Cell[][] cells;

    private static Gson gson;

    public Maze() {
    }

    public Maze(int w, int h) {
        this.height = h;
        this.width = w;
        cells = new Cell[w][h];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
        initialize();
    }

    public void initialize()
    {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].initialize(getCellAt(x+1,y), getCellAt(x,y+1), getCellAt(x-1,y), getCellAt(x,y-1));
            }
        }
    }

    public Player spawnPlayer()
    {
        GameSoloConfig config = GameSoloConfig.get();
        VectorInt2 pos = spawn_positions.get(0);
        return spawnPlayer(config, config.player_skin, cells[pos.x][pos.y]);
    }

    public ArrayList<Player> spawnPlayers(int nb_player)
    {
        GameMultiConfig config = GameMultiConfig.get();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < nb_player; i++) {
            VectorInt2 pos = spawn_positions.get(i);
            Player player = spawnPlayer(config, config.player_skins[i], cells[pos.x][pos.y]);
            players.add(player);
        }
        return players;
    }

    private Player spawnPlayer(GameConfig config, String player_skin, Cell cell) {
        Player player = new Player(
                player_skin,
                config.player_life,
                config.initial_player_moves,
                config.initial_bomb_number,
                config.initial_bomb_range);
        cell.addGameObject(player);
        return player;
    }

    public Enemy spawnEnemy(GameSoloConfig config, Cell cell) {
        Enemy enemy = new ActiveEnemy("black_knight", config.activeEnemy_life, config.activeEnemy_moves, config.activeEnemy_strength);
        cell.addGameObject(enemy);
        return enemy;
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

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                players.addAll(cells[x][y].getInstancesOf(Player.class));
            }
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

    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for(Cell[] cell_column : cells){
            for(Cell cell : cell_column){
                for(GameObject o : cell.getGameObjects()){
                    if(o instanceof Enemy) enemies.add((Enemy) o);
                }
            }
        }
        return enemies;
    }


//    public void applyConfig(GameConfig config) {
//        ArrayList<GameObject> objects = new ArrayList<GameObject>();
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                objects.addAll(cells[x][y].getGameObjects());
//            }
//        }
//        for (Object o : objects) {
//            if (o instanceof Wall) {
//                ((Wall) o).setLife(config.wall_life);
//            }
//        }
//    }

    private static void createGson() {
        gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(GameObject.class, new GameObjectTypeAdapter())
                .setPrettyPrinting()
                .create();
    }

    public static Maze importMaze(String name) {
        if(gson==null) {
            createGson();
        }
        Maze maze = gson.fromJson(Gdx.files.internal(Constants.PATH_MAZE + name + ".json").readString(), Maze.class);
        maze.initialize();
        return maze;
    }

    public void export(String name)
    {
        if(gson == null) {
            createGson();
        }
        try {
            File file = Gdx.files.internal(Constants.PATH_MAZE + name + ".json").file();
            Writer writer = new FileWriter(file);
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
