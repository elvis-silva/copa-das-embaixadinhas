package com.elvis.copadasembaixadinhas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.elvis.copadasembaixadinhas.camera.FollowCamera;
import com.elvis.copadasembaixadinhas.data.DataHandler;
import com.elvis.copadasembaixadinhas.manager.ResourceManager;
import com.elvis.copadasembaixadinhas.manager.SceneManager;
import com.elvis.copadasembaixadinhas.ui.DialogUtils;
import com.elvis.copadasembaixadinhas.utils.GUI;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.winsontan520.wversionmanager.library.WVersionManager;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.activity.BaseGameActivity;


public class MainActivity extends BaseGameActivity {

    private FollowCamera camera;
    public static final int CAMERA_WIDTH = 480;
    public static final int CAMERA_HEIGHT = 800;
    private int adBuddizShowed = 0;

    private int rateCont = 0;

//    public AdView adView;
    public InterstitialAd interstitialAd;
    public static int interstitialAdIndex = 0;
    public FrameLayout frameLayout;
    private DataHandler dataHandler, shoesDataHandler;
    private int highScore = 0;
    private int shoesScore = 0;
    public int score = 0;
    public boolean shared = false;
//    public SocialAuthAdapter adapter;
    private static MainActivity instance;

    SharedPreferences prefs;
    private CallbackManager callbackManager;
    private ProfilePictureView picture;
    private LoginButton loginButton;
    private int pictureVisible;
    private boolean loginInfoVisible;

    @Override
    public EngineOptions onCreateEngineOptions() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        camera = new FollowCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED,
                new FillResolutionPolicy(), camera);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        engineOptions.getAudioOptions().setNeedsSound(true);
        engineOptions.getRenderOptions().setMultiSampling(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        instance = this;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) {

        WVersionManager versionManager = new WVersionManager(this);
        versionManager.setTitle("Nova versão disponível");
        versionManager.setVersionContentUrl("https://copy.com/cLEZ9yP1ovb1"); // your update content url, see the response format below
        versionManager.setUpdateNowLabel("Atualizar");
        versionManager.setRemindMeLaterLabel("Depois");
        versionManager.setIgnoreThisVersionLabel("Ignorar");
        versionManager.setUpdateUrl("https://play.google.com/store/apps/details?id=com.elvis.copadasembaixadinhas"); // this is the link will execute when update now clicked. default will go to google play based on your package name.
        versionManager.setReminderTimer(10); // this mean checkVersion() will not take effect within 10 minutes
        versionManager.checkVersion();

        // enable debug logs (if applicable)
//        if (DEBUG_BUILD) {
//            mGameHelper.enableDebugLog(true, "GameHelper");
//        }


        return new LimitedFPSEngine(pEngineOptions, 60);
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        ResourceManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                SceneManager.getInstance().createMainMenuScene();
            }
        }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
            rateCont++;
//            if (rateCont == 15) {
//                WVersionManager versionManager = new WVersionManager(this);
//                versionManager.setTitle("Avalie por favor"); // optional
//                versionManager.setMessage("Ajude-nos melhorar nossos apps!"); // optional
//                versionManager.setAskForRatePositiveLabel("OK"); // optional
//                versionManager.setAskForRateNegativeLabel("Depois"); // optional
//                versionManager.askForRate();
//            }

            interstitialAdIndex++;
//            if (interstitialAdIndex == 18) {
//                showInterstialAd();
//            }
        }


        return false;
    }

    @Override
    protected void onSetContentView() {
        super.onSetContentView();

        loginButton = new LoginButton(MainActivity.this);

        LinearLayout loginContainer = new LinearLayout(MainActivity.this);
        loginContainer.setPadding(10, 40, 0, 0);
        loginContainer.addView(loginButton);
        loginButton.setVisibility(LoginButton.INVISIBLE);

        frameLayout = new FrameLayout(MainActivity.this);

        final FrameLayout.LayoutParams frameLayoutLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);

        final RelativeLayout.LayoutParams surfaceViewLayoutParams =
                new RelativeLayout.LayoutParams(BaseGameActivity.createSurfaceViewLayoutParams());

//        adView = new AdView(MainActivity.this);
//        adView.refreshDrawableState();

