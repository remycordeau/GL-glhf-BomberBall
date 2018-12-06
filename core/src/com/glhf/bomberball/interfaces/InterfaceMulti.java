package com.glhf.bomberball.interfaces;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

import java.util.ArrayList;

public class InterfaceMulti extends Table {

    private Stage stage;
    private ArrayList<Player> players;

    public InterfaceMulti(Stage stage, ArrayList<Player> players) {
        this.stage = stage;
        this.players = players;

        build();
    }

    private void build() {
        this.pad(10f);
        this.setSize(Constants.APP_WIDTH/3, Constants.APP_HEIGHT);

        for (Player player : players) {
            this.add(new PlayerWidget(player)).grow();
            this.row();
        }
        this.setDebug(true, true);
    }

    class PlayerWidget extends Table {

        public PlayerWidget(Player player) {
            this.pad(10);
            Image skin = new Image(player.getSprite());
            skin.setScaling(Scaling.fit);
            this.add(skin).expand().fill();
            this.add(new PlayerInfoWidget(player)).grow();
        }
    }

    class PlayerInfoWidget extends Table {
        public PlayerInfoWidget(Player player) {
            this.add(new HeartsWidget(player)).grow();
            this.row();
            this.add(new BonusWidget(player)).grow();
        }
    }

    class HeartsWidget extends Table {

        public HeartsWidget(Player player) {
            Image heart_0 = new Image(Graphics.Sprites.get("ui_heart_full"));
            Image heart_1 = new Image(Graphics.Sprites.get("ui_heart_full"));
            Image heart_2 = new Image(Graphics.Sprites.get("ui_heart_full"));
            Image heart_3 = new Image(Graphics.Sprites.get("ui_heart_full"));
            heart_0.setScaling(Scaling.fit);
            heart_1.setScaling(Scaling.fit);
            heart_2.setScaling(Scaling.fit);
            heart_3.setScaling(Scaling.fit);
            this.add(heart_0).grow().space(10);
            this.add(heart_1).grow().space(10);
            this.add(heart_2).grow().space(10);
            this.add(heart_3).grow().space(10);
        }
    }

    class BonusWidget extends Table {
        public BonusWidget(Player player) {
            Image bonus_0 = new Image(Graphics.Sprites.get("bow_02a"));
            Image bonus_1 = new Image(Graphics.Sprites.get("bow_02a"));
            Image bonus_2 = new Image(Graphics.Sprites.get("bow_02a"));
            bonus_0.setScaling(Scaling.fit);
            bonus_1.setScaling(Scaling.fit);
            bonus_2.setScaling(Scaling.fit);
            this.add(bonus_0).grow().space(10);
            this.add(bonus_1).grow().space(10);
            this.add(bonus_2).grow().space(10);
        }
    }
}
