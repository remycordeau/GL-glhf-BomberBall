package com.glhf.bomberball.menu;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.GUI.Button;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;

public class StateMultiMenu extends State {
    //Attributes
    private Button solo;
    private Button multi;
    private Button parametres;
    private boolean solob=false;
    //Constructor
    public StateMultiMenu(String name){
        super(name);
        this.settings();
    }

    /*Loading textures*/
    public void settings(){
        //BoutonSolo
        solo = new Button(160, 300, 400, 100, "BoutonSolo");
        //BoutonMulti
        multi = new Button(160, 200, 400, 100, "BoutonMulti");
        //BoutonParametres
        parametres = new Button(160, 100, 400, 100, "BoutonParametres");
    }

    public void draw(){
        System.out.println("in");
        solo.draw(batch);
        multi.draw(batch);
        parametres.draw(batch);
        if(solob)
        {
            batch.begin();
            batch.draw(Graphics.GUI.get("erreur"), 0, 0);
            batch.end();
        }
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if(solo.contains(x, y)) {

            solob=true;
            //TODO: On lance le jeu solo
        }
        if(multi.contains(x, y))
        {
            State state = new StateGameMulti("classic_maze_1.json");
            Game.setState(state);
        }
        if(solo.contains(x, y)) {
            //TODO: On lance les parametres
        }
        return false;
    }

}
