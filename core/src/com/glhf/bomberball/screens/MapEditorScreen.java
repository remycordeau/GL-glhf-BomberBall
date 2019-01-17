package com.glhf.bomberball.screens;

import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.gameobject.GameObject;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.MapEditorUI;
import com.glhf.bomberball.utils.VectorInt2;

public class MapEditorScreen extends MenuScreen {

    private final Maze maze;
    private final MapEditorUI ui;
    private Class<? extends GameObject> classSelected;

    public MapEditorScreen()
    {
        super();
        this.maze = new Maze(13, 11);

        ui = new MapEditorUI(this, maze);
        addUI(ui);
    }

    @Override
    protected void registerActionsHandlers() {
        super.registerActionsHandlers();
        input_handler.registerActionHandler(Action.DROP_SELECTED_OBJECT, this::dropSelectedObject);
        input_handler.registerActionHandler(Action.DELETE_OBJECT, this::deleteObject);
    }

    private void deleteObject(float x, float y) {
        VectorInt2 coords = ui.screenPosToCell(x,y);
        Cell cell = maze.getCellAt(coords.x, coords.y);
        if(cell != null && classSelected != null) {
            cell.removeGameObjects();
        }
    }

    private void dropSelectedObject(float x, float y) {
        try {
            VectorInt2 coords = ui.screenPosToCell(x,y);
            Cell cell = maze.getCellAt(coords.x, coords.y);
            if(cell != null && classSelected != null) {
                cell.removeGameObjects();
                cell.addGameObject(classSelected.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) { e.printStackTrace(); }
    }

    public <T extends GameObject> void select(Class<T> clazz){
        classSelected = clazz;
    }
}
