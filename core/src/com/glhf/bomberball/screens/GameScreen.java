package com.glhf.bomberball.screens;

import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

public abstract class GameScreen extends AbstractScreen {
    protected Maze maze;
    protected MazeDrawer maze_drawer;

    public GameScreen(Maze maze) {
        super();
//        maze = new Maze(11, 13);
//        maze.exportConfig(filename);
        this.maze = maze;
        this.maze_drawer = new MazeDrawer(maze,1/3f,1f,2/10f,1f, MazeDrawer.Fit.BEST);
    }

    @Override
    protected void registerActionsHandlers() {
        super.registerActionsHandlers();
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
