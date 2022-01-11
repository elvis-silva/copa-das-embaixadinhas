package com.elvis.copadasembaixadinhas.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import org.andengine.engine.Engine;

/**
 * Created by elvis on 18/07/15.
 */
public class DialogUtils {

    static public Dialog createDialog(int pPoints, int pBonus, int pTotal, int pRecord, final Engine pEngine) {
        Activity activity = MainActivity.getInstance();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_show_score, null);
        builder.setView(view);

        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "font/vipnagor.ttf");//font.ttf");

        TextView pointsText = (TextView) view.findViewById(R.id.points_text);
        pointsText.setText("Pontos:  " + String.valueOf(pPoints));
        pointsText.setTextColor(Color.BLUE);//rgb(224, 224, 224));
        pointsText.setTypeface(typeface);

        TextView bonusText = (TextView) view.findViewById(R.id.bonus_text);
        bonusText.setText("Bônus:  " + String.valueOf(pBonus));
        bonusText.setTextColor(Color.BLUE);//.rgb(224, 224, 224));
        bonusText.setTypeface(typeface);

        TextView totalText = (TextView) view.findViewById(R.id.total_text);
        totalText.setText("Total:  " + String.valueOf(pTotal));
        totalText.setTextColor(Color.BLUE);//.rgb(224, 224, 224));
        totalText.setTypeface(typeface);

        TextView recordText = (TextView) view.findViewById(R.id.high_score_text);
        recordText.setText("Recorde:  " + String.valueOf(pRecord));
        recordText.setTextColor(Color.RED);//.rgb(224, 224, 224));
        recordText.setTypeface(typeface);

        Button btnBack = (Button) view.findViewById(R.id.btn_back);
        ShareButton btnShare = (ShareButton) view.findViewById(R.id.btn_share);

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.elvis.copadasembaixadinhas"))
                .setContentTitle("Copa das Embaixadinhas")
                .setContentDescription("Fiz " + String.valueOf(pTotal) + " pontos.\nDesafio você a quebrar meu recorde agora!")
                .build();

        btnShare.setShareContent(content);

        final Dialog dialog = builder.create();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pEngine.start();
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
