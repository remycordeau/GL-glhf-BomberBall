package com.glhf.bomberball.menu;

import com.glhf.bomberball.menu.listener.*;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

import javax.xml.soap.Text;

public class StateMultiMenu extends StateMenu {
    //Attributes
    private StateMainMenu mainMenu;
    private String previewFile="maze_0.json";
    protected HorizontalGroup previewButtons;
    protected TextButton nextMapButton;
    protected TextButton previousMapButton;
    protected TextButton playButton;
    protected TextButton cancelButton;

    public StateMultiMenu(StateMainMenu mainMenu) {
        super();
        this.mainMenu = mainMenu;
        initializeButtons();
    }

    public void initializeButtons(){
        playButton = new TextButton("Jouer", Graphics.GUI.getSkin());
        playButton.addListener(new SetStateListener(new StateGameMulti(previewFile)));
        centerButtons.addActor(playButton);
        cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new SetStateListener(mainMenu));
        centerButtons.addActor(cancelButton);

        // Display of the preview
            //TODO: arriver à afficher le labyrinthe ...
        Maze maze = Maze.fromJsonFile(previewFile);
        MazeDrawer maze_drawer = new MazeDrawer(maze, 0f,1f,0f,1f, MazeDrawer.Fit.BEST);
        maze_drawer.drawMaze();

        //Buttons to chose the maze you want to play in
            //TODO: Placer les boutons à droite et à gauche de l'écran (utiliser deux horizontal group différents ?)
        previewButtons=new HorizontalGroup();
        previewButtons.setFillParent(true);
        nextMapButton = new TextButton(">", Graphics.GUI.getSkin());
        previousMapButton = new TextButton("<", Graphics.GUI.getSkin());
        previewButtons.addActor(previousMapButton);
        previewButtons.addActor(nextMapButton);
        stage.addActor(previewButtons);
    }
}
