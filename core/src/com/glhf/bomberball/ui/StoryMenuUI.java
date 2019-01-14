/**
 * @author : Rémy
 * creates the interface of the story mode (level selection) */
package com.glhf.bomberball.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Bomberball;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.maze.MazeDrawer;
import com.glhf.bomberball.screens.*;
import com.glhf.bomberball.utils.Constants;
import com.glhf.bomberball.utils.ScreenChangeListener;

public class StoryMenuUI extends Table {

    private Table level_selection;
    private static TextButton[] levels;
    private static MazeDrawer level_preview;
    private static StoryMenuScreen screen;

    public StoryMenuUI(StoryMenuScreen screen) {

        super();
        StoryMenuUI.screen = screen;
        this.setFillParent(true);
        level_selection = new Table();
        addButtons();
        level_preview = new MazeDrawer(screen.maze, 0.25f, 0.75f, 0.5f, 0.88f, MazeDrawer.Fit.BEST);
        this.add(level_preview);
        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture(Constants.PATH_GRAPHICS+"/background/SoloMenu.png")));
        this.setBackground(texture);
    }

    /**
     * initializes and adds all the buttons to the table. Also adds listeners to buttons if necessary
     */
    private void addButtons() {

        // Title

        Label label = new Label(Translator.translate("Level Selection"), Graphics.GUI.getSkin(), "Title");
        label.setAlignment(Align.center);
        label.setFontScale(1.7f, 1.7f);
        this.add(label).row();

        // Buttons to select the level

        TextButton previous_level_button = new AudioButton("<", Graphics.GUI.getSkin());
        previous_level_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.previousMaze();
                level_preview.setMaze(screen.maze);
                highlightLevel(screen.getMazeId());
                previous_level_button.setChecked(false);
            }
        });
        level_selection.add(previous_level_button);

        TextButton next_level_button = new AudioButton(">", Graphics.GUI.getSkin());
        next_level_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.nextMaze();
                level_preview.setMaze(screen.maze);
                highlightLevel(screen.getMazeId());
                next_level_button.setChecked(false);
            }
        });
        level_selection.add(next_level_button).spaceLeft(Value.percentHeight(8f));

        this.add(level_selection).align(Align.center).grow().row();

        Table buttons = new Table();

        // Available levels

        int nb_levels = screen.getMazeCount();
        levels = new TextButton[nb_levels];
        HorizontalGroup horizontal = new HorizontalGroup();

        for (int i = 0; i < nb_levels; i++) {
            levels[i] = new AudioButton(Integer.toString(i + 1), Graphics.GUI.getSkin(), "checkable");
            int finalI = i;
            levels[i].setDisabled(!screen.isLevelUnlocked(i));
            levels[i].addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    screen.getMaze(finalI);
                    //highlightLevel(screen.getMazeId());
                    level_preview.setMaze(screen.maze);
                }
            });
            horizontal.addActor(levels[i]);
            horizontal.space(25);

        }
        buttons.add(horizontal).spaceBottom(Value.percentHeight(0.5f)).row();

        // Play and quit buttons

        TextButton play_button = new AudioButton(Translator.translate("Play Selected Level"), Graphics.GUI.getSkin());
        play_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play_button.setChecked(false);
                if (screen.isLevelUnlocked(screen.getMazeId())) { // allows to play the level only if it's unlocked
                    Bomberball.changeScreen(new GameStoryScreen(screen, screen.maze, screen.getMazeId()));
                }
            }
        });
        buttons.add(play_button).row();

        TextButton back_button = new AudioButton(Translator.translate("Back to main menu"), Graphics.GUI.getSkin());
        back_button.addListener(new ScreenChangeListener(MainMenuScreen.class));
        back_button.getLabel().setFontScale(0.8f, 0.8f);
        buttons.add(back_button).spaceTop(Value.percentHeight(0.9f));
        this.add(buttons);
    }

    /**
     * changes the texture and allows preview of the level when the button is clicked
     * @param i the id of the level that will be unlocked
     */
    public static void unlockLevel(int i) {
        screen.setLevelUnlocked(i);
        levels[i].setDisabled(false);
    }

    public void highlightLevel(int j){
        if(screen.isLevelUnlocked(j)){
            for(int i = 0;i<screen.getMazeCount();i++){
                if(i!=j && !levels[i].isDisabled() == true){
                    levels[i].setChecked(false);
                }
            }
        }
        levels[j].setChecked(true);
    }
}


