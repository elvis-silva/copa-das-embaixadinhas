package com.elvis.copadasembaixadinhas.scene;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.manager.SceneManager.SceneType;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.util.color.Color;

/**
 * Created by com.copadasembaixadinhas.elvis on 25/04/14.
 */
public class SplashScene extends BaseScene {

    private Sprite splash;

    @Override
    public void createScene() {
        MainActivity.getInstance().hideLoginInfo();
        this.getBackground().setColor(Color.WHITE);

        splash = new Sprite(0, 0, resourceManager.splashRegion, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        splash.setPosition(MainActivity.CAMERA_WIDTH / 2 - splash.getWidth() / 2, MainActivity.CAMERA_HEIGHT / 2 - splash.getHeight() / 2);
        attachChild(splash);
    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene() {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
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
}
