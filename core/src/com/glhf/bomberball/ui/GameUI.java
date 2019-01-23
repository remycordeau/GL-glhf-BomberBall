package com.glhf.bomberball.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.gameobject.Score;
import com.glhf.bomberball.screens.AbstractScreen;
import com.glhf.bomberball.screens.GameMultiScreen;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static com.glhf.bomberball.utils.Constants.PATH_GRAPHICS;

public class GameUI extends Table {

    public GameUI(ArrayList<Player> players, boolean show_score) {
        setup(players, show_score);
    }

    public GameUI(Player player, boolean show_score) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player);
        setup(players, show_score);
    }

    public void setup(ArrayList<Player> players, boolean show_score) {
        this.setFillParent(true);

        ScoreUI score_ui = new ScoreUI();
        PlayersInfoUI left_ui = new PlayersInfoUI(players);
        ActionPlayerUI bottom_ui = new ActionPlayerUI();

        if (show_score) {
            this.add(score_ui).growX().width(Value.percentWidth(1/3f,this)).height(Value.percentHeight(0.1f, this)).align(Align.left);
            this.row();
        }
        this.add(left_ui).grow().width(Value.percentWidth(1/3f,this)).align(Align.left);
        this.row();
        this.add(bottom_ui).growX().height(Value.percentHeight(0.20f, this)).align(Align.bottom);
    }

    class ScoreUI extends Table implements Observer {
        private final Score sc;
        private Label label;

        public ScoreUI() {
            super();
            label = new Label("Score : ", Graphics.GUI.getSkin());
            label.setAlignment(Align.center);
            sc = Score.getINSTANCE();
            sc.addObserver(this);
            add(label).growX();
            update(null, null);
        }

        @Override
        public void update(Observable observable, Object o) {
            label.setText("Score : " + sc.getScore());
        }
    }
}
