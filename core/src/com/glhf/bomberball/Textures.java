package com.glhf.bomberball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class Textures {
    private static HashMap<String, Texture> textures;

    private static TextureAtlas atlas;
    private static HashMap<String, AtlasRegion> atlasRegions;

    public static Texture get(String id){
        if(!textures.containsKey(id)) {
            System.err.println("la texture \"" + id + "\" n'existe pas");
        }
        return textures.get(id);
    }

    public static AtlasRegion getAtlasRegion(String atlasRegion_str)
    {
        if (!atlasRegions.containsKey(atlasRegion_str)) {
            System.err.println("AtlasRegion " + atlasRegion_str + " doesn't exists");
        }
        return atlasRegions.get(atlasRegion_str);
    }

    public static void loadTextures() {
        textures = new HashMap<String, Texture>();
        loadTextures("core/assets/img/");
    }

    public static void loadAtlasRegions() {
        atlas = new TextureAtlas("core/packedImages/pack.atlas");
        atlasRegions = new HashMap<String, AtlasRegion>();
        for (AtlasRegion atlasRegion : atlas.getRegions()) {
            atlasRegions.put(atlasRegion.name, atlasRegion);
            System.out.println("AtlasRegion " + atlasRegion.name + " loaded");
        }
        System.out.println(atlasRegions.size() + " atlasRegions succesfully loaded");
    }

    private static void loadTextures(String path) {
        File f = new File(path);
        File[] files = f.listFiles();
        assert files != null;// le fichier f doit être un dossier
        for(File file : files) {
            if (file.isDirectory()){
                loadTextures(path + file.getName() + "/");
                continue;
            }
            String stringId = file.getName().split("[.]")[0];
            System.out.println("[LOAD] "+path + file.getName()+" --> "+stringId);
            textures.put(stringId, new Texture(Gdx.files.internal(path + file.getName())));
        }
    }
}
