package com.glhf.bomberball;

public class Constants {
    public static final int BOX_WIDTH = 16;
    public static final int BOX_HEIGHT = 16;
    public static final int APP_WIDTH = 4*200;
    public static final int APP_HEIGHT = 3*200;


    public static final String FLOOR_TEXTURE_NAME = "floor";


    public static final String PATH_MAZE = "core/assets/maze/";

    // contants for assets
    public static final int NB_FLOOR_VARIATION = 8;

    // constants for enemies
    public enum moves {UP, DOWN, RIGHT,LEFT}
    public enum enemyState {ACTIVE, HUNTER}
}
