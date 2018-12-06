package com.glhf.bomberball;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class Graphics {

    public static class Sprites {
        private static TextureAtlas sprites_atlasTexture;
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
                throw new RuntimeException("Sprite " + sprite_str + " doesn't exists");
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
                throw new RuntimeException("Animation " + anim_str + " doesn't exists");
            }
            return anim_atlasRegions.get(anim_str);
        }
    }

    public static class GUI {
        private static Skin skin;

        private static void load()
        {
            skin = new Skin();

            //
            skin.addRegions(new TextureAtlas(Constants.PATH_ATLAS_GUI));

            //load font
            BitmapFont font = new BitmapFont(new FileHandle(Constants.PATH_FONTS + "UniDreamLED.fnt"));

            //
            TextButtonStyle textButtonStyle = new TextButtonStyle();
            textButtonStyle.font = font;
            skin.add("default", textButtonStyle);

            //
            LabelStyle labelStyle = new LabelStyle(); //TODO au lieu de faire des textButton_style pour chaque élément créer un skin général
            labelStyle.font = font;
            skin.add("default", labelStyle);

            //
            ListStyle listStyle = new ListStyle();
            listStyle.font = font;
            listStyle.fontColorSelected = Color.WHITE;
            listStyle.fontColorUnselected = Color.RED;
            listStyle.selection = new TextureRegionDrawable(Sprites.get("bomb"));
            listStyle.background = new TextureRegionDrawable(Sprites.get("bomb"));
            skin.add("default", listStyle);

            //
            ScrollPaneStyle scrollStyle = new ScrollPaneStyle();
            scrollStyle.background = new TextureRegionDrawable(Sprites.get("bomb"));
            scrollStyle.hScroll = new TextureRegionDrawable(Sprites.get("bomb"));
            scrollStyle.hScrollKnob = new TextureRegionDrawable(Sprites.get("bomb"));
            scrollStyle.vScroll = new TextureRegionDrawable(Sprites.get("bomb"));
            scrollStyle.vScrollKnob = new TextureRegionDrawable(Sprites.get("bomb"));
            skin.add("default", scrollStyle);

            //
            SelectBoxStyle selectBoxStyle = new SelectBoxStyle();
            selectBoxStyle.font = font;
            selectBoxStyle.fontColor = Color.BLUE;
            selectBoxStyle.background = new TextureRegionDrawable(Sprites.get("bomb"));
            selectBoxStyle.listStyle = skin.get(ListStyle.class);
            selectBoxStyle.scrollStyle = skin.get(ScrollPaneStyle.class);
            skin.add("default", selectBoxStyle);
        }

        public static Skin getSkin(){
            return skin;
        }


    }

    public static void load() {
        Graphics.Sprites.load();
        Graphics.Anims.load();
        Graphics.GUI.load();
    }

}
