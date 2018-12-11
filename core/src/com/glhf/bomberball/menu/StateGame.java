package com.glhf.bomberball.menu;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

public abstract class StateGame extends State {
    protected Maze maze;
    protected MazeDrawer maze_drawer;

    public StateGame(String maze_filename) {
        super();
        loadMaze(maze_filename,1/3f,1f,2/10f,1f);
    }

    public void loadMaze(String filename, float w_minp, float w_maxp, float h_minp, float h_maxp) {
//        maze = new Maze(11, 13);
//        maze.export(filename);
        maze = Maze.importMaze(filename);
        maze_drawer = new MazeDrawer(maze, w_minp, w_maxp, h_minp, h_maxp, MazeDrawer.Fit.BEST);
    }

    @Override
    public void draw() {
        super.draw();
    }

    /*
    @Override
    public boolean keyDown(int keycode) {
        //HashMap<Integer, String> inputs = Config.getInputs();
        //System.out.println("keyDown"+keycode);
        switch (keycode){
            case Input.Keys.UP:
                moveCurrentPlayer(Directions.UP);
                break;
            case Input.Keys.RIGHT:
                moveCurrentPlayer(Directions.RIGHT);
                break;
            case Input.Keys.DOWN:
                moveCurrentPlayer(Directions.DOWN);
                break;
            case Input.Keys.LEFT:
                moveCurrentPlayer(Directions.LEFT);
                break;
            case Input.Keys.SPACE:
                endTurn();
                break;
        }
//        if(inputs.keySet().contains(keycode)) {
//            try {
//                Player.class.getMethod(inputs.get(keycode)).invoke(players[turn_number % Constants.NB_PLAYER_MAX]);
//            } catch (IllegalAccessException e) { e.printStackTrace(); }
//              catch (InvocationTargetException e) { e.printStackTrace(); }
//              catch (NoSuchMethodException e) { e.printStackTrace(); }
//        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 cell_pos = mazeDrawer.screenPosToCell(screenX, screenY);
        int cell_x = (int)cell_pos.x;
        int cell_y = (int)cell_pos.y;
        Directions dir = current_player.getCell().getCellDir(maze.getCellAt(cell_x, cell_y));
        if (dir != null) {
            current_player.dropBomb(dir);
        }
        return false;
    }*/
}
