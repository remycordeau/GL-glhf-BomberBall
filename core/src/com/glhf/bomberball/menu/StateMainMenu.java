package com.glhf.bomberball.menu;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.GUI.Button;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;


public class StateMainMenu extends State {
    //Attributes
    private Button solo;
    private Button multi;
    private Button parametres;
    boolean err = false;
    //Constructor
    public StateMainMenu(String name){
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
        solo.draw(batch);
        multi.draw(batch);
        parametres.draw(batch);
        if (err)
        {
            batch.begin();
            batch.draw(Graphics.GUI.get("erreur"), 160, 0);
            batch.end();
        }
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        y = Constants.APP_HEIGHT - y;
        if(solo.contains(x, y)) {
            //TODO: On lance le jeu solo
            err = true;
        }
        if(multi.contains(x, y))
        {
            //TODO: On lance le menu Multi
            State state = new StateMultiMenu("Menu multi");
            Game.setState(state);
        }
        if(parametres.contains(x, y)) {
            //TODO: On lance le menu parametres
            err=true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (err){err=false;}
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
