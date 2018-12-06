package com.glhf.bomberball.interfaceMulti;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

public class PlayerInfo extends Group {

    //attributes
    private Player player;

    //private VerticalGroup info_player;
    private Group info_player;
    private HorizontalGroup heart_group;
    private HorizontalGroup bonus_group;

    private TextureRegion player_profil;

    /**
     * constructor
     */
    public PlayerInfo(Player player){
        this.player = player;
        //info_player = new VerticalGroup();
        info_player = new Group();
        info_player.setDebug(true);

        /*heart_group = new HorizontalGroup();
        heart_group = new HorizontalGroup();
        bonus_group = new HorizontalGroup().space(10f);*/

        player_profil = player.getSprite();
        //this.addActor(player_profil);
        /*this.addActor(info_player);
        info_player.addActor(heart_group);
        heart_group.setDebug(true);
        this.addHeart();
        info_player.addActor(bonus_group);
        bonus_group.setDebug(true);
        this.addBonus();*/
    }

    private void addHeart(){
        heart_group.setSize(heart_group.getParent().getWidth(), heart_group.getParent().getHeight());
        for(int i=2; i<=player.getLife()+1; i++) {
            Image ui_heart_full = new Image(Graphics.Sprites.get("ui_heart_full"));
            ui_heart_full.setScaling(Scaling.fill);
            heart_group.addActor(ui_heart_full);
        }
    }

    private void addBonus(){
        int number_bomb_remaining = player.getNumberBombRemaining();
        int number_speed_remaining = player.getNumberMoveRemaining();
        int number_range_boost = player.getBombRange();
        bonus_group.addActor(new Image(Graphics.Sprites.get("gift_01a")));
        bonus_group.addActor(new Image(Graphics.Sprites.get("bomb")));
        bonus_group.addActor(new Image(Graphics.Sprites.get("bow_02a")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float ratio = this.getHeight()/player_profil.getRegionHeight();
        batch.draw(player_profil, this.getX()+10f, this.getY()+10f, player_profil.getRegionWidth()*ratio, this.getHeight());
    }
}
