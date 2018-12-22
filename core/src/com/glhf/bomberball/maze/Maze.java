package com.glhf.bomberball.maze;

import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.utils.Constants;
import com.glhf.bomberball.config.GameConfig;
import com.glhf.bomberball.config.GameMultiConfig;
import com.glhf.bomberball.config.GameSoloConfig;
import com.glhf.bomberball.gameobject.*;
import com.glhf.bomberball.maze.json.GameObjectTypeAdapter;
import com.glhf.bomberball.maze.cell.Cell;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class Maze {

    private String title;
    private ArrayList<Vector2> spawn_positions;
    private int players_count;
    private int height;
    private int width;
    private Cell[][] cells;

    private transient GameConfig config;
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
        cells = new Cell[width][height];
        spawn_positions = new ArrayList<Vector2>(){};
        spawn_positions.add(new Vector2(0, 0));
        spawn_positions.add(new Vector2(0, h - 1));
        spawn_positions.add(new Vector2(w - 1, 0f));
        spawn_positions.add(new Vector2(w - 1, h - 1));
        players_count = spawn_positions.size();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
                if (x % 2 == 1 && y % 2 == 1) {
                    cells[x][y].addGameObject(new IndestructibleWall());
                }
                else if (Math.random() < 0.1) {
                    cells[x][y].addGameObject(new DestructibleWall());
                }
                else if (Math.random() < 0.05) {
                    cells[x][y].addGameObject(new BonusWall(new Bonus(Bonus.Type.SPEED)));
                }
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


    public Player spawnPlayer(GameSoloConfig config)
    {
        Vector2 p = spawn_positions.get(0);
        Player player = spawnPlayer(config, config.player_skin, cells[(int) p.x][(int) p.y]);
        return player;
    }

    public ArrayList<Player> spawnPlayers(GameMultiConfig config)
    {
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < config.player_count; i++) {
            Vector2 p = spawn_positions.get(i);
            Player player = spawnPlayer(config, config.player_skins[i], cells[(int) p.x][(int) p.y]);
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
        ArrayList<Player> players = new ArrayList<Player>();
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
        try {
            Maze maze = gson.fromJson(new FileReader(new File(Constants.PATH_MAZE + name + ".json")), Maze.class);
            maze.initialize();
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
