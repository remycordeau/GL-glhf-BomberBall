package com.glhf.bomberball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.ui.MapEditorUI;

public class MapEditorScreen extends AbstractScreen {

    public MapEditorScreen()
    {
        super();
        addUI(new MapEditorUI(this));
    }
}
