package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;

public class ActionPlayerUI extends Table {
    //attributes
    TextButton reachable_squares_move;
    TextButton reachable_squares_bomb;
    TextButton endTurn;

    /**
     * constructor
     */
    public ActionPlayerUI(){
        this.reachable_squares_move = new TextButton("a", Graphics.GUI.getSkin());
        this.reachable_squares_bomb = new TextButton("b", Graphics.GUI.getSkin());
        this.endTurn= new TextButton("c", Graphics.GUI.getSkin());
        this.addActor(reachable_squares_bomb);
        this.addActor(reachable_squares_move);
        this.addActor(endTurn);
    }
}
