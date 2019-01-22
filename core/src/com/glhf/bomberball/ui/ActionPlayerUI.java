package com.glhf.bomberball.ui;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.InputHandler.Action;
import com.glhf.bomberball.Translator;
import com.glhf.bomberball.audio.AudioButton;
import com.glhf.bomberball.config.InputsConfig;
import com.glhf.bomberball.screens.GameScreen;

import static com.glhf.bomberball.utils.Constants.PATH_GRAPHICS;

public class ActionPlayerUI extends Table {
    //attributes
    TextButton reachable_squares_move;
    TextButton reachable_squares_bomb;
    TextButton endTurn;
    GameScreen screen;
    HorizontalGroup actions;
    /**
     * constructor
     */
    public ActionPlayerUI(GameScreen screen){
        this.screen=screen;
        InputsConfig config = InputsConfig.get();
        /*this.reachable_squares_move = new AudioButton(Translator.translate("mode movement button", config.getInputName(Action.MODE_MOVE)), Graphics.GUI.getSkin(), "small");
        this.reachable_squares_bomb = new AudioButton(Translator.translate("mode bombe button", config.getInputName(Action.MODE_BOMB)), Graphics.GUI.getSkin(),"small");
        this.endTurn= new AudioButton(Translator.translate("endTurn button", config.getInputName(Action.ENDTURN)), Graphics.GUI.getSkin(),"small");
        // TODO : ajout des listeners sur les différents boutons
        reachable_squares_move.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setMoveMode();
            }
        });
        reachable_squares_bomb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                screen.setBombMode();
            }
        });
        endTurn.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event, float x, float y){
               screen.endTurn();
           }
        });
        this.add(reachable_squares_bomb).growX();
        this.add(reachable_squares_move).growX();
        this.add(endTurn).growX();
        this.pad(5);*/
        actions = new HorizontalGroup();
        actions.space(15);

        Table table_end_turn= new Table();
        Label end_turn = new Label("End Turn",Graphics.GUI.getSkin());
        TextButton end_turn_button = new TextButton("F",Graphics.GUI.getSkin());
        table_end_turn.add(end_turn_button).row();
        table_end_turn.add(end_turn);
        actions.addActor(table_end_turn);

        Table table_move= new Table();
        Label move = new Label(" se déplacer/poser bombe",Graphics.GUI.getSkin());
        TextButton move_button = new TextButton("flèches",Graphics.GUI.getSkin());
        table_move.add(move_button).row();
        table_move.add(move);
        actions.addActorAfter(table_end_turn,table_move);

        Table table_bomb = new Table();
        Label bomb = new Label("poser bombe",Graphics.GUI.getSkin());
        TextButton bomb_button = new TextButton("B",Graphics.GUI.getSkin());
        table_bomb.add(bomb_button).row();
        table_bomb.add(bomb);
        actions.addActorAfter(table_move,table_bomb);

        Table table_move_mode = new Table();
        Label move_mode = new Label("Mode déplacement",Graphics.GUI.getSkin());
        TextButton move_mode_button = new TextButton("D",Graphics.GUI.getSkin());
        table_move_mode.add(move_mode_button).row();
        table_move_mode.add(move_mode);
        actions.addActorAfter(table_bomb,table_move_mode);

        this.add(actions);
    }
}
