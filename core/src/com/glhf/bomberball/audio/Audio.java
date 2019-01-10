package com.glhf.bomberball.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * @author jyra
 * Example : Audio.CLICK_BUTTON.play() to play a song
 */
public enum Audio {
    CLICK_BUTTON("core/assets/sounds/SFX/click_button.wav");

    public Sound sound;
    Audio(String path)
    {
        sound= Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public void play()
    {
        sound.play();
    }


}
