package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.glhf.bomberball.Graphics;


public class StateMainMenu extends State {

    //Attributes
    private TextureAtlas.AtlasRegion solo;
    private TextureAtlas.AtlasRegion multi;
    private TextureAtlas.AtlasRegion parametre;
    private TextureAtlas.AtlasRegion erreur;
    private final int X_BUTTON =160;
    private final int Y_SOLO=300;
    private final int Y_MULTI=200;
    private final int Y_PARAM=100;
    private final int X_SIZE=376;  //Note : les boutons à la rache font 376x183
    private final int Y_SIZE=183;


    //Constructor
    public StateMainMenu(){
        super("MainMenu");
        this.settings();//TODO La texture boutonsolo n'a pas été push
    }
    /*Loading textures*/
    public void settings(){

        //Message d'erreur
        erreur = Graphics.GUI.get("erreur");
        //BoutonSolo
        solo = Graphics.GUI.get("BoutonSolo");
        //BoutonMulti
        multi = Graphics.GUI.get("BoutonMulti");
        //Bouton Paramètres
        parametre = Graphics.GUI.get("BoutonParametres");
    }

    public void draw(){
        batch.begin();
        batch.draw(solo, X_BUTTON, Y_SOLO);
        batch.draw(multi, X_BUTTON, Y_MULTI);
        batch.draw(parametre, X_BUTTON, Y_PARAM);
        batch.end();
    }
    @Override
    public boolean touchDown(int x, int y, int pointer, int bouton) {
        //Doit afficer une erreur quand on clique mais ne se lance même pas ...
        if(x> X_BUTTON && x < X_BUTTON+X_SIZE && y> Y_SOLO && y<Y_SOLO+Y_SIZE)
        {
            //TODO: On lance le jeu solo
            /*batch.begin();
            batch.draw(erreur, 0, 0);
            batch.end();*/
        }
        if(x> X_BUTTON && x < X_BUTTON+X_SIZE && y> Y_MULTI && y<Y_MULTI+Y_SIZE)
        {
            //TODO: On lance le jeu multi
        }
        if(x> X_BUTTON && x < X_BUTTON+X_SIZE && y> Y_PARAM && y<Y_PARAM+Y_SIZE)
        {
            //TODO: On lance les parametres
        }
        batch.begin();
        batch.draw(erreur, 0, 0);
        batch.end();
        return false;
    }
}
