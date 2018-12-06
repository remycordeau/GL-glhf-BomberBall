package com.glhf.bomberball;

import java.io.File;

public class Constants {
    // textures properties
    public static final int BOX_WIDTH = 16;
    public static final int BOX_HEIGHT = 16;
    public static final int APP_WIDTH = 720;
    public static final int APP_HEIGHT = 480;


    // constans for paths
    public static final String PATH_ASSET         = "core/assets/";
    public static final String PATH_MAZE          = PATH_ASSET+"maze/";
    public static final String PATH_CONFIG_FILE   = PATH_ASSET+"config.json";
    public static final String PATH_GAMEOBJECT    = PATH_ASSET+"Config/config.txt";
    public static final String PATH_CONFIG_TMP    = PATH_ASSET+"Config/configtmp.txt";
    public static final String PATH_GRAPHICS      = PATH_ASSET+"graphics/";
    public static final String PATH_FONTS         = PATH_GRAPHICS+"fonts/";
    public static final String PATH_PACKS         = PATH_GRAPHICS+"packs/";
    public static final String PATH_ATLAS_SPRITES = PATH_PACKS+"pack_sprites.atlas";
    public static final String PATH_ATLAS_ANIMS   = PATH_PACKS+"pack_animations.atlas";
    public static final String PATH_ATLAS_GUI     = PATH_PACKS+"pack_gui.atlas";

    // contants for assets
    public static final int NB_FLOOR_VARIATION = 8;

    // constants for enemies
    public enum moves {UP, DOWN, RIGHT, LEFT}
    public enum enemyState {ACTIVE, HUNTER}
}
