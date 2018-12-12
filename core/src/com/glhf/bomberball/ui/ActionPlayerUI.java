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
        this.reachable_squares_move = new TextButton("Ou se déplacer ?", Graphics.GUI.getSkin());
        this.reachable_squares_bomb = new TextButton("Ou poser la bombe ?", Graphics.GUI.getSkin());
        this.endTurn= new TextButton("Fin de tour", Graphics.GUI.getSkin());
    }
}
