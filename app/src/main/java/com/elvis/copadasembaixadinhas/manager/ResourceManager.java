package com.elvis.copadasembaixadinhas.manager;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.camera.FollowCamera;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import java.io.IOException;

/**
 * Created by com.copadasembaixadinhas.elvis on 24/04/14.
 */
public class ResourceManager {

    public static final ResourceManager INSTANCE = new ResourceManager();
    public Font font, groupFont, titleFont, groupViewTitleFont, groupViewTitlesFont,
                groupViewTextFont, gameScoreFont;

    public Engine engine;
    public MainActivity activity;
    public FollowCamera camera;
    public VertexBufferObjectManager vbom;
    public String currentTeam = "aus";

    public Sound jumpSound, playSound;

    public ITextureRegion splashRegion, moreAppsBgRegion, fgtsBtn, gogoJohnnyBtn, flightDroneBtn,
                mainMenuBackgroundRegion, button, loadingBgRegion, gameBgBtn,
                brazilIcon, cameroonIcon, croatiaIcon, mexicoIcon,
                australiaIcon, chileIcon, netherlandsIcon, spainIcon,
                colombiaIcon, costaDoMarfinIcon, greeceIcon, japanIcon,
                costaRicaIcon, englandIcon, italyIcon, uruguayIcon,
                ecuadorIcon, franceIcon, hondurasIcon, swizerlandIcon,
                argentinaIcon, bosniaAndHerzegovinaIcon, iranIcon, nigeriaIcon,
                germanyIcon, ghanaIcon, portugalIcon, usaIcon,
                algeriaIcon, belgiumIcon, koreaRepublicIcon, russiaIcon,
                bgGameBtn, gamingBgRegion, shoesBtn, lockIconTextureRegion;

    public ITiledTextureRegion gloveTextureRegion, ballTextureRegion;

    private BuildableBitmapTextureAtlas mainMenuTextureAtlas, moreAppsTextureAtlas,
                gamingSceneTextureAtlas;

    private BitmapTextureAtlas splashTextureAtlas;

    public static ResourceManager getInstance () {
        return INSTANCE;
    }

    public static void prepareManager(Engine pEngine, MainActivity pActivity, FollowCamera pCamera, VertexBufferObjectManager pVbom) {
        getInstance().engine = pEngine;
        getInstance().activity = pActivity;
        getInstance().camera = pCamera;
        getInstance().vbom = pVbom;
    }

    public void loadAllResources () {
 //       loadIconGraphic();

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        final BuildableBitmapTextureAtlas loadingTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);
        loadingTextureAtlas.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
  //      button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mainMenuTextureAtlas, activity, "button.png");
        loadingBgRegion =  BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadingTextureAtlas, activity, "backgrounds/choose_shoes_bg.png");

        try {
            loadingTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            loadingTextureAtlas.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }

        loadFonts();
        loadGameSound();
    }

    public void loadMainMenuResources () {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        mainMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);
        this.mainMenuTextureAtlas.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mainMenuTextureAtlas, activity, "button.png");
        gameBgBtn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mainMenuTextureAtlas, activity, "game_btn_bg.png");
        mainMenuBackgroundRegion =  BitmapTextureAtlasTextureRegionFactory.createFromAsset(mainMenuTextureAtlas, activity, "backgrounds/choose_shoes_bg.png");

        try {
            this.mainMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.mainMenuTextureAtlas.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    public void loadMoreAppsResources () {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        moreAppsTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);
        this.moreAppsTextureAtlas.addEmptyTextureAtlasSource(0, 0, 1024, 1024);

        moreAppsBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(moreAppsTextureAtlas, activity, "backgrounds/more_bg.png");

        fgtsBtn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(moreAppsTextureAtlas, activity, "fgts_corrigido_btn.png");
        flightDroneBtn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(moreAppsTextureAtlas, activity, "flight_drone_btn.png");
        gogoJohnnyBtn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(moreAppsTextureAtlas, activity, "gogo_johnny_btn.png");
//        button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(moreAppsTextureAtlas, activity, "button.png");

        try {
            this.moreAppsTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.moreAppsTextureAtlas.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }

    }

    public void loadEmbaixadinhasGameResources (final String pTeam) {
        ResourceManager.getInstance().currentTeam = pTeam;

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        gamingSceneTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);
        this.gamingSceneTextureAtlas.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
