package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.menu.listener.SetStateListener;
import sun.font.GraphicComponent;

import static javax.swing.text.StyleConstants.Background;

public class StateStoryMenu extends StateMenu {


    private TextButton back_button;
    private StateMainMenu mainMenu;

    public StateStoryMenu(StateMainMenu mainMenu){

        super();
        this.mainMenu = mainMenu;
        initializeButtons();

    }

    private void initializeButtons() {

        back_button = new TextButton("Back to main menu", Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new SetStateListener(mainMenu));
        centerButtons.addActor(back_button);
    }

}


