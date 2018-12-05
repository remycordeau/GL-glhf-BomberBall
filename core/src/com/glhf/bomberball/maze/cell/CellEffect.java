package com.glhf.bomberball.maze.cell;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;

public abstract class CellEffect {

    private Cell cell;

    protected transient Animation<AtlasRegion> animation;

    public CellEffect(Cell cell) {
        this.cell = cell;
    }

    public AtlasRegion getSprite() {
        return animation.getKeyFrame(Game.time_elapsed);
    }

    public void dispose() {
        cell.removeEffect();
    }
}
