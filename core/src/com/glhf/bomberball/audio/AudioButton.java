package com.glhf.bomberball.audio;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class AudioButton extends TextButton {
    public AudioButton(String text, Skin skin){
        super(text, skin);
        this.addListener(new ListenerAudioButton());
    }
}
