package com.elvis.copadasembaixadinhas.scene;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.manager.SceneManager;
import com.elvis.copadasembaixadinhas.utils.ButtonUtils;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by com.copadasembaixadinhas.elvis on 25/04/14.
 */
public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {

    private Sprite mainMenuBackground;

    private static final int GAMES_BUTTON = 0;
    private static final int MORE_BUTTON = 1;

    @Override
    public void createScene() {
        createBackground();
        createChildsScene();
        MainActivity.getInstance().showLoginInfo();
    }

    private void createBackground() {
        mainMenuBackground = new Sprite(0, 0, 480, 800, resourceManager.mainMenuBackgroundRegion, vbom);
    }

    private void createChildsScene() {

        final MenuScene menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);
        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(true);
        SpriteBackground sBg = new SpriteBackground(mainMenuBackground);
        menuScene.setBackground(sBg);
        final IMenuItem gamesBtn = ButtonUtils.gameButton(GAMES_BUTTON, resourceManager.gameBgBtn, vbom);
        menuScene.addMenuItem(gamesBtn);
        float btnPosX = (MainActivity.CAMERA_WIDTH - gamesBtn.getWidth()) * 0.5f;
        gamesBtn.setPosition(btnPosX, 200f);
        final IMenuItem moreBtn = ButtonUtils.titleButton(MORE_BUTTON, "+ A P P S", resourceManager.button, vbom, 1.1f, 1f);
        menuScene.addMenuItem(moreBtn);
        moreBtn.setPosition((MainActivity.CAMERA_WIDTH - moreBtn.getWidth()) * 0.5f, MainActivity.CAMERA_HEIGHT - moreBtn.getHeight() - 200f);//btnPosX, teamsBtn.getY() + gamesBtn.getHeight() + 10f);//

        menuScene.setOnMenuItemClickListener(this);
        setChildScene(menuScene);
    }

    @Override
    public void onBackKeyPressed() {
        MainActivity.getInstance().showInterstialAd();
//        System.exit(0);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void randomText() {

    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()) {
            case MORE_BUTTON:
                resourceManager.playSound.play();
                SceneManager.getInstance().loadMoreAppsScene(engine);
                return true;
            case GAMES_BUTTON:
                resourceManager.playSound.play();
                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "aus");
                return true;
            default:
                return false;
        }
    }
}
