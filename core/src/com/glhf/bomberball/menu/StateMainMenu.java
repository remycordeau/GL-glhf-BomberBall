package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glhf.bomberball.Graphics;

import static com.glhf.bomberball.Graphics.GUI.get;

public class StateMainMenu extends State {

    //Attributes
    private TextureAtlas.AtlasRegion solo;
    private TextureRegion region1;
    private TextureAtlas.AtlasRegion multi;
    private TextureRegion region2;
    private TextureAtlas.AtlasRegion parametre;
    private TextureRegion region3;

    //Constructor
    public StateMainMenu(){
        super("MainMenu");
        this.settings();//TODO La texture boutonsolo n'a pas été push
    }
    /*Loading textures*/
    public void settings(){
        //BoutonSolo
        //get("BoutonSolo");
        solo = Graphics.GUI.get("crate");

        //BoutonMulti
        multi = Graphics.GUI.get("crate");

        //Bouton Paramètres
        parametre = Graphics.GUI.get("crate");
    }

    public void draw(){
        batch.begin();
        batch.draw(solo, 200, 400);
        batch.draw(multi, 200, 300);
        batch.draw(parametre, 200, 200);
        batch.end();
    }
}
