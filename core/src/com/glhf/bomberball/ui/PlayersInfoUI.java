package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

import java.util.ArrayList;

public class PlayersInfoUI extends PlayerObserver {

    private ArrayList<PlayerWidget> wplayers;

    public PlayersInfoUI(ArrayList<Player> players) {
        super(players);
        for (Player player : players) {
            PlayerWidget pw = new PlayerWidget(player);
            this.wplayers.add(pw);
            this.add(pw).grow();
            this.row();
        }
    }

    @Override
    public void update() {
        for (PlayerWidget pw : wplayers) {
            pw.update();
        }
    }

    class PlayerWidget extends Table {
        private Image player_skin;
        private PlayerInfoWidget player_info;

        public PlayerWidget(Player player) {
            this.pad(20);
            player_skin = new Image(player.getSprite());
            player_skin.setScaling(Scaling.fit);
            this.add(player_skin).grow();
            player_info = new PlayerInfoWidget(player);
            this.add(player_info).grow();
        }

        public void update() {
            //change image with animation
            player_info.update();
        }
    }

    class PlayerInfoWidget extends Table {
        private HeartsWidget player_hearts;
        private NumberBonusWidget player_bonus;

        public PlayerInfoWidget(Player player) {
            player_hearts = new HeartsWidget(player);
            this.add(player_hearts).grow();
            this.row();
            this.add(new ImageBonusWidget()).grow();
            this.row();
            player_bonus = new NumberBonusWidget(player);
            this.add(player_bonus).grow();
        }

        public void update() {
            player_hearts.update();
            player_bonus.update();
        }
    }

    class HeartsWidget extends Table {
        private Player player;
        private ArrayList<Image> hearts;
        public HeartsWidget(Player player) {
            this.pad(5);
            this.player = player;
            for (int i=0; i<player.getLife(); i++) {
                Image heart = new Image(Graphics.Sprites.get("ui_heart_full"));
                heart.setScaling(Scaling.fit);
                heart.setAlign(Align.left);
                hearts.add(heart);
                this.add(heart).grow().space(5);
            }
        }

        public void update() {
            while (player.getLife() < hearts.size()) {
                hearts.get(hearts.size()-1).remove();
                hearts.remove(hearts.size()-1);
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
        private Player player;
        private Label number_moves;
        private Label number_bombs;
        private Label bomb_range;
        public NumberBonusWidget(Player player) {
            this.pad(5);
            this.player = player;
            number_moves = new Label("x"+player.getNumberMoveRemaining(), Graphics.GUI.getSkin(), "small");
            number_bombs = new Label("x"+player.getNumberBombRemaining(), Graphics.GUI.getSkin(), "small");
            bomb_range = new Label("x"+player.getBombRange(), Graphics.GUI.getSkin(), "small");
            number_moves.setAlignment(Align.center);
            number_bombs.setAlignment(Align.center);
            bomb_range.setAlignment(Align.center);
            this.add(number_moves).grow().space(10);
            this.add(number_bombs).grow().space(10);
            this.add(bomb_range).grow().space(10);
        }

        public void update() {
            number_moves.setText("x"+player.getNumberMoveRemaining());
            number_bombs.setText("x"+player.getNumberBombRemaining());
            bomb_range.setText("x"+player.getBombRange());
        }
    }
}
