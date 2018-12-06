package com.glhf.bomberball.interfaces;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

import java.util.ArrayList;

public class InterfaceMulti extends Table {

    private ArrayList<Player> players;

    public InterfaceMulti(ArrayList<Player> players) {
        this.players = players;

        build();
    }

    private void build() {
        this.setSize(Constants.APP_WIDTH/3, Constants.APP_HEIGHT);

        for (Player player : players) {
            this.add(new PlayerWidget(player)).grow();
            this.row();
        }
        this.setDebug(true, true);
    }

    class PlayerWidget extends Table {

        public PlayerWidget(Player player) {
            this.pad(20);
            Image player_skin = new Image(player.getSprite());
            player_skin.setScaling(Scaling.fit);
            this.add(player_skin).grow();
            this.add(new PlayerInfoWidget(player)).grow();
        }
    }

    class PlayerInfoWidget extends Table {
        public PlayerInfoWidget(Player player) {
            this.add(new HeartsWidget(player)).grow();
            this.row();
            this.add(new ImageBonusWidget()).grow();
            this.row();
            this.add(new NumberBonusWidget(player)).grow();
        }
    }

    class HeartsWidget extends Table {

        public HeartsWidget(Player player) {
            this.pad(5);
            for (int i=0; i<player.getLife(); i++) {
                Image heart = new Image(Graphics.Sprites.get("ui_heart_full"));
                heart.setScaling(Scaling.fit);
                heart.setAlign(Align.left);
                this.add(heart).grow().space(5);
            }
        }
    }

    class ImageBonusWidget extends Table {
        public ImageBonusWidget() {
            this.pad(5);
            Image number_moves = new Image(Graphics.Sprites.get("gift_01a"));
            Image number_bombs = new Image(Graphics.Sprites.get("bomb"));
            Image bomb_range = new Image(Graphics.Sprites.get("bow_02a"));
            number_moves.setScaling(Scaling.fit);
            number_bombs.setScaling(Scaling.fit);
            bomb_range.setScaling(Scaling.fit);
            this.add(number_moves).grow().space(10);
            this.add(number_bombs).grow().space(10);
            this.add(bomb_range).grow().space(10);
        }
    }

    class NumberBonusWidget extends Table {
        public NumberBonusWidget(Player player) {
            this.pad(5);
            Label number_moves = new Label("x"+player.getNumberMoveRemaining(), Graphics.LabelStyleMulti.getStyle());
            Label number_bombs = new Label("x"+player.getNumberBombRemaining(), Graphics.LabelStyleMulti.getStyle());
            Label bomb_range = new Label("x"+player.getBombRange(), Graphics.LabelStyleMulti.getStyle());
            this.add(number_moves).grow().space(10);
            this.add(number_bombs).grow().space(10);
            this.add(bomb_range).grow().space(10);
        }
    }
}
