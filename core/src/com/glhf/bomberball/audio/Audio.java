package com.glhf.bomberball.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * @author jyra
 * Example : Audio.CLICK_BUTTON.play() to play a song
 */
public enum Audio {
    CLICK_BUTTON("core/assets/sounds/click_button.wav"),
    VICTORY("core/assets/sounds/victory.wav"),
    CLICK_PLAY("core/assets/sounds/PlayLevelButtonTone.wav"),
    MAIN_MENU("core/assets/sounds/MainMenu.mp3");

    public Sound sound;
    Audio(String path)
    {
        sound= Gdx.audio.newSound(Gdx.files.internal(path));
        System.out.println("Sounds " + path + " loaded");
    }

    public void play() {
        this.silence();
        sound.play(); }
    public void dispose() { sound.dispose(); }
    public void playloop(){ sound.loop();}
    public void silence () {
        Audio[] a = Audio.values();
        for (Audio l : a) {
            l.dispose();
        }
    }

}
