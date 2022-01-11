package com.elvis.copadasembaixadinhas.scene;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.manager.SceneManager;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

/**
 * Created by com.copadasembaixadinhas.elvis on 25/04/14.
 */
public class LoadingScene extends BaseScene {

    Text text;

    @Override
    public void createScene() {
        MainActivity.getInstance().hideLoginInfo();

        final Sprite sceneBackground = new Sprite(0, 0, 480, 800, resourceManager.loadingBgRegion, vbom);
        setBackgroundEnabled(true);
        SpriteBackground sBg = new SpriteBackground(sceneBackground);
        setBackground(sBg);
        text = new Text(MainActivity.CAMERA_WIDTH / 4 - 50f, MainActivity.CAMERA_HEIGHT / 2, resourceManager.font, "Aguarde...", vbom);
        attachChild(text);
    }

    @Override
    public void onBackKeyPressed() {
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_LOADING;
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

/*        clearChildScene();

        final Random randomVal = new Random();

        final int val = randomVal.nextInt(3);
        String loadingText;
        switch (val) {
            case 0:
                loadingText = "Aguarde...";
                break;
            case 1:
                loadingText = "Tô indo...";
                break;
            case 2:
                loadingText = "Só um pokinho...";
                break;
            case 3:
                loadingText = "Vai Brasiiill...";
                break;
            default:
                loadingText = "";
                break;
        }
        text = new Text(MainActivity.CAMERA_WIDTH / 4 - 50f, MainActivity.CAMERA_HEIGHT / 2, resourceManager.font, loadingText, vbom);

        attachChild(text);
*/    }
}
