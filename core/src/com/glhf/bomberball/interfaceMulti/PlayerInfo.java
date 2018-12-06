package com.glhf.bomberball.interfaceMulti;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

public class PlayerInfo extends HorizontalGroup {
    /*
    //attributes
    private Player player;

    //constructor
    public PlayerInfo(Player player) {
        this.player = player;
        TextureAtlas.AtlasRegion atlas_region = player.getSprite();
        this.setBounds(atlas_region.getRegionX(), atlas_region.getRegionY(), atlas_region.getRegionWidth(), atlas_region.getRotatedPackedHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(player.getSprite(), this.getX(), this.getY());
        batch.draw(Graphics.Sprites.get("bomb"),this.getX()+20f, this.getY());
        for(int i=2; i<=player.getLife()+1; i++) {
            batch.draw(Graphics.Sprites.get("ui_heart_full"), this.getX() + i*20f, this.getY());
        }
    }*/

    //attributes
    private Player player;

    private VerticalGroup info_player;
    private HorizontalGroup heart_group;
    private HorizontalGroup bonus_group;


    /**
     * constructor
     */
    public PlayerInfo(Player player){
        this.player=player;
        PlayerPic player_profil = new PlayerPic(player);
        PlayerInfoBonus player_info_bonus = new PlayerInfoBonus(player);
        this.addActor(player_profil);
        info_player.addActor(heart_group);
        info_player.addActor(bonus_group);
    }
}