//        int screenSize = getResources().getConfiguration().screenLayout &
//                Configuration.SCREENLAYOUT_SIZE_MASK;

//        switch(screenSize) {
//            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
//                adView.setAdSize(AdSize.FULL_BANNER);
//                break;
//            case Configuration.SCREENLAYOUT_SIZE_LARGE:
//                adView.setAdSize(AdSize.FULL_BANNER);
//                break;
//            default:
//                adView.setAdSize(AdSize.BANNER);
//        }

//        adView.setAdUnitId("ca-app-pub-4768510961285493/7590177169");

//        adView.setVisibility(View.VISIBLE);
//        adView.setEnabled(true);

        AdRequest adRequest = new AdRequest
                .Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("B5C01953FD602089271030B3E22BCC23")
                .build();

//        adView.loadAd(adRequest);

        loadInterstitial(adRequest);

        this.mRenderSurfaceView = new RenderSurfaceView(MainActivity.this);
        this.mRenderSurfaceView.setRenderer(this.mEngine, MainActivity.this);

        final FrameLayout.LayoutParams adViewLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);

        frameLayout.addView(this.mRenderSurfaceView, surfaceViewLayoutParams);
        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        frameLayout.addView(linearLayout);

        picture = new ProfilePictureView(MainActivity.this);
        picture.setPadding(10, 10, 10, 0);
        linearLayout.addView(picture);
        picture.setVisibility(ProfilePictureView.INVISIBLE);

        pictureVisible = loginButton.getText().equals(getString(R.string.com_facebook_loginview_log_out_button)) ?
                ProfilePictureView.VISIBLE : ProfilePictureView.INVISIBLE;

        if(pictureVisible == ProfilePictureView.VISIBLE) {
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/{user-id}/picture",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            picture.setProfileId(response.getRequest().getAccessToken().getUserId());
                            picture.setVisibility(pictureVisible);
                            loginButton.setVisibility(LoginButton.VISIBLE);
                        }
                    }
            ).executeAsync();
        }
//        GUI.toastShort(String.valueOf(picture.getProfileId()));

//        picture.offsetLeftAndRight(10);
        linearLayout.addView(loginContainer);

        loginButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

//        linearLayout.addView(loginButton);

        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/{user-id}/picture",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                picture.setProfileId(response.getRequest().getAccessToken().getUserId());
                                picture.setVisibility(ProfilePictureView.VISIBLE);
                                pictureVisible = ProfilePictureView.VISIBLE;
                            }
                        }
                ).executeAsync();
                GUI.toastLong("Conectado ao facebook!");
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
                GUI.alert(MainActivity.this, "Houve um erro! Tente mais tarde.");
            }
        });

//        frameLayout.addView(adView);

//        adView.setAdListener(new AdListener() {
//            public void onAdLoaded(){
//                adView.setLayoutParams(adViewLayoutParams);
//            }
//        });

        this.setContentView(frameLayout, frameLayoutLayoutParams);
        loginButton.setVisibility(LoginButton.VISIBLE);
    }

    public void hideLoginInfo() {
        loginInfoVisible = false;
        updateGUI();
    }

    public void showLoginInfo() {
        loginInfoVisible = true;
        updateGUI();
    }

    final Runnable myRunnable = new Runnable() {
        public void run() {
            if(loginInfoVisible) {
                picture.setVisibility(pictureVisible);
                loginButton.setVisibility(LoginButton.VISIBLE);
            } else {
                picture.setVisibility(ProfilePictureView.INVISIBLE);
                loginButton.setVisibility(LoginButton.INVISIBLE);
            }
        }
    };

    final Handler myHandler = new Handler();

    private void updateGUI() {
        myHandler.post(myRunnable);
    }

    public void showInterstialAd () {
        if(interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    instance.finish();
                }
            });
        } else {
            instance.finish();
        }
