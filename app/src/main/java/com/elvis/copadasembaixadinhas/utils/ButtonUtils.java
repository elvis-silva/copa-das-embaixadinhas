package com.elvis.copadasembaixadinhas.utils;

import com.elvis.copadasembaixadinhas.manager.ResourceManager;

import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by com.copadasembaixadinhas.elvis on 26/04/14.
 */
public class ButtonUtils {
    private static  ResourceManager resourceManager = ResourceManager.getInstance();

    public static IMenuItem titleButton(int pID, CharSequence pTitle, ITextureRegion pTextureRegion, VertexBufferObjectManager vbom, float pSelectedScale, float pUnselectedScale) {
        IMenuItem btn = new ScaleMenuItemDecorator(new SpriteMenuItem(pID, pTextureRegion, vbom), pSelectedScale, pUnselectedScale);
        Text text = new Text(0, 0, resourceManager.font, pTitle, vbom);
        btn.attachChild(text);
        float textPosX = (btn.getWidth() - text.getWidth()) * 0.5f;
        float textPosY = (btn.getHeight() - text.getHeight()) * 0.5f;
        text.setPosition(textPosX, textPosY);
        return btn;
    }

    public static IMenuItem shortButton(int pID, CharSequence pTitle, ITextureRegion pTextureRegion, VertexBufferObjectManager vbom, float pSelectedScale, float pUnselectedScale) {
        IMenuItem btn = new ScaleMenuItemDecorator(new SpriteMenuItem(pID, pTextureRegion, vbom), pSelectedScale, pUnselectedScale);
        Text text = new Text(0, 0, resourceManager.font, pTitle, vbom);
        btn.attachChild(text);
        float textPosX = (btn.getWidth() - text.getWidth()) * 0.5f;
        float textPosY = (btn.getHeight() - text.getHeight()) * 0.5f;
        text.setPosition(textPosX, textPosY);
        text.setScale(0.85f);
        return btn;
    }

    public static IMenuItem teamButton (int pID, CharSequence pTeamName, ITextureRegion pIconTeam, VertexBufferObjectManager vbom) {
        IMenuItem btn = new ScaleMenuItemDecorator(new SpriteMenuItem(pID, pIconTeam, vbom), 1.1f, 1f);
        Text teamName = new Text(0, 0, resourceManager.groupViewTitlesFont, pTeamName, vbom);
        btn.attachChild(teamName);
        teamName.setScale(0.5f);
        teamName.setPosition((pIconTeam.getWidth() - teamName.getWidth()) * 0.5f, pIconTeam.getHeight() - 20f);
        return btn;
    }

    public static IMenuItem moreAppsButton (int pID, CharSequence pAppName, ITextureRegion pAppIcon, VertexBufferObjectManager vbom) {
        IMenuItem btn = new ScaleMenuItemDecorator(new SpriteMenuItem(pID, pAppIcon, vbom), 1.2f, 1f);
        Text appName = new Text(0, 0, resourceManager.groupViewTitlesFont, pAppName, vbom);
        btn.attachChild(appName);
        appName.setScale(0.5f);
        appName.setPosition((pAppIcon.getWidth() - appName.getWidth()) * 0.5f, pAppIcon.getHeight());
        return btn;
    }

    public static IMenuItem gameButton (int pID, ITextureRegion pGameIcon, VertexBufferObjectManager vbom) {
        IMenuItem btn = new ScaleMenuItemDecorator(new SpriteMenuItem(pID, pGameIcon, vbom), 1.2f, 1f);
        return btn;
    }
}