//        button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "button.png");

        gamingBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "backgrounds/embaixadinhas_bg.png");
        bgGameBtn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "backgrounds/bg_group_btn.png");
        ballTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "ball_6.png", 3, 2);
        shoesBtn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/brazil.png");

        if (pTeam.equals("aus")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_australia_sprite.png", 3, 1);
        } else if (pTeam.equals("kor")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_korea_sprite.png", 3, 1);
        } else if (pTeam.equals("cam")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_cameroon_sprite.png", 3, 1);
        } else if (pTeam.equals("jap")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_japan_sprite.png", 3, 1);
        } else if (pTeam.equals("nig")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_nigeria_sprite.png", 3, 1);
        } else if (pTeam.equals("ira")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_iran_sprite.png", 3, 1);
        } else if (pTeam.equals("gha")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_ghana_sprite.png", 3, 1);
        } else if (pTeam.equals("hon")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_honduras_sprite.png", 3, 1);
        } else if (pTeam.equals("cos")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_costa_rica_sprite.png", 3, 1);
        } else if (pTeam.equals("ecu")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_ecuador_sprite.png", 3, 1);
        } else if (pTeam.equals("cdm")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_costa_do_marfim_sprite.png", 3, 1);
        } else if (pTeam.equals("alg")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_algeria_sprite.png", 3, 1);
        } else if (pTeam.equals("bos")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_bosnia_sprite.png", 3, 1);
        } else if (pTeam.equals("mex")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_mexico_sprite.png", 3, 1);
        } else if (pTeam.equals("rus")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_russia_sprite.png", 3, 1);
        } else if (pTeam.equals("cro")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_croatia_sprite.png", 3, 1);
        } else if (pTeam.equals("fra")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_france_sprite.png", 3, 1);
        } else if (pTeam.equals("hol")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_netherland_sprite.png", 3, 1);
        } else if (pTeam.equals("chi")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_chile_sprite.png", 3, 1);
        } else if (pTeam.equals("usa")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_usa_sprite.png", 3, 1);
        } else if (pTeam.equals("gre")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_greece_sprite.png", 3, 1);
        } else if (pTeam.equals("bel")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_belgium_sprite.png", 3, 1);
        } else if (pTeam.equals("eng")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_england_sprite.png", 3, 1);
        } else if (pTeam.equals("ita")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_italy_sprite.png", 3, 1);
        } else if (pTeam.equals("col")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_colombia_sprite.png", 3, 1);
        } else if (pTeam.equals("uru")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_uruguay_sprite.png", 3, 1);
        } else if (pTeam.equals("sui")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_switzerland_sprite.png", 3, 1);
        } else if (pTeam.equals("arg")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_argentina_sprite.png", 3, 1);
        } else if (pTeam.equals("por")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_portugal_sprite.png", 3, 1);
        } else if (pTeam.equals("ger")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_germany_sprite.png", 3, 1);
        } else if (pTeam.equals("spa")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_spain_sprite.png", 3, 1);
        } else if (pTeam.equals("bra")) {
            gloveTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gamingSceneTextureAtlas, activity, "sprites/chuteira_brazil_sprite.png", 3, 1);
        }

        try {
            this.gamingSceneTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.gamingSceneTextureAtlas.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    public void loadChooseShoesResources () {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        gamingSceneTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);
        this.gamingSceneTextureAtlas.addEmptyTextureAtlasSource(0, 0, 1024, 1024);
        button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "button_short.png");
        lockIconTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "lock.png");

        gamingBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "backgrounds/choose_shoes_bg.png");

        brazilIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/brazil.png");
        cameroonIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/cameroon.png");
        croatiaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/croatia.png");
        mexicoIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/mexico.png");

        spainIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/spain.png");
        netherlandsIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/netherlands.png");
        chileIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/chile.png");
        australiaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/australia.png");

        colombiaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/colombia.png");
        costaDoMarfinIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/costa_do_marfim.png");
        greeceIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/greece.png");
        japanIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/japan.png");

        costaRicaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/costa_rica.png");
        englandIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/england.png");
        italyIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/italy.png");
        uruguayIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/uruguay.png");

        ecuadorIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/ecuador.png");
        franceIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/france.png");
        hondurasIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/honduras.png");
        swizerlandIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/switzerland.png");

        argentinaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/argentina.png");
        bosniaAndHerzegovinaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/bosnia.png");
        iranIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/iran.png");
        nigeriaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/nigeria.png");

        germanyIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/germany.png");
        ghanaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/ghana.png");
        portugalIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/portugal.png");
        usaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/usa.png");

        algeriaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/algeria.png");
        belgiumIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/belgium.png");
        koreaRepublicIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/korea.png");
        russiaIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamingSceneTextureAtlas, activity, "shoes/russia.png");

        try {
            this.gamingSceneTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.gamingSceneTextureAtlas.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }

    }

    public void loadSplashResources () {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 480, 800, TextureOptions.BILINEAR);
        splashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
        splashTextureAtlas.load();
    }

    public void loadGameSound() {
        try {
            jumpSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sfx/jumpSound.wav");
//            dieSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sfx/deadSound.wav");
//            scoreSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sfx/rewardSound.wav");
            playSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sfx/buttonSound.wav");
//            runSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sfx/runSound.wav");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadFonts () {
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "vipnagor.ttf", 42f, true, Color.CYAN_ARGB_PACKED_INT, 2f, Color.BLACK_ARGB_PACKED_INT);
        font.load();
        final ITexture mainFontTexture2 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        titleFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture2, activity.getAssets(), "vipnagor.ttf", 50f, true, android.graphics.Color.rgb(105, 105, 105), 2f, android.graphics.Color.BLACK);
        titleFont.load();
        final ITexture mainFontTexture7 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        gameScoreFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture7, activity.getAssets(), "vipnagor.ttf", 50f, true, android.graphics.Color.RED, 2f, android.graphics.Color.YELLOW);
        gameScoreFont.load();
        final ITexture mainFontTexture3 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        groupFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture3, activity.getAssets(), "vipnagor.ttf", 50f, true, android.graphics.Color.rgb(0, 102, 0)/*.RED*/, 2f, android.graphics.Color.rgb(255, 215, 0)/*.BLUE*/);
        groupFont.load();
        final ITexture mainFontTexture4 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        groupViewTitleFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture4, activity.getAssets(), "vipnagor.ttf", 50f, true, android.graphics.Color.rgb(0, 102, 0), 2f, android.graphics.Color.rgb(255, 215, 0));
        groupViewTitleFont.load();
        final ITexture mainFontTexture5 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        groupViewTextFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture5, activity.getAssets(), "nonserif.ttf", 50f, true, android.graphics.Color.BLACK, 1f, android.graphics.Color.BLACK);
        groupViewTextFont.load();
        final ITexture mainFontTexture6 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        groupViewTitlesFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture6, activity.getAssets(), "nonserif.ttf", 35f, true, android.graphics.Color.BLACK, 1f, android.graphics.Color.BLACK);
        groupViewTitlesFont.load();
    }

    public void unloadGamingScreen() {
        gamingSceneTextureAtlas.unload();
        gamingBgRegion = null;
    }

    public void unloadMoreAppsScreen() {
        moreAppsTextureAtlas.unload();
        moreAppsBgRegion = null;
    }

    public void unloadSplashScreen() {
        splashTextureAtlas.unload();
        splashRegion = null;
    }

    public void unloadMenuScreen() {
        mainMenuTextureAtlas.unload();
        mainMenuBackgroundRegion = null;
    }
}
