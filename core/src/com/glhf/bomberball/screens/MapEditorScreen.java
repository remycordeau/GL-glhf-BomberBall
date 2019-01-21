package com.glhf.bomberball.screens;

import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.gameobject.GameObject;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.MapEditorUI;
import com.glhf.bomberball.utils.VectorInt2;

import java.io.File;
import java.util.ArrayList;

import static com.glhf.bomberball.utils.Constants.PATH_MAZE;

public class MapEditorScreen extends MenuScreen {

    private final Maze maze;
    private final MapEditorUI ui;
    private boolean symmetric = true;
    private GameObject objectSelected;
    private VectorInt2 player_pos = null;

    public MapEditorScreen()
    {
        super();
        this.maze = new Maze(13, 13);

        ui = new MapEditorUI(this, maze);
        addUI(ui);
    }

    @Override
    protected void registerActionsHandlers() {
        super.registerActionsHandlers();
        input_handler.registerActionHandler(Action.DROP_SELECTED_OBJECT, (x,y)->dropSelectedObject(ui.screenPosToCell(x,y)));
        input_handler.registerActionHandler(Action.DELETE_OBJECT, (x,y)->deleteObject(ui.screenPosToCell(x,y)));
    }

    private ArrayList<VectorInt2> getPositions(VectorInt2 p) {
        ArrayList<VectorInt2> positions = new ArrayList<VectorInt2>();
        if (!maze.isCellInBounds(p.x, p.y))
            return positions;

        if (!symmetric) {
            positions.add(p);
        }
        else {
            int half_w = maze.getWidth() / 2;
            int half_h = maze.getHeight() / 2;
            int x = p.x - half_w;
            int y = p.y - half_h;
            positions.add(new VectorInt2(x + half_w, y + half_h));
            positions.add(new VectorInt2(-x + half_w, y + half_h));
            positions.add(new VectorInt2(x + half_w, -y + half_h));
            positions.add(new VectorInt2(-x + half_w, -y + half_h));
        }
        return positions;
    }

    private void dropSelectedObject(VectorInt2 coords) {
        boolean placed = false;
        for (VectorInt2 p : getPositions(coords)) {
            Cell cell = maze.getCellAt(p.x, p.y);
            if(cell != null && objectSelected != null && cell.isEmpty()) {
                cell.addGameObject(objectSelected.clone());
                placed = true;
            }
        }

        // Checks if already placed players spawns
        if (placed && objectSelected instanceof Player) {
            if (player_pos != null) {
                for (VectorInt2 p : getPositions(player_pos)) {
                    maze.getCellAt(p.x, p.y).removeGameObjects();
                }
            }
            player_pos = coords;
        }
    }

    private void deleteObject(VectorInt2 coords) {
        for (VectorInt2 p : getPositions(coords)) {
            Cell cell = maze.getCellAt(p.x, p.y);
            if(cell != null) {
                cell.removeGameObjects();
            }
        }
    }

    public void select(GameObject object){
        objectSelected = object;
    }

    public void saveMaze() {
        if (player_pos == null) {
            return;
        }

        // Removing Players and adding spawn positions
        for (VectorInt2 p : getPositions(player_pos)) {
            maze.getCellAt(p.x, p.y).removeGameObjects();
            maze.addPlayerSpawn(p);
        }

        File dir = new File(PATH_MAZE+"/multi/");
        int maze_id = dir.listFiles().length;
        maze.export("multi/maze_" + maze_id);
        Bomberball.changeScreen(new MainMenuScreen());
    }
}
