package com.glhf.bomberball.interfaceMulti;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

public class PlayerInfo extends HorizontalGroup {

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
        info_player.left(); // PQ CA MARCHE PAS
        info_player.setDebug(true);
        heart_group = new HorizontalGroup();
        heart_group.left();
        bonus_group = new HorizontalGroup().space(10f);
        player_profil = new Image(player.getSprite());
        this.addActor(player_profil);
        this.addActor(info_player);
        info_player.addActor(heart_group);
        heart_group.setDebug(true);
        this.addHeart();
        info_player.addActor(bonus_group);
        bonus_group.setDebug(true);
        this.addBonus();
    }

    private void addHeart(){
        for(int i=2; i<=player.getLife()+1; i++) {
            Image ui_heart_full = new Image(Graphics.Sprites.get("ui_heart_full"));

            heart_group.addActor(ui_heart_full);
        }
    }

    private void addBonus(){
        int number_bomb_remaining = player.getNumberBombRemaining();
        int number_speed_remaining = player.getNumberMoveRemaining();
        int number_range_boost = player.getNumberBonus("BombRangeBoost")+player.getInitialBombRange();
        bonus_group.addActor(new Image(Graphics.Sprites.get("gift_01a")));
        bonus_group.addActor(new Image(Graphics.Sprites.get("bomb")));
        bonus_group.addActor(new Image(Graphics.Sprites.get("bow_02a")));
    }

}
