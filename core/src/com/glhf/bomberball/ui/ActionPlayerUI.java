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
        this.reachable_squares_move = new TextButton("Bombe [b]", Graphics.GUI.getSkin(), "small");
        this.reachable_squares_bomb = new TextButton("Déplacement [d]", Graphics.GUI.getSkin(),"small");
        this.endTurn= new TextButton("Fin de tour [f]", Graphics.GUI.getSkin(),"small");
        this.add(reachable_squares_bomb).growX();
        this.add(reachable_squares_move).growX();
        this.add(endTurn).growX();
        this.pad(5);
    }
}
