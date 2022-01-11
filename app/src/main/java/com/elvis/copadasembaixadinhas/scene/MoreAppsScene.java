package com.elvis.copadasembaixadinhas.scene;

import android.content.Intent;
import android.net.Uri;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.manager.ResourceManager;
import com.elvis.copadasembaixadinhas.manager.SceneManager;
import com.elvis.copadasembaixadinhas.utils.ButtonUtils;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by com.copadasembaixadinhas.elvis on 18/05/14.
 */
public class MoreAppsScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private Sprite sceneBackground;

    private static final int FGTS_CORRIGIDO = 0;
    private static final int FLIGHT_DRONE = 1;
    private static final int GOGO_JOHNNY = 2;

    @Override
    public void createScene() {
        createBackground();
        createChildsScene();
    }

    private void createBackground() {
        sceneBackground = new Sprite(0, 0, 480, 800, resourceManager.moreAppsBgRegion, vbom);
    }

    private void createChildsScene() {
        final MenuScene menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);
        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(true);
        final SpriteBackground sBg = new SpriteBackground(sceneBackground);
        menuScene.setBackground(sBg);

        final IMenuItem fgts = ButtonUtils.moreAppsButton(FGTS_CORRIGIDO, "FGTS Corrigido", resourceManager.fgtsBtn, vbom);
        menuScene.addMenuItem(fgts);
        final IMenuItem flightDrone = ButtonUtils.moreAppsButton(FLIGHT_DRONE, "Flight Drone", resourceManager.flightDroneBtn, vbom);
        menuScene.addMenuItem(flightDrone);
        final IMenuItem gogoJohnny = ButtonUtils.moreAppsButton(GOGO_JOHNNY, "Go!Go! Johnny", resourceManager.gogoJohnnyBtn, vbom);
        menuScene.addMenuItem(gogoJohnny);
        float row1 = 425f;
        float row2 = 575f;
        float col1 = (MainActivity.CAMERA_WIDTH - resourceManager.fgtsBtn.getWidth() * 2f) / 3f;
        float col2 = resourceManager.fgtsBtn.getWidth() + col1 * 2;

        gogoJohnny.setPosition(col1, row1);
        flightDrone.setPosition(col2, row1);
        fgts.setPosition((MainActivity.CAMERA_WIDTH - fgts.getWidth()) * 0.5f, row2);

        menuScene.setOnMenuItemClickListener(this);
        setChildScene(menuScene);
    }

    @Override
    public void onBackKeyPressed() {
        camera.clearUpdateHandlers();
//        activity.showAdBuddizInsterstitial();
        SceneManager.getInstance().loadMainMenuScene(engine);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_MORE_APPS;
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
        Intent intent;
        switch (pMenuItem.getID()) {
            case FGTS_CORRIGIDO:
                resourceManager.playSound.play();
                Uri uriFGTS = Uri.parse("https://play.google.com/store/apps/details?id=com.com.copadasembaixadinhas.elvis.fgtscorrigido.app");
                intent = new Intent(Intent.ACTION_VIEW, uriFGTS);
                ResourceManager.getInstance().activity.startActivity(intent);
                return true;
            case FLIGHT_DRONE:
                resourceManager.playSound.play();
                Uri uriFlightDrone = Uri.parse("https://play.google.com/store/apps/details?id=com.com.copadasembaixadinhas.elvis.flightdrone");
                intent = new Intent(Intent.ACTION_VIEW, uriFlightDrone);
                ResourceManager.getInstance().activity.startActivity(intent);
                return true;
            case GOGO_JOHNNY:
                resourceManager.playSound.play();
                Uri uriGoGoJohnny = Uri.parse("https://play.google.com/store/apps/details?id=com.com.copadasembaixadinhas.elvis.gogojohnny");
                intent = new Intent(Intent.ACTION_VIEW, uriGoGoJohnny);
                ResourceManager.getInstance().activity.startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
