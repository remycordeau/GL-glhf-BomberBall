package com.glhf.bomberball.interfaceMulti;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.gameobject.Player;

public class PlayerInfo extends Actor {
    //attributes
    private Player player;

    //constructor
    public PlayerInfo(Player player) {
        this.player = player;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(player.getSprite(),0,0);
        batch.draw(Graphics.Sprites.get("bomb"),5, 0);
        batch.draw(Graphics.Sprites.get("full_heart"),10,0);
    }
}
