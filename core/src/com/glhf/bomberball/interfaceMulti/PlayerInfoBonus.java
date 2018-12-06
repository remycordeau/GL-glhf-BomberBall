package com.glhf.bomberball.interfaceMulti;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

public class PlayerInfoBonus extends Actor {
    //attributes
    private Player player;

    /**
     * constructor
     */
    public PlayerInfoBonus(Player p){
        this.player=p;
        TextureAtlas.AtlasRegion atlas_region = player.getSprite();
        this.setBounds(atlas_region.getRegionX(), atlas_region.getRegionY(), atlas_region.getRegionWidth(), atlas_region.getRotatedPackedHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Graphics.Sprites.get("bomb"),this.getX()+20f, this.getY());
        for(int i=2; i<=player.getLife()+1; i++) {
            batch.draw(Graphics.Sprites.get("ui_heart_full"), this.getX() + i*20f, this.getY());
        }
    }
}
