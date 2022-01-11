package com.elvis.copadasembaixadinhas.manager;


import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.scene.BaseScene;
import com.elvis.copadasembaixadinhas.scene.ChooseShoesScene;
import com.elvis.copadasembaixadinhas.scene.GameSceneEmbaixadinhas;
import com.elvis.copadasembaixadinhas.scene.LoadingScene;
import com.elvis.copadasembaixadinhas.scene.MainMenuScene;
import com.elvis.copadasembaixadinhas.scene.MoreAppsScene;
import com.elvis.copadasembaixadinhas.scene.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

/**
 * Created by com.copadasembaixadinhas.elvis on 24/04/14.
 */
public class SceneManager {

    public BaseScene splashScene, mainMenuScene, loadingScene, moreAppsScene, gamingScene;

    public SceneType lastSceneType;
    public String currentShoes = "aus";
    public String currentChallenge = "none";
    public String currentChallengeShoes = "";

    private static final SceneManager INSTANCE = new SceneManager();

    private SceneType currentSceneType;
    public BaseScene currentScene;
    private Engine engine = ResourceManager.getInstance().engine;

    public enum SceneType {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_LOADING,
        SCENE_MORE_APPS,
        SCENE_GAMING,
        SCENE_SHOES
    }

    public static SceneManager getInstance () {
        return INSTANCE;
    }

    public void createMainMenuScene () {
        ResourceManager.getInstance().loadMainMenuResources();
        mainMenuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        SceneManager.getInstance().setScene(mainMenuScene);
        disposeSplashScene();
    }

    public void loadMainMenuScene (final Engine mEngine) {
        unloadCurrentScene();
        setScene(loadingScene);
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadMainMenuResources();
                mainMenuScene = new MainMenuScene();
                setScene(mainMenuScene);
            }
        }));
    }

    public void loadEmbaixadinhasGameScene (final Engine mEngine, final String pTeam) {
        unloadCurrentScene();
        setScene(loadingScene);
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadEmbaixadinhasGameResources(pTeam);
                gamingScene = new GameSceneEmbaixadinhas();
                setScene(gamingScene);
            }
        }));
    }

    public void loadChooseShoesScene (final Engine mEngine) {
        unloadCurrentScene();
        setScene(loadingScene);
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadChooseShoesResources();
                gamingScene = new ChooseShoesScene();
                setScene(gamingScene);
            }
        }));
    }

    public void loadMoreAppsScene(final Engine mEngine) {
        unloadCurrentScene();
        setScene(loadingScene);
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadMoreAppsResources();
                moreAppsScene = new MoreAppsScene();
                setScene(moreAppsScene);
            }
        }));
    }
    public void unloadCurrentScene() {
        if (SceneManager.getInstance().currentSceneType == SceneType.SCENE_MENU) {
            disposeMenuScene();
        } else if (SceneManager.getInstance().currentSceneType == SceneType.SCENE_MORE_APPS) {
            disposeMoreAppsScene();
        } else if (SceneManager.getInstance().currentSceneType == SceneType.SCENE_GAMING) {
            disposeGamingScene();
        }
    }

    private void disposeGamingScene() {
        ResourceManager.getInstance().unloadGamingScreen();
        gamingScene.disposeScene();
        gamingScene = null;
    }

    private void disposeMoreAppsScene() {
        ResourceManager.getInstance().unloadMoreAppsScreen();
        moreAppsScene.disposeScene();
        moreAppsScene = null;
    }

    public void disposeSplashScene() {
        ResourceManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }

    private void disposeMenuScene() {
        ResourceManager.getInstance().unloadMenuScreen();
        mainMenuScene.disposeScene();
        mainMenuScene = null;
    }

    public void createSplashScene (OnCreateSceneCallback pOnCreateSceneCallback) {
        MainActivity.getInstance().hideLoginInfo();

        ResourceManager.getInstance().loadSplashResources();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
        ResourceManager.getInstance().loadAllResources();
    }

    public void setScene (BaseScene baseScene) {
        engine.setScene(baseScene);
        if(baseScene.equals(loadingScene)) {
            MainActivity.getInstance().hideLoginInfo();
            baseScene.randomText();
        }
        currentScene = baseScene;
        currentSceneType = baseScene.getSceneType();
    }

    public void setScene (SceneType sceneType) {
        switch (sceneType) {
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_MENU:
                setScene(mainMenuScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            case SCENE_MORE_APPS:
                setScene(moreAppsScene);
                break;
            case SCENE_GAMING:
                setScene(gamingScene);
                break;
            default:
                break;
        }
    }

    public BaseScene getCurrentScene() {
        return currentScene;
    }

}
