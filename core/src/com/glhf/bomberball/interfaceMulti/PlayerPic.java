package com.glhf.bomberball.interfaceMulti;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

public class PlayerPic extends Actor {
    //attributes
    private Player player;

    /**
     * constructor
     */
    public PlayerPic(Player p){
        this.player=p;
        TextureAtlas.AtlasRegion atlas_region = player.getSprite();
        this.setBounds(atlas_region.getRegionX(), atlas_region.getRegionY(), atlas_region.getRegionWidth(), atlas_region.getRotatedPackedHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(player.getSprite(), this.getX(), this.getY());
    }
}
