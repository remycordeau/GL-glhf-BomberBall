package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeDrawer;

import javax.xml.soap.Text;

public class StateMultiMenu extends StateMenu {
    //Attributes
    private StateMainMenu mainMenu;
    private String previewFile="maze_0.json";
    protected VerticalGroup previewButtons;
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
    }
}
