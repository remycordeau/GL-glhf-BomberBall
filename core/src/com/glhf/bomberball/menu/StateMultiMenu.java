package com.glhf.bomberball.menu;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.GUI.Button;
import com.glhf.bomberball.GUI.ButtonUndo;
import com.glhf.bomberball.GUI.SelectButton;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;

public class StateMultiMenu extends State {
    //Attributes
    private Button retrievePlayer;
    private Button addPlayer;
    private SelectButton numberPlayer;
    private Button begin;
    private Button begin_random;
    private ButtonUndo cancel;
    private boolean err =false;
    //Constructor
    public StateMultiMenu(String name){
        this.settings();
    }

    /*Loading textures*/
    public void settings(){
        //Button retrievePlayer
        retrievePlayer = new Button(100, 15, 20, 200, "Minus2");

        //Button addPlayer
        addPlayer = new Button(200, 15, 20, 20, "Plus2");

        //Button numberPlayer
        numberPlayer = new SelectButton(137, 2, 50, 50, Constants.config_file.getIntAttribute("nb_player_max")+"");

        //Button Cancel
        State s = new StateMainMenu("MainMenu");
        cancel = new ButtonUndo(400, 0, 100, 100, s);

        //Button Begin
        begin = new Button(160, 200, 400, 100, "BoutonMulti");

        //Button Beign Random
        begin_random = new Button(160, 100, 400, 100, "BoutonMulti");
    }
}
