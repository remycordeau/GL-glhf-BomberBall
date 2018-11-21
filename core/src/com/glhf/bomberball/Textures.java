package com.glhf.bomberball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.util.HashMap;

public class Textures {
    private static HashMap<String, Texture> textures;

    public static Texture getTexture(String id){
        if(!textures.containsKey(id)) {
            System.err.println("la texture \"" + id + "\" n'existe pas");
        }
        return textures.get(id);
    }

    public static void loadTextures() {
        textures = new HashMap<String, Texture>();
        loadTextures("core/assets/");
    }
    private static void loadTextures(String path) {
        textures = new HashMap<String, Texture>();
        File f = new File(path);
        File[] files = f.listFiles();
        assert files != null;// le fichier f doit être un dossier
        for(File file : files) {
            if (file.isDirectory()){
                loadTextures(path + file.getName() + "/");
                continue;
            }
            String stringId = file.getName().replace(".png", "");
            System.out.println(stringId);
            textures.put(stringId, new Texture(Gdx.files.internal(path + file.getName())));
        }
    }
}
