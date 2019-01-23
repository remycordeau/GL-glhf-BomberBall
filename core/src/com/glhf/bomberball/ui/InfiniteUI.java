/**
 * @author : Rémy
 * creates the user interface when the user is playing a level in the story mode
 */
package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.config.GameInfiniteConfig;
import com.glhf.bomberball.gameobject.NumberTurn;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.gameobject.Score;
import com.glhf.bomberball.screens.GameInfiniteScreen;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class InfiniteUI extends MenuUI {

    public InfiniteUI(Player player, GameInfiniteScreen screen) {
        this.setFillParent(true);

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player);
        PlayersInfoUI left_ui = new PlayersInfoUI(players);
        left_ui.setFillParent(true);
        left_ui.padRight(Value.percentWidth(2/3f));
        left_ui.padBottom(Value.percentHeight(0.20f));
        left_ui.align(Align.top);

        ActionPlayerUI bottom_ui = new ActionPlayerUI(screen);
        bottom_ui.setFillParent(true);
        bottom_ui.padTop(Value.percentHeight(0.80f));
        bottom_ui.align(Align.bottom);

        ScoreUI top_left_ui = new ScoreUI();
        top_left_ui.setFillParent(true);
        top_left_ui.padRight(Value.percentWidth(2/3f));
        top_left_ui.padBottom(Value.percentHeight(0.50f));
        top_left_ui.align(Align.topLeft);

        TurnUI middle_left_ui = new TurnUI();
        middle_left_ui.setFillParent(true);
        middle_left_ui.padRight(Value.percentWidth(2/3f));
        middle_left_ui.padBottom(Value.percentHeight(0.1f));
        middle_left_ui.align(Align.center);

        if(GameInfiniteConfig.get().finite_number_turn) {
            this.addActor(middle_left_ui);
        }
        this.addActor(left_ui);
        this.addActor(top_left_ui);
        this.addActor(bottom_ui);
        debug();
    }

    private class ScoreUI extends Table implements Observer {
        private final Score sc;
        private Label label;

        public ScoreUI() {
            super();
            label = new Label("Score : ", Graphics.GUI.getSkin());
            sc = Score.getINSTANCE();
            sc.addObserver(this);
            add(label).growX();
            update(null, null);
        }

        @Override
        public void update(Observable observable, Object o) {
            label.setText("Score : "+sc.getScore());
        }
    }

    private class TurnUI extends Table implements Observer {
        private final NumberTurn nt;
        private Label label2;

        public TurnUI() {
            super();
            label2 = new Label("Tour restant : ", Graphics.GUI.getSkin());
            nt = NumberTurn.getINSTANCE();
            nt.addObserver(this);
            add(label2).growX();
            update(null, null);
        }

        @Override
        public void update(Observable observable, Object o) {
            label2.setText("Tours restants : "+nt.getNbTurn());

        }
    }
}
