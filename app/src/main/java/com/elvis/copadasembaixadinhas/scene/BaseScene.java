package com.elvis.copadasembaixadinhas.scene;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.camera.FollowCamera;
import com.elvis.copadasembaixadinhas.manager.ResourceManager;
import com.elvis.copadasembaixadinhas.manager.SceneManager.SceneType;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by com.copadasembaixadinhas.elvis on 24/04/14.
 */
public abstract class BaseScene extends Scene {
    // Variables
    protected Engine engine;
    protected MainActivity activity;
    protected FollowCamera camera;
    protected VertexBufferObjectManager vbom;
    protected ResourceManager resourceManager;

    // Constructors
    public BaseScene() {
        this.resourceManager = ResourceManager.getInstance();
        this.activity = resourceManager.activity;
        this.camera = resourceManager.camera;
        this.vbom = resourceManager.vbom;
        this.engine = resourceManager.engine;
        createScene();
    }

    // Abstraction
    public abstract void createScene();
    public abstract void onBackKeyPressed();
    public abstract SceneType getSceneType();
    public abstract void disposeScene();
    public abstract void resume();
    public abstract void pause();
    public abstract void randomText();
}

