package com.glhf.bomberball.menu;

import com.glhf.bomberball.menu.listener.*;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;


public class StateMultiMenu extends StateMenu {
    //Attributes
    private StateMainMenu mainMenu;
    private int previewMapNumber=0;

    private MazeDrawer maze_drawer;

    public static int maxMaze=0;   //TODO: Trouver une façon plus élégante de connaître le nombre max de labyrinthe proposé pour jouer. Il faut augmenter ce nombre à chaque fois qu'on veut rajouter un labyritnhe
    private String previewFile="maze_"+previewMapNumber+".json";
    protected HorizontalGroup previewButtons;
    protected TextButton nextMapButton;
    protected TextButton previousMapButton;
    protected TextButton playButton;
    protected TextButton cancelButton;

    public StateMultiMenu(StateMainMenu mainMenu) {
        super();
        this.mainMenu = mainMenu;
        this.showPreview();
        initializeButtons();
    }

    public void initializeButtons(){
        playButton = new TextButton("Jouer", Graphics.GUI.getSkin());
        playButton.addListener(new SetStateListener(new StateGameMulti(previewFile)));
        centerButtons.addActor(playButton);
        cancelButton = new TextButton("Retour", Graphics.GUI.getSkin());
        cancelButton.addListener(new SetStateListener(mainMenu));
        centerButtons.addActor(cancelButton);


        //Buttons to chose the maze you want to play in
            //TODO: Placer les boutons à droite et à gauche de l'écran (utiliser deux horizontal group différents ?)
        previewButtons=new HorizontalGroup();
        previewButtons.setFillParent(true);
        nextMapButton = new TextButton(">", Graphics.GUI.getSkin());
        nextMapButton.addListener(new ChangePreviewListener(1,this));
        previousMapButton = new TextButton("<", Graphics.GUI.getSkin());
        previousMapButton.addListener(new ChangePreviewListener(-1,this));
        previewButtons.addActor(previousMapButton);
        previewButtons.addActor(nextMapButton);
        stage.addActor(previewButtons);
    }

    @Override
    public void draw() {
        super.draw();
        maze_drawer.drawMaze();
    }

    public void showPreview()
    {
        Maze maze = Maze.fromJsonFile(previewFile);
        maze_drawer = new MazeDrawer(maze, 0.75f,1f,0.75f,1f, MazeDrawer.Fit.BEST);
    }
    // Getter
    public int getPreviewMapNumber() {
        return previewMapNumber;
    }
    public static int getMaxMaze() {
        return maxMaze;
    }

    // Setter
    public void setPreviewMapNumber(int previewMapNumber) {
        this.previewMapNumber = previewMapNumber;
    }

    public void setPreviewFile(String previewFile) {
        this.previewFile = previewFile;
    }


}
