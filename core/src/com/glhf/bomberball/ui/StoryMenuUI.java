package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.MainMenuScreen;
import com.glhf.bomberball.screens.ScreenChangeListener;

public class StoryMenuUI extends Table {

    private TextButton back_button, next_level_button, previous_level_button;
    private Label label;
    private Table level_selection;


    public StoryMenuUI(){

        super();

        this.setFillParent(true);
        //this.padLeft(Value.percentWidth(0.25f));
        //this.padRight(Value.percentWidth(0.25f));
//        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.5f));
        level_selection = new Table();


        addButtons();
    }

    private void addButtons() {

        label = new Label("Level Selection",Graphics.GUI.getSkin(),"Title");
        label.setAlignment(Align.center);
        this.add(label).growX().spaceBottom(Value.percentHeight(1)).row();


        previous_level_button = new TextButton("<",Graphics.GUI.getSkin());
        level_selection.add(previous_level_button);

        next_level_button = new TextButton(">",Graphics.GUI.getSkin());
        level_selection.add(next_level_button).spaceLeft(Value.percentHeight(8f));

        this.add(level_selection).row();

        back_button = new TextButton("Back to main menu",Graphics.GUI.getSkin());
        back_button.getLabel().setFontScale(0.8f,0.8f);
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        this.add(back_button).bottom();



        }
}


