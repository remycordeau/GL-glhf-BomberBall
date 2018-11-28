package com.glhf.bomberball.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StateMainMenu extends State {

    //Attributes
    private Texture test1;
    private TextureRegion region1;
    private Texture test2;
    private TextureRegion region2;
    private Texture test3;
    private TextureRegion region3;

    //Constructor
    public StateMainMenu(){
        super("MainMenu");
        //this.settings();//TODO La texture boutonsolo n'a pas été push
    }

    /*Loading textures*/
    public void settings(){
        //BoutonSolo
        //test1 = Textures.get("BoutonSolo");//TODO La texture boutonsolo n'a pas été push
        region1 = new TextureRegion(test1, 0, 0, 400, 100);

        //BoutonMulti
        //test2 = Textures.get("BoutonMulti");
        region2 = new TextureRegion(test2, 0, 0, 400, 100);

        //Bouton Paramètres
        //test3 = Textures.get("BoutonParametres");
        region3 = new TextureRegion(test3, 0, 0, 400, 100);
    }

    public void draw(){
        batch.draw(region1, 200, 400);
        batch.draw(region2, 200, 300);
        batch.draw(region3, 200, 200);
    }
}
