package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;

public class MapEditorMenuScreen extends MenuScreen {
    //Attributes

    public MapEditorMenuScreen(){
        super();
        initializeButtons();
    }

    public void initializeButtons() {
        TextButton textButton = new TextButton("Retour", Graphics.GUI.getSkin());
        textButton.addListener(new ScreenChangeListener(MainMenuScreen.class));
        centerButtons.addActor(textButton);
    }
}
