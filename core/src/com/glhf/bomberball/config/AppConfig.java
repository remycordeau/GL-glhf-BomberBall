package com.glhf.bomberball.config;

public class AppConfig extends Config {

    public int screen_width = 960;
    public int scree_height = 540;

    public boolean fullscreen = false;

    public static AppConfig get(String config_app) {
        return get(config_app, AppConfig.class);
    }
}
