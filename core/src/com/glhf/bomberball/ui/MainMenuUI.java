/**
 * @author : Rémy
 * creates the user interface for the title menu. Displays all the features available in the game.
 */
package com.glhf.bomberball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.Audio;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.cell.Cell;
import com.glhf.bomberball.screens.*;

public class MainMenuUI extends Table {

    public MainMenuUI() {
        Audio.MAIN_MENU.playMusique();
        this.setFillParent(true);
        this.padLeft(Value.percentWidth(0.25f));
        this.padRight(Value.percentWidth(0.25f));
        this.padTop(Value.percentHeight(0.1f));
        this.padBottom(Value.percentHeight(0.1f));
        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture("core/assets/graphics/background/MainMenu.png")));
        this.setBackground(texture);
        addButtons();
    }

    /**
     * creates and adds buttons to the interface. Also adds listeners to these buttons if necessary.
     */
    private void addButtons()
    {
        Maze mazex = Maze.importMaze("maze_0");
        Cell origin = new Cell(0, 0);
        Cell test = new Cell(0, 1);
        System.out.println("Le maze de test est finissable : " + mazex.isReachableCell(origin, test));
        TextButton b;
        Skin skin = Graphics.GUI.getSkin();

        Value spacing = Value.percentHeight(0.15f);

        Table Buttons = new Table();

        b = new TextButton(Translator.translate("Solo"), Graphics.GUI.getSkin());
        b.addListener(new ScreenChangeListener(SoloMenuScreen.class));
        Buttons.add(b).growX().space(spacing).row();

        b = new TextButton(Translator.translate("Multiplayer"), skin);
        b.addListener(new ScreenChangeListener(MultiMenuScreen.class));
        Buttons.add(b).growX().space(spacing).row();

        b = new TextButton(Translator.translate("Map Editor"), Graphics.GUI.getSkin());
        b.addListener(new ScreenChangeListener(MapEditorScreen.class));
        Buttons.add(b).growX().space(spacing).row();

        b = new TextButton(Translator.translate("Settings"), Graphics.GUI.getSkin());
        b.addListener(new ScreenChangeListener(SettingsMenuScreen.class));
        Buttons.add(b).growX().space(spacing).row();

        b = new TextButton(Translator.translate("Quit"), Graphics.GUI.getSkin());
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        Buttons.add(b).growX();

        this.add(Buttons).padTop(10f);
    }
}
