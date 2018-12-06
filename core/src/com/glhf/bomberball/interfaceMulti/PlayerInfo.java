package com.glhf.bomberball.interfaceMulti;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
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

    private Image player_profil;

    /**
     * constructor
     */
    public PlayerInfo(Player player){
        this.player = player;
        info_player = new VerticalGroup();
        heart_group = new HorizontalGroup();
        bonus_group = new HorizontalGroup();
        player_profil = new Image(player.getSprite());
        this.addActor(player_profil);
        this.addActor(info_player);
        info_player.addActor(heart_group);
        this.addHeart();
        info_player.addActor(bonus_group);
    }

    private void addHeart(){
        for(int i=2; i<=player.getLife()+1; i++) {
            heart_group.addActor(new Image(Graphics.Sprites.get("ui_heart_full")));
        }
    }

    private void addBonus(){
        int number_bomb_remaining = player.getNumberBombRemaining();
        int number_speed_remaining = player.getNumberMoveRemaining();
        int number_range_boost = player.getNumberBonus("BombRangeBoost")+player.getInitialBombRange();

    }

}
