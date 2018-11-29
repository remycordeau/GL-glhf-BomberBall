package com.glhf.bomberball;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class Graphics {

    public static class Sprites {
        private static TextureAtlas sprites_atlasTexture;;
        private static HashMap<String, AtlasRegion> sprites_atlasRegions;

        private static void load()
        {
            sprites_atlasTexture = new TextureAtlas(Constants.PATH_ATLAS_SPRITES);
            sprites_atlasRegions = new HashMap<String, AtlasRegion>();
            for (AtlasRegion atlasRegion : sprites_atlasTexture.getRegions()) {
                sprites_atlasRegions.put(atlasRegion.name, atlasRegion);
                System.out.println("Sprite " + atlasRegion.name + " loaded");
            }
            System.out.println(sprites_atlasRegions.size() + " sprites succesfully loaded");
        }

        public static AtlasRegion get(String sprite_str)
        {
            if (!sprites_atlasRegions.containsKey(sprite_str)) {
                System.err.println("Sprite " + sprite_str + " doesn't exists");
            }
            return sprites_atlasRegions.get(sprite_str);
        }
    }

    public static class Anims {
        private static TextureAtlas anim_atlasTexture;
        private static HashMap<String, Array<AtlasRegion>> anim_atlasRegions;

        private static void load()
        {
            anim_atlasTexture = new TextureAtlas(Constants.PATH_ATLAS_ANIMS);
            anim_atlasRegions = new HashMap<String, Array<AtlasRegion>>();
            for (AtlasRegion atlasRegion : anim_atlasTexture.getRegions()) {
                if (!anim_atlasRegions.containsKey(atlasRegion.name)) {
                    anim_atlasRegions.put(atlasRegion.name, anim_atlasTexture.findRegions(atlasRegion.name));
                    System.out.println("Animation " + atlasRegion.name + " loaded");
                }
            }
            System.out.println(anim_atlasRegions.size() + " animations succesfully loaded");
        }

        public static Array<AtlasRegion> get(String anim_str)
        {
            if (!anim_atlasRegions.containsKey(anim_str)) {
                System.err.println("Animation " + anim_str + " doesn't exists");
            }
            return anim_atlasRegions.get(anim_str);
        }
    }

    public static void load() {
        Graphics.Sprites.load();
        Graphics.Anims.load();
    }

}