//        interstitialAdIndex = 0;
    }

    public static RelativeLayout.LayoutParams getSurfaceViewLayoutParams () {
        return new RelativeLayout.LayoutParams(BaseGameActivity.createSurfaceViewLayoutParams());
    }

    private void loadInterstitial (AdRequest pAdRequest) {
        interstitialAd = new InterstitialAd(MainActivity.this);
        interstitialAd.setAdUnitId("ca-app-pub-4768510961285493/7262227962");
        interstitialAd.loadAd(pAdRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public synchronized void onResumeGame() {
        super.onResumeGame();
        if (SceneManager.getInstance().getCurrentScene() == SceneManager.getInstance().currentScene) {
            SceneManager.getInstance().currentScene.resume();
        }
    }

    @Override
    public synchronized void onPauseGame() {
        super.onPauseGame();
        if (SceneManager.getInstance().getCurrentScene() == SceneManager.getInstance().currentScene) {
            SceneManager.getInstance().currentScene.pause();
        }
    }

    @Override
    protected synchronized void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
//        if (adView != dialog_show_score) {
//            adView.resume();
//        }
    }

    @Override
    protected void onPause() {
//        if (adView != dialog_show_score) {
//            adView.pause();
//        }
        super.onPause();

        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onDestroy () {
//        if (adView != dialog_show_score) {
//            adView.destroy();
//        }

        System.exit(0);
        super.onDestroy();
//        AdBuddiz.onDestroy();
    }

    public void setHighScore(int score) {
        if (score > getHighScore()) {
            updateHighScore(score);
        }
    }

    public int getHighScore() {
        dataHandler = new DataHandler(this);
        dataHandler.open();
        Cursor cursor = dataHandler.returnData();
        if(cursor.moveToFirst()) {
            if (cursor.moveToLast()) {
                highScore = cursor.getInt(0);
            } do {
                highScore = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        dataHandler.close();
        return highScore;
    }

    public void updateHighScore (int newHighScore) {
        dataHandler = new DataHandler(this);
        dataHandler.open();
        dataHandler.deleteData();
        long highScoreLong = dataHandler.insertData(newHighScore);
        dataHandler.close();
    }

    public void setShoesScore(int score) {
        if (score > getShoesScore()) {
            updateShoesScore(score);
        }
    }

    public int getShoesScore() {
        shoesDataHandler = new DataHandler(this);
        shoesDataHandler.open();
        Cursor cursor = shoesDataHandler.returnShoesData();
        if(cursor.moveToFirst()) {
            if (cursor.moveToLast()) {
                shoesScore = cursor.getInt(0);
            } do {
                shoesScore = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return shoesScore;
    }

    public void updateShoesScore (int newShoesScore) {
        shoesDataHandler = new DataHandler(this);
        shoesDataHandler.open();
        shoesDataHandler.deleteShoesData();
        long shoesScoreLong = shoesDataHandler.insertShoesData(newShoesScore);
        shoesDataHandler.close();
    }

    public int getBonus (final String pCurrentShoes) {
        if (pCurrentShoes.equals("kor")) {
            return 5;
        } else if (pCurrentShoes.equals("cam")) {
            return 10;
        } else if (pCurrentShoes.equals("jap")) {
            return 15;
        } else if (pCurrentShoes.equals("nig")) {
            return 20;
        } else if (pCurrentShoes.equals("ira")) {
            return 30;
        } else if (pCurrentShoes.equals("gha")) {
            return 40;
        } else if (pCurrentShoes.equals("hon")) {
            return 50;
        } else if (pCurrentShoes.equals("cos")) {
            return 70;
        } else if (pCurrentShoes.equals("ecu")) {
            return 90;
        } else if (pCurrentShoes.equals("cdm")) {
            return 110;
        } else if (pCurrentShoes.equals("alg")) {
            return 130;
        } else if (pCurrentShoes.equals("bos")) {
            return 150;
        } else if (pCurrentShoes.equals("mex")) {
            return 180;
        } else if (pCurrentShoes.equals("rus")) {
            return 210;
        } else if (pCurrentShoes.equals("cro")) {
            return 240;
        } else if (pCurrentShoes.equals("fra")) {
            return 270;
        } else if (pCurrentShoes.equals("hol")) {
            return 300;
        } else if (pCurrentShoes.equals("chi")) {
            return 340;
        } else if (pCurrentShoes.equals("usa")) {
            return 380;
        } else if (pCurrentShoes.equals("gre")) {
            return 420;
        } else if (pCurrentShoes.equals("bel")) {
            return 460;
        } else if (pCurrentShoes.equals("eng")) {
            return 500;
        } else if (pCurrentShoes.equals("ita")) {
            return 550;
        } else if (pCurrentShoes.equals("col")) {
            return 600;
        } else if (pCurrentShoes.equals("uru")) {
            return 650;
        } else if (pCurrentShoes.equals("sui")) {
            return 700;
        } else if (pCurrentShoes.equals("arg")) {
            return 750;
        } else if (pCurrentShoes.equals("por")) {
            return 800;
        } else if (pCurrentShoes.equals("ger")) {
            return 850;
        } else if (pCurrentShoes.equals("spa")) {
            return 900;
        } else if (pCurrentShoes.equals("bra")) {
            return 1000;
        }
        return 0;
    }

    public boolean hasChallenge () {
        final String currentChallenge = SceneManager.getInstance().currentChallenge;
        return  currentChallenge.equals("kor") && getCurrentTotalScore() >= 60   ||
                currentChallenge.equals("cam") && getCurrentTotalScore() >= 80   ||
                currentChallenge.equals("jap") && getCurrentTotalScore() >= 100  ||
                currentChallenge.equals("nig") && getCurrentTotalScore() >= 130  ||
                currentChallenge.equals("ira") && getCurrentTotalScore() >= 160  ||
                currentChallenge.equals("gha") && getCurrentTotalScore() >= 190  ||
                currentChallenge.equals("hon") && getCurrentTotalScore() >= 230  ||
                currentChallenge.equals("cos") && getCurrentTotalScore() >= 270  ||
                currentChallenge.equals("ecu") && getCurrentTotalScore() >= 310  ||
                currentChallenge.equals("cdm") && getCurrentTotalScore() >= 360  ||
                currentChallenge.equals("alg") && getCurrentTotalScore() >= 410  ||
                currentChallenge.equals("bos") && getCurrentTotalScore() >= 460  ||
                currentChallenge.equals("mex") && getCurrentTotalScore() >= 510  ||
                currentChallenge.equals("rus") && getCurrentTotalScore() >= 560  ||
                currentChallenge.equals("cro") && getCurrentTotalScore() >= 620  ||
                currentChallenge.equals("fra") && getCurrentTotalScore() >= 680  ||
                currentChallenge.equals("hol") && getCurrentTotalScore() >= 740  ||
                currentChallenge.equals("chi") && getCurrentTotalScore() >= 800  ||
                currentChallenge.equals("usa") && getCurrentTotalScore() >= 860  ||
                currentChallenge.equals("gre") && getCurrentTotalScore() >= 920  ||
                currentChallenge.equals("bel") && getCurrentTotalScore() >= 980  ||
                currentChallenge.equals("eng") && getCurrentTotalScore() >= 1050 ||
                currentChallenge.equals("ita") && getCurrentTotalScore() >= 1120 ||
                currentChallenge.equals("col") && getCurrentTotalScore() >= 1190 ||
                currentChallenge.equals("uru") && getCurrentTotalScore() >= 1260 ||
                currentChallenge.equals("sui") && getCurrentTotalScore() >= 1330 ||
                currentChallenge.equals("arg") && getCurrentTotalScore() >= 1400 ||
                currentChallenge.equals("por") && getCurrentTotalScore() >= 1470 ||
                currentChallenge.equals("ger") && getCurrentTotalScore() >= 1540 ||
                currentChallenge.equals("spa") && getCurrentTotalScore() >= 1620 ||
                currentChallenge.equals("bra") && getCurrentTotalScore() >= 1800;
    }

    public boolean hasCurrentChallenge () {
        final String currentChallenge = SceneManager.getInstance().currentChallenge;
        return  currentChallenge.equals("kor") ||
                currentChallenge.equals("cam") ||
                currentChallenge.equals("jap") ||
                currentChallenge.equals("nig") ||
                currentChallenge.equals("ira") ||
                currentChallenge.equals("gha") ||
                currentChallenge.equals("hon") ||
                currentChallenge.equals("cos") ||
                currentChallenge.equals("ecu") ||
                currentChallenge.equals("cdm") ||
                currentChallenge.equals("alg") ||
                currentChallenge.equals("bos") ||
                currentChallenge.equals("mex") ||
                currentChallenge.equals("rus") ||
                currentChallenge.equals("cro") ||
                currentChallenge.equals("fra") ||
                currentChallenge.equals("hol") ||
                currentChallenge.equals("chi") ||
                currentChallenge.equals("usa") ||
                currentChallenge.equals("gre") ||
                currentChallenge.equals("bel") ||
                currentChallenge.equals("eng") ||
                currentChallenge.equals("ita") ||
                currentChallenge.equals("col") ||
                currentChallenge.equals("uru") ||
                currentChallenge.equals("sui") ||
                currentChallenge.equals("arg") ||
                currentChallenge.equals("por") ||
                currentChallenge.equals("ger") ||
                currentChallenge.equals("spa") ||
                currentChallenge.equals("bra") ;
    }

    private int getCurrentTotalScore () {
        final int bonusValue = ResourceManager.getInstance().activity.getBonus(SceneManager.getInstance().currentShoes);
        final int currentScoreValue = ResourceManager.getInstance().activity.score;
        return currentScoreValue + bonusValue;
    }

    private String getCurrentChallenge () {
        String currentChallengeReturn = "none";
        final String currentChallenge = SceneManager.getInstance().currentChallenge;
        if (currentChallenge.equals("kor")) {
            currentChallengeReturn = "Coréia do Sul";
            updateShoesScore(1);
        } else if (currentChallenge.equals("cam")) {
            currentChallengeReturn = "Camarões";
            updateShoesScore(2);
        } else if (currentChallenge.equals("jap")) {
            currentChallengeReturn = "Japão";
            updateShoesScore(3);
        } else if (currentChallenge.equals("nig")) {
            currentChallengeReturn = "Nigéria";
            updateShoesScore(4);
        } else if (currentChallenge.equals("ira")) {
            currentChallengeReturn = "Irã";
            updateShoesScore(5);
        } else if (currentChallenge.equals("gha")) {
            currentChallengeReturn = "Gana";
            updateShoesScore(6);
        } else if (currentChallenge.equals("hon")) {
            currentChallengeReturn = "Honduras";
            updateShoesScore(7);
        } else if (currentChallenge.equals("cos")) {
            currentChallengeReturn = "Costa Rica";
            updateShoesScore(8);
        } else if (currentChallenge.equals("ecu")) {
            currentChallengeReturn = "Equador";
            updateShoesScore(9);
        } else if (currentChallenge.equals("cdm")) {
            currentChallengeReturn = "Costa do Marfim";
            updateShoesScore(10);
        } else if (currentChallenge.equals("alg")) {
            currentChallengeReturn = "Argélia";
            updateShoesScore(11);
        } else if (currentChallenge.equals("bos")) {
            currentChallengeReturn = "Bósnia e Herzegovina";
            updateShoesScore(12);
        } else if (currentChallenge.equals("mex")) {
            currentChallengeReturn = "México";
            updateShoesScore(13);
        } else if (currentChallenge.equals("rus")) {
            currentChallengeReturn = "Rússia";
            updateShoesScore(14);
        } else if (currentChallenge.equals("cro")) {
            currentChallengeReturn = "Croácia";
            updateShoesScore(15);
        } else if (currentChallenge.equals("fra")) {
            currentChallengeReturn = "França";
            updateShoesScore(16);
        } else if (currentChallenge.equals("hol")) {
            currentChallengeReturn = "Holanda";
            updateShoesScore(17);
        } else if (currentChallenge.equals("chi")) {
            currentChallengeReturn = "Chile";
            updateShoesScore(18);
        } else if (currentChallenge.equals("usa")) {
            currentChallengeReturn = "Estados Unidos";
            updateShoesScore(19);
        } else if (currentChallenge.equals("gre")) {
            currentChallengeReturn = "Grécia";
            updateShoesScore(20);
        } else if (currentChallenge.equals("bel")) {
            currentChallengeReturn = "Bélgica";
            updateShoesScore(21);
        } else if (currentChallenge.equals("eng")) {
            currentChallengeReturn = "Inglaterra";
            updateShoesScore(22);
        } else if (currentChallenge.equals("ita")) {
            currentChallengeReturn = "Itália";
            updateShoesScore(23);
        } else if (currentChallenge.equals("col")) {
            currentChallengeReturn = "Colômbia";
            updateShoesScore(24);
        } else if (currentChallenge.equals("uru")) {
            currentChallengeReturn = "Uruguai";
            updateShoesScore(25);
        } else if (currentChallenge.equals("sui")) {
            currentChallengeReturn = "Suíça";
            updateShoesScore(26);
        } else if (currentChallenge.equals("arg")) {
            currentChallengeReturn = "Argentina";
            updateShoesScore(27);
        } else if (currentChallenge.equals("por")) {
            currentChallengeReturn = "Portugal";
            updateShoesScore(28);
        } else if (currentChallenge.equals("ger")) {
            currentChallengeReturn = "Alemanha";
            updateShoesScore(29);
        } else if (currentChallenge.equals("spa")) {
            currentChallengeReturn = "Espanha";
            updateShoesScore(30);
        } else if (currentChallenge.equals("bra")) {
            currentChallengeReturn = "Brasil";
            updateShoesScore(31);
        }
        return currentChallengeReturn;
    }

    public void showChallengeWinner () {
        final String currentChallenge = getCurrentChallenge();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                final Dialog dialog = new Dialog(MainActivity.this);
//                dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_score);
                LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                btnLayoutParams.weight = 1f;

                FrameLayout.LayoutParams tableRowtLayoutParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                final TableLayout tableLayout = new TableLayout(MainActivity.this);
                tableLayout.setLayoutParams(new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

                TableRow tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView lblScore = new TextView(MainActivity.this);
                tableRow.addView(lblScore);
                lblScore.setTextColor(Color.rgb(112, 128, 144));
                lblScore.setTextSize(20f);
                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                lblScore.setTypeface(font);

                final int currentScoreValue = ResourceManager.getInstance().activity.score;
                lblScore.setText("     Chuteira");

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView challengeReward = new TextView(MainActivity.this);
                tableRow.addView(challengeReward);
                challengeReward.setTextColor(Color.rgb(0, 128, 0));
                challengeReward.setTextSize(25f);
//                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                challengeReward.setTypeface(font);

                challengeReward.setText(currentChallenge);

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView cont = new TextView(MainActivity.this);
                tableRow.addView(cont);
                cont.setTextColor(Color.rgb(112,128,144));
                cont.setTextSize(20f);
//                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                cont.setTypeface(font);

                cont.setText("  desbloqueada");
/*
                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView bonusScore = new TextView(MainActivity.this);
                tableRow.addView(bonusScore);
                bonusScore.setTextColor(Color.rgb(20, 20, 200));
                bonusScore.setTextSize(30f);
//                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                bonusScore.setTypeface(font);

                final int bonusValue = ResourceManager.getInstance().activity.getBonus(SceneManager.getInstance().currentShoes);
                bonusScore.setText("Bônus:  " + String.valueOf(bonusValue));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView totalScore = new TextView(MainActivity.this);
                tableRow.addView(totalScore);
                totalScore.setTextColor(Color.rgb(20, 20, 200));
                totalScore.setTextSize(30f);
//                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                totalScore.setTypeface(font);

                final int totalValue = currentScoreValue + bonusValue;
                totalScore.setText("Total:  " + String.valueOf(totalValue));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView lblHighScore = new TextView(MainActivity.this);
                tableRow.addView(lblHighScore);
                lblHighScore.setTextColor(Color.rgb(200, 20, 20));
                lblHighScore.setTextSize(30f);
                Typeface hfont = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                lblHighScore.setTypeface(hfont);

                final int recordValue = ResourceManager.getInstance().activity.getHighScore();
                lblHighScore.setText("Recorde:  " + String.valueOf(recordValue));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                final TextView tv = new TextView(MainActivity.this);
                tv.setTextSize(20f);
                tv.setText("");
                tableRow.addView(tv);
*/
                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);

                //////////////////
                // SHARE REGION //
                //////////////////
//                final Button shareBtn = new Button(MainActivity.this);
//                shareBtn.setTextSize(20f);
//                shareBtn.setTextColor(Color.rgb(20, 20, 200));
//                shareBtn.setText("Compartilhar");
//
//                adapter = new SocialAuthAdapter(new ResponseListener());
//                adapter.addProvider(SocialAuthAdapter.Provider.FACEBOOK, R.drawable.facebook);
//                adapter.enable(shareBtn);

                final Button exitBtn = new Button(MainActivity.this);
                exitBtn.setTextSize(20f);
                exitBtn.setTextColor(Color.rgb(20, 20, 200));
                exitBtn.setText("Continuar");

                tableRow.addView(exitBtn);
//                tableRow.addView(shareBtn);

                exitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEngine.start();
                        dialog.dismiss();
//                        ResourceManager.getInstance().activity.showScore();
                    }
                });

                dialog.setContentView(tableLayout);

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mEngine.start();
//                        showInterstialAd();
                        dialog.dismiss();
                        ResourceManager.getInstance().activity.showScore();
                    }
                });

                mEngine.stop();
                dialog.show();

                SceneManager.getInstance().currentChallenge = "none";
                SceneManager.getInstance().currentChallengeShoes = "";
            }
        });
    }

    public void showScore(){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

//                ShowScoreDialog showScoreDialog = new ShowScoreDialog();
//                showScoreDialog.getDialog();

                final int currentScoreValue = ResourceManager.getInstance().activity.score;
                final int bonusValue = ResourceManager.getInstance().activity.getBonus(
                        SceneManager.getInstance().currentShoes);
                final int totalValue = currentScoreValue + bonusValue;
                final int currentHighScore = ResourceManager.getInstance().activity.getHighScore();
                final int recordValue = currentHighScore >= totalValue ? currentHighScore : totalValue;

                Dialog dialog = DialogUtils.createDialog(currentScoreValue, bonusValue, totalValue,
                        recordValue, mEngine);


//                mEngine.stop();


//                if (interstitialAdIndex == 0) loadInterstitial();
//                interstitialAdIndex++;

//                final

//                final Dialog dialog = new Dialog(MainActivity.this);
//                dialog.setContentView(R.layout.dialog_show_score);
//                dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_score);

//                LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//                btnLayoutParams.weight = 1f;
/*
                FrameLayout.LayoutParams tableRowtLayoutParams = new FrameLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                final TableLayout tableLayout = new TableLayout(MainActivity.this);
                tableLayout.setLayoutParams(new FrameLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                TableRow tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView lblScore = new TextView(MainActivity.this);
                tableRow.addView(lblScore);
                lblScore.setTextColor(Color.rgb(224, 224, 224));
                lblScore.setTextSize(30f);
                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                lblScore.setTypeface(font);

                final int currentScoreValue = ResourceManager.getInstance().activity.score;
                lblScore.setText("Pontos:  " + String.valueOf(currentScoreValue));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView bonusScore = new TextView(MainActivity.this);
                tableRow.addView(bonusScore);
                bonusScore.setTextColor(Color.rgb(224, 224, 224));
                bonusScore.setTextSize(30f);
//                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                bonusScore.setTypeface(font);

                final int bonusValue = ResourceManager.getInstance().activity.getBonus(
                        SceneManager.getInstance().currentShoes);
                bonusScore.setText("Bônus:  " + String.valueOf(bonusValue));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView totalScore = new TextView(MainActivity.this);
                tableRow.addView(totalScore);
                totalScore.setTextColor(Color.rgb(224, 224, 224));
                totalScore.setTextSize(30f);
//                Typeface font = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                totalScore.setTypeface(font);

                final int totalValue = currentScoreValue + bonusValue;
                totalScore.setText("Total:  " + String.valueOf(totalValue));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView lblHighScore = new TextView(MainActivity.this);
                tableRow.addView(lblHighScore);
                lblHighScore.setTextColor(Color.rgb(175, 0, 0));
                lblHighScore.setTextSize(30f);
                Typeface hfont = Typeface.createFromAsset(getAssets(), "font/font.ttf");
                lblHighScore.setTypeface(hfont);

                final int currentHighScore = ResourceManager.getInstance().activity.getHighScore();
                final int recordValue = currentHighScore >= totalValue ? currentHighScore : totalValue;
                ResourceManager.getInstance().activity.setHighScore(recordValue);
                lblHighScore.setText("Recorde:  " + String.valueOf(recordValue));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                final TextView tv = new TextView(MainActivity.this);
                tv.setTextSize(20f);
                tv.setText("");
                tableRow.addView(tv);
                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);

                //////////////////
                // SHARE REGION //
                //////////////////
                final ShareButton shareBtn = new ShareButton(MainActivity.this);
//                shareBtn.setLayoutParams(btnLayoutParams);

                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.elvis.copadasembaixadinhas"))
                        .setContentTitle("Copa das Embaixadinhas")
                        .setContentDescription("Quero ver quem quebra meu recorde agora!")
                        .build();

                shareBtn.setShareContent(content);
//                final Button shareBtn = new Button(MainActivity.this);
//                shareBtn.setTextSize(20f);
//                shareBtn.setTextColor(Color.BLACK);
//                shareBtn.setText("Compartilhar");

//                adapter = new SocialAuthAdapter(new ResponseListener());
//                adapter.addProvider(SocialAuthAdapter.Provider.FACEBOOK, R.drawable.facebook);
//                adapter.enable(shareBtn);

                final Button exitBtn = new Button(MainActivity.this);
//                exitBtn.setLayoutParams(btnLayoutParams);
                exitBtn.setTextSize(20f);
                exitBtn.setTextColor(Color.BLACK);
                exitBtn.setText("Outra vez");

                tableRow.addView(exitBtn);
                tableRow.addView(shareBtn);

                exitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEngine.start();
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(tableLayout);
*/
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mEngine.start();
//                        showInterstialAd();
                        dialog.dismiss();
                    }
                });

                mEngine.stop();
                dialog.show();

            }
        });
    }
