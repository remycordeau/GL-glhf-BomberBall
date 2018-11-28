package com.glhf.bomberball;

public class Constants {
    // textures properties
    public static final int BOX_WIDTH = 16;
    public static final int BOX_HEIGHT = 16;
    public static final int APP_WIDTH = 720;
    public static final int APP_HEIGHT = 480;

    //game properties
    public static final int NB_PLAYER_MAX = 4;

    public static final String FLOOR_TEXTURE_NAME = "floor";

    // constans for paths
    public static final String PATH_ASSET       = "core/assets/";
    public static final String PATH_MAZE        = PATH_ASSET+"maze/";
    public static final String PATH_CONFIG_FILE = PATH_ASSET+"config.json";
    public static final String PATH_GAMEOBJECT =  PATH_ASSET+"gameobject/";

    // contants for assets
    public static final int NB_FLOOR_VARIATION = 8;

    // constants for enemies
    public enum moves {UP, DOWN, RIGHT,LEFT}
    public enum enemyState {ACTIVE, HUNTER}
}
