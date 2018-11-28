package com.glhf.bomberball;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class Graphics {
    private static TextureAtlas atlas;
    private static HashMap<String, TextureAtlas.AtlasRegion> atlasRegions;

    public static void load() {
        atlas = new TextureAtlas("core/packedImages/pack.atlas");
        atlasRegions = new HashMap<String, TextureAtlas.AtlasRegion>();
        for (TextureAtlas.AtlasRegion atlasRegion : atlas.getRegions()) {
            atlasRegions.put(atlasRegion.name, atlasRegion);
            System.out.println("AtlasRegion " + atlasRegion.name + " loaded");
        }
        System.out.println(atlasRegions.size() + " atlasRegions succesfully loaded");
    }

    public static TextureAtlas.AtlasRegion get(String atlasRegion_str)
    {
        if (!atlasRegions.containsKey(atlasRegion_str)) {
            System.err.println("AtlasRegion " + atlasRegion_str + " doesn't exists");
        }
        return atlasRegions.get(atlasRegion_str);
    }

}
