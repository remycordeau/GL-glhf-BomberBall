package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.menu.listener.SetStateListener;

public class StateSoloMenu extends StateMenu {

    private TextButton infinite_button, story_button, back_button;
    private StateMainMenu mainMenu;

    public StateSoloMenu(StateMainMenu mainMenu)
    {
        super();
        this.mainMenu = mainMenu;
        initializeButtons();
    }

    private void initializeButtons() {

        story_button = new TextButton("Story Mode", Graphics.GUI.getSkin());
        story_button.addListener(new SetStateListener(new StateStoryMenu(mainMenu)));
        centerButtons.addActor(story_button);

        infinite_button = new TextButton("Infinite Mode", Graphics.GUI.getSkin());
        infinite_button.addListener(new SetStateListener(new StateInfiniteMenu(mainMenu)));
        centerButtons.addActor(infinite_button);

        centerButtons.space(20);
        back_button = new TextButton("Back to main menu",Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new SetStateListener(mainMenu));
        centerButtons.addActor(back_button);
    }

}
