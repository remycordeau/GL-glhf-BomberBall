package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.GUI.Button;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;


public class StateMainMenu extends State {
    //Attributes
    private Button solo;
    private Button multi;
    private Button editor;
    private Button parametres;
    private Button quit;

    boolean solob = false;
    //Constructor
    public StateMainMenu(String name){
        this.settings();
    }

    /*Loading textures*/
    public void settings() {
        //BoutonSolo
        solo = new Button(160, 400, 400, 100, "BoutonSolo");
        //BoutonMulti
        multi = new Button(160, 300, 400, 100, "BoutonMulti");
        //BoutonEditeur
        editor = new Button(160, 200, 400, 100, "BoutonEditeur");
        //BoutonParametres
        parametres = new Button(160, 100, 400, 100, "BoutonParametres");
        //BoutonQuitter
        quit = new Button(160, 0, 400, 100, "BoutonQuitter");
    }
}
