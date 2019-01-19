package com.glhf.bomberball.screens;

import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.gameobject.GameObject;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.ui.MapEditorUI;
import com.glhf.bomberball.utils.VectorInt2;

import java.io.File;

import static com.glhf.bomberball.utils.Constants.PATH_MAZE;

public class MapEditorScreen extends MenuScreen {

    private final Maze maze;
    private final MapEditorUI ui;
    private boolean symmetric;
    private GameObject objectSelected;

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
        symmetric = true;
        if(symmetric) {
            input_handler.registerActionHandler(Action.DROP_SELECTED_OBJECT, this::dropSelectedObjects);
            input_handler.registerActionHandler(Action.DELETE_OBJECT, this::deleteObjects);
        }else{
            input_handler.registerActionHandler(Action.DROP_SELECTED_OBJECT, (x,y)->dropSelectedObject(ui.screenPosToCell(x,y)));
            input_handler.registerActionHandler(Action.DELETE_OBJECT, (x,y)->deleteObject(ui.screenPosToCell(x,y)));
        }
    }

    private void deleteObject(VectorInt2 coords) {
        Cell cell = maze.getCellAt(coords.x, coords.y);
        if(cell!=null) cell.removeGameObjects();
    }

    private void deleteObjects(float x, float y) {
        VectorInt2 size = new VectorInt2(maze.getHeight(), maze.getWidth())
                .scl(1/2f); //scale half
        VectorInt2 coords = ui.screenPosToCell(x,y)
                .sub(size) //substract [0, height] -> [-height/2, height/2]
                .abs(); //absolute value [-height/2, height/2] -> [0, height/2]
        VectorInt2 min = new VectorInt2(size.x-coords.x,size.y-coords.y);
        VectorInt2 max = new VectorInt2(size.x+coords.x,size.y+coords.y);
        deleteObject(new VectorInt2(min.x,min.y));
        deleteObject(new VectorInt2(min.x,max.y));
        deleteObject(new VectorInt2(max.x,min.y));
        deleteObject(new VectorInt2(max.x,max.y));

    }

    private void dropSelectedObject(VectorInt2 coords) {
        Cell cell = maze.getCellAt(coords.x, coords.y);
        if(cell!=null && objectSelected!=null && cell.isEmpty()) cell.addGameObject(objectSelected.clone());
    }

    private void dropSelectedObjects(float x, float y) {
        VectorInt2 size = new VectorInt2(maze.getHeight(), maze.getWidth())
                .scl(1/2f); //scale half
        VectorInt2 coords = ui.screenPosToCell(x,y)
                .sub(size) //substract [0, height] -> [-height/2, height/2]
                .abs(); //absolute value [-height/2, height/2] -> [0, height/2]
        VectorInt2 min = new VectorInt2(size.x-coords.x,size.y-coords.y);
        VectorInt2 max = new VectorInt2(size.x+coords.x,size.y+coords.y);
        dropSelectedObject(new VectorInt2(min.x,min.y));
        dropSelectedObject(new VectorInt2(min.x,max.y));
        dropSelectedObject(new VectorInt2(max.x,min.y));
        dropSelectedObject(new VectorInt2(max.x,max.y));
    }

    public void select(GameObject object){
        objectSelected = object;
    }

    public void saveMaze(String maze_name){
        //remove Players from the maze, they doesn't need to
        for(int x=0; x<maze.getWidth(); x++){
            for(int y=0; y<maze.getHeight(); y++){
                Cell cell = maze.getCellAt(x, y);
                for(Player p : cell.getInstancesOf(Player.class)) {
                    cell.removeGameObject(p);
                    maze.addPlayerSpawn(new VectorInt2(cell.getX(),cell.getY()));
                }
            }
        }
        //export
        File dir = new File(PATH_MAZE);
        if(!dir.exists()) dir.mkdirs();
        maze.export(maze_name);
    }
}
