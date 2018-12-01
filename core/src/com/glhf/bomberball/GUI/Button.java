package com.glhf.bomberball.GUI;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.glhf.bomberball.Graphics;

import javax.xml.soap.Text;

public class Button extends Rectangle{
    //Attributes
    private TextureAtlas.AtlasRegion fixedSprite;
    private TextureAtlas.AtlasRegion sprite;
    private TextureAtlas.AtlasRegion shinySprite;


    public Button(int x, int y, int width, int height, String s, boolean isShiny){
        super(x, y, width, height);
        sprite = Graphics.GUI.get(s);
        if(isShiny) {
            fixedSprite = Graphics.GUI.get(s);
            shinySprite = Graphics.GUI.get(s+"Shiny");
        }
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(sprite, x, y);
        batch.end();
    }

    public void drawShiny(SpriteBatch batch){
        batch.begin();
        batch.draw(shinySprite, x, y);
        batch.end();
    }

    TextureAtlas.AtlasRegion getSprite(){
        return sprite;
    }

    public void setSprite(TextureAtlas.AtlasRegion sprite){
        this.sprite = sprite;
    }

    public void alternativeSprite(int x, int y){
        TextureAtlas.AtlasRegion tmp = sprite;
        if(this.contains(x, y)) {
            this.setSprite(Graphics.GUI.get(shinySprite+""));
        }
        else{
            this.setSprite(Graphics.GUI.get(fixedSprite+""));
        }
    }
}
