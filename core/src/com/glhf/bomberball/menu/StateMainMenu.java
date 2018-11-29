package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glhf.bomberball.Graphics;

import static com.glhf.bomberball.Graphics.GUI.get;

public class StateMainMenu extends State {

    //Attributes
    private TextureAtlas.AtlasRegion test1;
    private TextureRegion region1;
    private TextureAtlas.AtlasRegion test2;
    private TextureRegion region2;
    private TextureAtlas.AtlasRegion test3;
    private TextureRegion region3;

    //Constructor
    public StateMainMenu(){
        super("MainMenu");
        this.settings();//TODO La texture boutonsolo n'a pas été push
    }
    /*Loading textures*/
    public void settings(){
        //BoutonSolo
        test1 = Graphics.GUI.get("BoutonSolo");

        //BoutonMulti
        test2 = Graphics.GUI.get("BoutonMulti");

        //Bouton Paramètres
        test3 = Graphics.GUI.get("BoutonParametres");
    }

    public void draw(){
        batch.begin();
        batch.draw(test1, 200, 400);
        batch.draw(test2, 200, 300);
        batch.draw(test3, 200, 200);
        batch.end();
    }
}
