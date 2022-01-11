package com.elvis.copadasembaixadinhas.utils;

import com.elvis.copadasembaixadinhas.manager.ResourceManager;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.IFont;

/**
 * Created by com.copadasembaixadinhas.elvis on 22/05/14.
 */
public class TextUtil {

    public static Text newText (IFont pFont, CharSequence pText) {
        final Text txt =  new Text(0, 0, pFont, pText, ResourceManager.getInstance().vbom);
        txt.setScale(0.6f);
        return txt;
    }
}
