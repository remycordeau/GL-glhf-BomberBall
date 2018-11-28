package com.glhf.bomberball;

public class Constants {
    public static final int BOX_WIDTH = 16;
    public static final int BOX_HEIGHT = 16;
    public static final int APP_WIDTH = 720;
    public static final int APP_HEIGHT = 480;

    public static final int NB_PLAYER_MAX = 4;


    public static final String FLOOR_TEXTURE_NAME = "floor";

    // constants for paths
    public static final String PATH_MAZE = "core/assets/maze/";
    public static final String PATH_GAMEOBJECT = "core/assets/gameobject/";

    // contants for assets
    public static final int NB_FLOOR_VARIATION = 8;

    // constants for enemies
    public enum moves {UP, DOWN, RIGHT,LEFT}
    public enum enemyState {ACTIVE, HUNTER}
}
