package com.glhf.bomberball.maze.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.glhf.bomberball.Graphics;

public class SelectEffect extends CellEffect {

    public SelectEffect(Cell cell, Color color) {
        super(cell);
        this.color = color;
        animation = new Animation<AtlasRegion>(0.07f, Graphics.Anims.get("cell/select/white"), Animation.PlayMode.LOOP_PINGPONG);
    }
}
