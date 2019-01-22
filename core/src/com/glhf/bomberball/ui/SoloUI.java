/**
 * @author : Rémy
 * creates the user interface when the user is playing a level in the story mode
 */
package com.glhf.bomberball.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.screens.GameStoryScreen;

import java.util.ArrayList;

public class SoloUI extends Table {

    public SoloUI(Player player, GameStoryScreen screen) {
        this.setFillParent(true);

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player);
        PlayersInfoUI left_ui = new PlayersInfoUI(players);
        left_ui.padRight(Value.percentWidth(2/3f));
        left_ui.setFillParent(true);
        ActionPlayerUI bottom_ui = new ActionPlayerUI(screen);
        bottom_ui.setFillParent(true);
        bottom_ui.align(Align.bottom);
        this.addActor(left_ui);
        this.addActor(bottom_ui);
    }
}
