package com.glhf.bomberball.menu;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.menu.listener.SetStateListener;

public class StateSoloMenu extends StateMenu {
    //Attributes
    StateMainMenu mainMenu;

    //Constructor
    public StateSoloMenu(StateMainMenu mainMenu) {
        super();
        this.mainMenu = mainMenu;
        initializeButtons();
    }

    public void initializeButtons() {
        TextButton textButton = new TextButton("Retour", Graphics.GUI.getSkin());
        textButton.addListener(new SetStateListener(mainMenu));
        centerButtons.addActor(textButton);
    }

}