package com.glhf.bomberball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

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
        private static TextureAtlas gui_atlasTexture;
        private static HashMap<String, AtlasRegion> gui_atlasRegions;
        private static float initial_height;

        private static void load()
        {
            initial_height = Gdx.graphics.getHeight();
            loadAtlas();
            loadSkin();
        }

        public static Skin getSkin(){
            return skin;
        }

        public static void scaleFont() {
            for (ObjectMap.Entry<String, BitmapFont> f : skin.getAll(BitmapFont.class)) {
                f.value.getData().setScale(Gdx.graphics.getHeight() / initial_height);
            }
        }

        private static void loadSkin() {

            skin = new Skin();

            //
            Texture white = new Texture(new Pixmap(1,1, Format.RGB888));
            skin.add("white", white);
            skin.add("bomb", new TextureRegionDrawable(Sprites.get("bomb")));

            //
            skin.addRegions(new TextureAtlas(Constants.PATH_ATLAS_GUI));

            //BitmapFont font = new BitmapFont(new FileHandle(Constants.PATH_FONTS + "Calibri/Calibri.fnt"));

            /* Génération de la BitmapFont avec FreeTypeFontGenerator */
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle(Constants.PATH_FONTS + "Compass/CompassPro.ttf"));
            FreeTypeFontParameter parameter = new FreeTypeFontParameter();
            parameter.size = 32;
            BitmapFont font = generator.generateFont(parameter);
            skin.add("small", font);
            parameter.size = 52;
            font = generator.generateFont(parameter);
            skin.add("default", font);
            generator.dispose();

            NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("core/assets/graphics/gui/rock_9patch.png"), 16, 16, 16, 16));
            TextButtonStyle textButtonStyle = new TextButtonStyle();
            textButtonStyle.up = patch;
            textButtonStyle.down = patch;
            textButtonStyle.over = patch;
            textButtonStyle.checked = patch;
            textButtonStyle.font = font;
            textButtonStyle.fontColor = Color.WHITE;
            textButtonStyle.overFontColor = Color.GRAY;
            textButtonStyle.downFontColor = Color.RED;
            skin.add("default", textButtonStyle);

            //
            textButtonStyle.up = patch;
            textButtonStyle.down = patch;
            textButtonStyle.over = patch;
            textButtonStyle.checked = patch;
            textButtonStyle.font = skin.getFont("small");
            textButtonStyle.fontColor = Color.WHITE;
            textButtonStyle.overFontColor = Color.GRAY;
            textButtonStyle.downFontColor = Color.RED;
            skin.add("small", textButtonStyle);
            //
            LabelStyle labelStyle = new LabelStyle();
            labelStyle.font = font;
            skin.add("default", labelStyle);
            //
            labelStyle = new LabelStyle();
            labelStyle.font = skin.getFont("small");
            skin.add("small", labelStyle);

            //
            SliderStyle sliderStyle = new SliderStyle();
            sliderStyle.knob = skin.get("bomb", TextureRegionDrawable.class);
            sliderStyle.background = skin.getDrawable("white");
            skin.add("default-horizontal", sliderStyle);

            //
            ListStyle listStyle = new ListStyle();
            listStyle.font = font;
            listStyle.fontColorSelected = Color.WHITE;
            listStyle.fontColorUnselected = Color.RED;
            listStyle.selection = skin.get("bomb", TextureRegionDrawable.class);
            listStyle.background = skin.getDrawable("white");
            skin.add("default", listStyle);

            //
            ScrollPaneStyle scrollStyle = new ScrollPaneStyle();
            scrollStyle.background = skin.getDrawable("white");
            scrollStyle.hScroll = skin.getDrawable("white");
            scrollStyle.hScrollKnob = skin.get("bomb", TextureRegionDrawable.class);
            scrollStyle.vScroll = skin.getDrawable("white");
            scrollStyle.vScrollKnob = skin.get("bomb", TextureRegionDrawable.class);
            skin.add("default", scrollStyle);

            //
            SelectBoxStyle selectBoxStyle = new SelectBoxStyle();
            selectBoxStyle.font = font;
            selectBoxStyle.fontColor = Color.BLUE;
            selectBoxStyle.background = skin.getDrawable("white");
            selectBoxStyle.listStyle = skin.get(ListStyle.class);
            selectBoxStyle.scrollStyle = skin.get(ScrollPaneStyle.class);
            skin.add("default", selectBoxStyle);
        }

        private static void loadAtlas()
        {
            gui_atlasTexture = new TextureAtlas(Constants.PATH_ATLAS_GUI);
            gui_atlasRegions = new HashMap<String, AtlasRegion>();
            for (AtlasRegion atlasRegion : gui_atlasTexture.getRegions()) {
                gui_atlasRegions.put(atlasRegion.name, atlasRegion);
                System.out.println("GUI element" + atlasRegion.name + " loaded");
            }
            System.out.println(gui_atlasRegions.size() + " gui elements succesfully loaded");
        }

        public static AtlasRegion get(String sprite_str)
        {
            if (!gui_atlasRegions.containsKey(sprite_str)) {
                throw new RuntimeException("GUI element " + sprite_str + " doesn't exists");
            }
            return gui_atlasRegions.get(sprite_str);
        }
    }

    public static void load() {
        Graphics.Sprites.load();
        Graphics.Anims.load();
        Graphics.GUI.load();
    }

}
