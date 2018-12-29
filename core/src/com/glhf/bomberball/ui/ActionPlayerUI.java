package com.glhf.bomberball.ui;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.screens.GameMultiScreen;
import com.glhf.bomberball.screens.GameStoryScreen;

public class ActionPlayerUI extends Table {
    //attributes
    TextButton reachable_squares_move;
    TextButton reachable_squares_bomb;
    TextButton endTurn;
    GameMultiScreen screen;
    GameStoryScreen story_screen;
    /**
     * constructor
     */
    public ActionPlayerUI(GameMultiScreen screen){
        this.screen=screen;
        this.reachable_squares_move = new TextButton("Déplacement [d]", Graphics.GUI.getSkin(), "small");
        this.reachable_squares_bomb = new TextButton("Bombe [b]", Graphics.GUI.getSkin(),"small");
        this.endTurn= new TextButton("Fin de tour [f]", Graphics.GUI.getSkin(),"small");
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
        this.pad(5);
    }

    public ActionPlayerUI(GameStoryScreen screen) {

        this.story_screen=screen;
        this.reachable_squares_move = new TextButton("Déplacement [d]", Graphics.GUI.getSkin(), "small");
        this.reachable_squares_bomb = new TextButton("Bombe [b]", Graphics.GUI.getSkin(),"small");
        this.endTurn= new TextButton("Fin de tour [f]", Graphics.GUI.getSkin(),"small");
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
        this.pad(5);
    }
}
