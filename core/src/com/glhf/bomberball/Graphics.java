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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.glhf.bomberball.utils.Constants;

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
            skin.add("checkboxOff", new TextureRegionDrawable(GUI.get("checkboxOff")));
            skin.add("checkboxOn", new TextureRegionDrawable(GUI.get("checkboxOn")));

            skin.add("default", Color.WHITE);

            //
            skin.addRegions(new TextureAtlas(Constants.PATH_ATLAS_GUI));

            //BitmapFont font = new BitmapFont(new FileHandle(Constants.PATH_FONTS + "Calibri/Calibri.fnt"));

            /* Génération de la BitmapFont avec FreeTypeFontGenerator */
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle(Constants.PATH_FONTS + "Compass/CompassPro.ttf"));
            FreeTypeFontParameter parameter = new FreeTypeFontParameter();
            parameter.size = 32;
            BitmapFont font = generator.generateFont(parameter);
            skin.add("small", font);
            parameter.size = 26;
            font = generator.generateFont(parameter);
            skin.add("very_small", font);
            parameter.size = 38;
            font = generator.generateFont(parameter);
            skin.add("default", font);
            generator.dispose();

            //==========TextButtonStyle
            NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("core/assets/graphics/gui/rock_9patch.png"), 16, 16, 16, 16));
            NinePatchDrawable patch2 = new NinePatchDrawable(new NinePatch(new Texture("core/assets/graphics/gui/rock_disable_9patch.png"), 16, 16, 16, 16));
            TextButtonStyle textButtonStyle = new TextButtonStyle(patch, patch, patch, skin.getFont("default"));
            textButtonStyle.fontColor = Color.WHITE;
            textButtonStyle.overFontColor = Color.GRAY;
            textButtonStyle.downFontColor = Color.RED;
            textButtonStyle.disabled = patch2;
            skin.add("default", textButtonStyle);

            textButtonStyle = new TextButtonStyle(textButtonStyle);//copy of textButtonStyle
            textButtonStyle.font = skin.getFont("small");
            skin.add("small", textButtonStyle);

            textButtonStyle = new TextButtonStyle(textButtonStyle);//copy of textButtonStyle
            textButtonStyle.font = skin.getFont("very_small");
            textButtonStyle.checked = patch.tint(Color.RED);
            skin.add("input_select", textButtonStyle);

            textButtonStyle = new TextButtonStyle(patch2,patch2,patch2,skin.getFont("default"));
            textButtonStyle.overFontColor = Color.GRAY;
            textButtonStyle.fontColor = Color.WHITE;
            skin.add("locked level",textButtonStyle);

            //========LabelStyle
            LabelStyle labelStyle = new LabelStyle();
            labelStyle.font = font;
            skin.add("default", labelStyle);

            labelStyle = new LabelStyle(labelStyle);//copy of labelStyle
            labelStyle.font = skin.getFont("small");
            skin.add("small", labelStyle);

            labelStyle = new LabelStyle(labelStyle);//copy of labelStyle
            labelStyle.font = skin.getFont("very_small");
            skin.add("very_small", labelStyle);

            labelStyle = new LabelStyle(labelStyle);//copy of labelStyle
            labelStyle.font = font;
            labelStyle.fontColor = Color.GREEN;
            skin.add("Title", labelStyle);


            //=======SliderStyle
            SliderStyle sliderStyle = new SliderStyle();
            sliderStyle.knob = skin.get("bomb", TextureRegionDrawable.class);
            sliderStyle.background = skin.getDrawable("white");
            skin.add("default-horizontal", sliderStyle);

            //=======ListStyle
            ListStyle listStyle = new ListStyle();
            listStyle.font = font;
            listStyle.fontColorSelected = Color.WHITE;
            listStyle.fontColorUnselected = Color.RED;
            listStyle.selection = skin.get("bomb", TextureRegionDrawable.class);
            listStyle.background = skin.getDrawable("white");
            skin.add("default", listStyle);

            //=======ScrollPaneStyle
            ScrollPaneStyle scrollStyle = new ScrollPaneStyle();
            scrollStyle.background = skin.getDrawable("white");
            scrollStyle.hScroll = skin.getDrawable("white");
            scrollStyle.hScrollKnob = skin.get("bomb", TextureRegionDrawable.class);
            scrollStyle.vScroll = skin.getDrawable("white");
            scrollStyle.vScrollKnob = skin.get("bomb", TextureRegionDrawable.class);
            skin.add("default", scrollStyle);

            //========SelectBoxStyle
            SelectBoxStyle selectBoxStyle = new SelectBoxStyle();//TODO meilleur visuel
            selectBoxStyle.font = font;
            selectBoxStyle.fontColor = Color.BLUE;
            selectBoxStyle.background = skin.getDrawable("white");
            selectBoxStyle.listStyle = skin.get(ListStyle.class);
            selectBoxStyle.scrollStyle = skin.get(ScrollPaneStyle.class);
            skin.add("default", selectBoxStyle);

            //=======CheckBoxStyle
            CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
            checkBoxStyle.checkboxOff = skin.get("checkboxOff", TextureRegionDrawable.class);
            checkBoxStyle.checkboxOn = skin.get("checkboxOn", TextureRegionDrawable.class);
            checkBoxStyle.font = skin.getFont("default");
            checkBoxStyle.fontColor = skin.getColor("default");
            skin.add("default", checkBoxStyle);
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