/*
    public void showChallenge (final String pChallengeNumber, final String pShoesName, final String pPoints) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {*/

//                if (interstitialAdIndex == 0) loadInterstitial();
//                interstitialAdIndex++;
/*
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("DESAFIO " + pChallengeNumber);
//                dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_score);
                LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                btnLayoutParams.weight = 1f;

                FrameLayout.LayoutParams tableRowtLayoutParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                final TableLayout tableLayout = new TableLayout(MainActivity.this);
                tableLayout.setLayoutParams(new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                TableRow tableRow = new TableRow(MainActivity.this);
                tableRow.setLayoutParams(new LinearLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT));
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView lblScore = new TextView(MainActivity.this);
                tableRow.addView(lblScore);
                lblScore.setTextColor(Color.rgb(20, 20, 200));
//                lblScore.setTextSize(10f);
                Typeface font = Typeface.createFromAsset(getAssets(),"font/font.ttf");
                lblScore.setTypeface(font);

                lblScore.setText("\nComplete este desafio para desbloquear a " + pShoesName + ".\n\n" +
                                "Objetivo: fazer " + pPoints + "embaixadinhas."
                );

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                final TextView lblHighScore = new TextView(MainActivity.this);
                //               tableRow.addView(lblHighScore);
                lblHighScore.setTextColor(Color.rgb(200, 20, 20));
                lblHighScore.setTextSize(30f);
                Typeface hfont = Typeface.createFromAsset(getAssets(),"font/font.ttf");
                lblHighScore.setTypeface(hfont);

                lblHighScore.setText("Recorde:  " + String.valueOf(ResourceManager.getInstance().activity.getHighScore()));

                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                final TextView tv = new TextView(MainActivity.this);
                tv.setTextSize(20f);
                tv.setText("");
                tableRow.addView(tv);
                tableRow = new TableRow(MainActivity.this);
                tableLayout.addView(tableRow, tableRowtLayoutParams);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);

                final Button noBtn = new Button(MainActivity.this);
                noBtn.setLayoutParams(btnLayoutParams);

//                noBtn.setTextSize(20f);
//                noBtn.setTextColor(Color.rgb(20, 20, 200));
                noBtn.setText("AGORA NÃO.");

//                adapter = new SocialAuthAdapter(new ResponseListener());
//                adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
//                adapter.enable(shareBtn);

                final Button okBtn = new Button(MainActivity.this);
                okBtn.setLayoutParams(btnLayoutParams);
//                okBtn.setTextSize(20f);
//                okBtn.setTextColor(Color.rgb(20, 20, 200));
                okBtn.setText("ACEITAR?");

                tableRow.addView(okBtn);
                tableRow.addView(noBtn);

                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEngine.start();
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(tableLayout);

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mEngine.start();
                        showInterstialAd();
                        dialog.dismiss();
                    }
                });

                mEngine.stop();
                dialog.show();

            }
        });
    }*/
}