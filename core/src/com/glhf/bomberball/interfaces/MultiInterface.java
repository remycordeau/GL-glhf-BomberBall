package com.glhf.bomberball.interfaces;

import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.glhf.bomberball.gameobject.Player;
import com.glhf.bomberball.widgets.PlayersWidget;

import java.util.ArrayList;

public class MultiInterface extends AbstractInterface {

    public MultiInterface(ArrayList<Player> players) {
        PlayersWidget left_ui = new PlayersWidget(players);
        left_ui.padRight(Value.percentWidth(2/3f));
        left_ui.setFillParent(true);
        this.addActor(left_ui);
    }

}
