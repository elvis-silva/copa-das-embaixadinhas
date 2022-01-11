package com.elvis.copadasembaixadinhas.scene;

import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.manager.ResourceManager;
import com.elvis.copadasembaixadinhas.manager.SceneManager;
import com.elvis.copadasembaixadinhas.utils.ButtonUtils;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import java.util.ArrayList;

/**
 * Created by com.copadasembaixadinhas.elvis on 17/06/14.
 */
public class ChooseShoesScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private MenuScene menuScene;
    private Sprite sceneBackground;
    private Text title, challengeTitle, challengeText, shoesName, challengeDetails;
    private boolean popupVisible = false;
    private ArrayList<Sprite> lockList;
    private ArrayList<Text> bonusList;

    private IMenuItem   ausIcon, korIcon, camIcon, japIcon, nigIcon, iraIcon, ghaIcon, honIcon,
                        cosIcon, ecuIcon, cdmIcon, algIcon, bosIcon, mexIcon, rusIcon, croIcon,
                        fraIcon, holIcon, chiIcon, usaIcon, greIcon, belIcon, engIcon, itaIcon,
                        colIcon, uruIcon, swiIcon, argIcon, porIcon, gerIcon, espIcon, braIcon,
                        initChallengeBtn, noBtn, okBtn;

    private static final int AUS_BUTTON =  0;
    private static final int KOR_BUTTON =  1;
    private static final int CAM_BUTTON =  2;
    private static final int JAP_BUTTON =  3;
    private static final int NIG_BUTTON =  4;
    private static final int IRA_BUTTON =  5;
    private static final int GHA_BUTTON =  6;
    private static final int HON_BUTTON =  7;
    private static final int COS_BUTTON =  8;
    private static final int ECU_BUTTON =  9;
    private static final int CDM_BUTTON = 10;
    private static final int ALG_BUTTON = 11;
    private static final int BOS_BUTTON = 12;
    private static final int MEX_BUTTON = 13;
    private static final int RUS_BUTTON = 14;
    private static final int CRO_BUTTON = 15;
    private static final int FRA_BUTTON = 16;
    private static final int HOL_BUTTON = 17;
    private static final int CHI_BUTTON = 18;
    private static final int USA_BUTTON = 19;
    private static final int GRE_BUTTON = 20;
    private static final int BEL_BUTTON = 21;
    private static final int ENG_BUTTON = 22;
    private static final int ITA_BUTTON = 23;
    private static final int COL_BUTTON = 24;
    private static final int URU_BUTTON = 25;
    private static final int SUI_BUTTON = 26;
    private static final int ARG_BUTTON = 27;
    private static final int POR_BUTTON = 28;
    private static final int GER_BUTTON = 29;
    private static final int SPA_BUTTON = 30;
    private static final int BRA_BUTTON = 31;
    private static final int INIT_CHALLENGE_BTN = 32;
    private static final int NO_BTN = 33;
    private static final int OK_BTN = 34;

    private static final String AUSTRALIA = "Austrália";
    private static final String KOREA = "Coréia do Sul";
    private static final String CAMEROON = "Camarões";
    private static final String JAPAN = "Japão";
    private static final String NIGERIA = "Nigéria";
    private static final String IRAN = "Irã";
    private static final String GHANA = "Gana";
    private static final String HONDURAS = "Honduras";
    private static final String COSTA_RICA = "Costa Rica";
    private static final String ECUADOR = "Equador";
    private static final String COSTA_DO_MARFIM = "Costa do Marfim";
    private static final String ALGERIA = "Argélia";
    private static final String BOSNIA = "Bósnia e Herzegovina";
    private static final String MEXICO = "México";
    private static final String RUSSIA = "Rússia";
    private static final String CROATIA = "Croácia";
    private static final String FRANCE = "França";
    private static final String NETHERLAND = "Holanda";
    private static final String CHILE = "Chile";
    private static final String USA = "Estados Unidos";
    private static final String GREECE = "Grécia";
    private static final String BELGIUM = "Bélgica";
    private static final String ENGLAND = "Inglaterra";
    private static final String ITALY = "Itália";
    private static final String COLOMBIA = "Colômbia";
    private static final String URUGUAY = "Uruguai";
    private static final String SWEETZERLAND = "Suíça";
    private static final String ARGENTINA = "Argentina";
    private static final String PORTUGAL = "Portugal";
    private static final String GERMANY = "Alemanha";
    private static final String SPAIN = "Espanha";
    private static final String BRAZIL = "Brasil";

    private static final String CHUTEIRA_DA = "Chuteira da ";
    private static final String CHUTEIRA_DO = "Chuteira do ";

    private static final String CHALLENGE_TITTLE = "DESAFIO #";
    private static final String CHALLENGE_TEXT = "Complete o desafio para\n desbloquear a chuteira";
    private static final String CHALLENGE_DETAILS_TEXT_PART_1 = "Faça ";
    private static final String CHALLENGE_DETAILS_TEXT_PART_2 = " pontos.";

    @Override
    public void createScene() {
        createBackground();
        createChildsScene();
    }

    private void createBackground() {
        sceneBackground = new Sprite(0, 0, 480, 800, resourceManager.gamingBgRegion, vbom);
    }

    private void createChildsScene() {
        menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);
        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(true);
        SpriteBackground sBg = new SpriteBackground(sceneBackground);
        menuScene.setBackground(sBg);
        title = new Text(0, 0, resourceManager.titleFont, "Escolha sua chuteira", vbom);
        float titlePosX = (MainActivity.CAMERA_WIDTH - title.getWidth()) * 0.5f;
        title.setPosition(titlePosX, 100f);
        title.setScale(0.8f);
        menuScene.attachChild(title);

        braIcon = ButtonUtils.teamButton(BRA_BUTTON, "Brasil", resourceManager.brazilIcon, vbom);
        menuScene.addMenuItem(braIcon);
        camIcon = ButtonUtils.teamButton(CAM_BUTTON, "Camarões", resourceManager.cameroonIcon, vbom);
        menuScene.addMenuItem(camIcon);
        croIcon = ButtonUtils.teamButton(CRO_BUTTON, "Croácia", resourceManager.croatiaIcon, vbom);
        menuScene.addMenuItem(croIcon);
        mexIcon = ButtonUtils.teamButton(MEX_BUTTON, "México", resourceManager.mexicoIcon, vbom);
        menuScene.addMenuItem(mexIcon);
        ausIcon = ButtonUtils.teamButton(AUS_BUTTON, "Austrália", resourceManager.australiaIcon, vbom);
        menuScene.addMenuItem(ausIcon);
        chiIcon = ButtonUtils.teamButton(CHI_BUTTON, "Chile", resourceManager.chileIcon, vbom);
        menuScene.addMenuItem(chiIcon);
        holIcon = ButtonUtils.teamButton(HOL_BUTTON, "Holanda", resourceManager.netherlandsIcon, vbom);
        menuScene.addMenuItem(holIcon);
        espIcon = ButtonUtils.teamButton(SPA_BUTTON, "Espanha", resourceManager.spainIcon, vbom);
        menuScene.addMenuItem(espIcon);
        colIcon = ButtonUtils.teamButton(COL_BUTTON, "Colombia", resourceManager.colombiaIcon, vbom);
        menuScene.addMenuItem(colIcon);
        cdmIcon = ButtonUtils.teamButton(CDM_BUTTON, "Costa do Marfim", resourceManager.costaDoMarfinIcon, vbom);
        menuScene.addMenuItem(cdmIcon);
        greIcon = ButtonUtils.teamButton(GRE_BUTTON, "Grécia", resourceManager.greeceIcon, vbom);
        menuScene.addMenuItem(greIcon);
        japIcon = ButtonUtils.teamButton(JAP_BUTTON, "Japão", resourceManager.japanIcon, vbom);
        menuScene.addMenuItem(japIcon);
        cosIcon = ButtonUtils.teamButton(COS_BUTTON, "Costa Rica", resourceManager.costaRicaIcon, vbom);
        menuScene.addMenuItem(cosIcon);
        engIcon = ButtonUtils.teamButton(ENG_BUTTON, "Inglaterra", resourceManager.englandIcon, vbom);
        menuScene.addMenuItem(engIcon);
        itaIcon = ButtonUtils.teamButton(ITA_BUTTON, "Itália", resourceManager.italyIcon, vbom);
        menuScene.addMenuItem(itaIcon);
        uruIcon = ButtonUtils.teamButton(URU_BUTTON, "Uruguai", resourceManager.uruguayIcon, vbom);
        menuScene.addMenuItem(uruIcon);
        ecuIcon = ButtonUtils.teamButton(ECU_BUTTON, "Equador", resourceManager.ecuadorIcon, vbom);
        menuScene.addMenuItem(ecuIcon);
        fraIcon = ButtonUtils.teamButton(FRA_BUTTON, "França", resourceManager.franceIcon, vbom);
        menuScene.addMenuItem(fraIcon);
        honIcon = ButtonUtils.teamButton(HON_BUTTON, "Honduras", resourceManager.hondurasIcon, vbom);
        menuScene.addMenuItem(honIcon);
        swiIcon = ButtonUtils.teamButton(SUI_BUTTON, "Suíça", resourceManager.swizerlandIcon, vbom);
        menuScene.addMenuItem(swiIcon);
        argIcon = ButtonUtils.teamButton(ARG_BUTTON, "Argentina", resourceManager.argentinaIcon, vbom);
        menuScene.addMenuItem(argIcon);
        bosIcon = ButtonUtils.teamButton(BOS_BUTTON, "Bósnia", resourceManager.bosniaAndHerzegovinaIcon, vbom);
        menuScene.addMenuItem(bosIcon);
        iraIcon = ButtonUtils.teamButton(IRA_BUTTON, "Iran", resourceManager.iranIcon, vbom);
        menuScene.addMenuItem(iraIcon);
        nigIcon = ButtonUtils.teamButton(NIG_BUTTON, "Nigéria", resourceManager.nigeriaIcon, vbom);
        menuScene.addMenuItem(nigIcon);
        gerIcon = ButtonUtils.teamButton(GER_BUTTON, "Alemanha", resourceManager.germanyIcon, vbom);
        menuScene.addMenuItem(gerIcon);
        ghaIcon = ButtonUtils.teamButton(GHA_BUTTON, "Gana", resourceManager.ghanaIcon, vbom);
        menuScene.addMenuItem(ghaIcon);
        porIcon = ButtonUtils.teamButton(POR_BUTTON, "Potugal", resourceManager.portugalIcon, vbom);
        menuScene.addMenuItem(porIcon);
        usaIcon = ButtonUtils.teamButton(USA_BUTTON, "EUA", resourceManager.usaIcon, vbom);
        menuScene.addMenuItem(usaIcon);
        algIcon = ButtonUtils.teamButton(ALG_BUTTON, "Argélia", resourceManager.algeriaIcon, vbom);
        menuScene.addMenuItem(algIcon);
        belIcon = ButtonUtils.teamButton(BEL_BUTTON, "Bélgica", resourceManager.belgiumIcon, vbom);
        menuScene.addMenuItem(belIcon);
        korIcon = ButtonUtils.teamButton(KOR_BUTTON, "Coréia do Sul", resourceManager.koreaRepublicIcon, vbom);
        menuScene.addMenuItem(korIcon);
        rusIcon = ButtonUtils.teamButton(RUS_BUTTON, "Rússia", resourceManager.russiaIcon, vbom);
        menuScene.addMenuItem(rusIcon);

        float col1 = (MainActivity.CAMERA_WIDTH - resourceManager.brazilIcon.getWidth() * 4) * 0.2f;
        float col2 = col1 * 2 + resourceManager.brazilIcon.getWidth();
        float col3 = col1 * 3 + resourceManager.brazilIcon.getWidth() * 2;
        float col4 = col1 * 4 + resourceManager.brazilIcon.getWidth() * 3;
        float row1 = 150f;
        float row2 = 230f;
        float row3 = 310f;
        float row4 = 390f;
        float row5 = 470f;
        float row6 = 550f;
        float row7 = 630f;
        float row8 = 710f;

        final int currentShoesScore = activity.getShoesScore();
        lockList = new ArrayList<Sprite>();
        bonusList = new ArrayList<Text>();

        Sprite lockIcon;
        Text bonusText;

        ausIcon.setPosition(col1, row1);

        korIcon.setPosition(col2, row1);
        if (currentShoesScore < 1) {
            lockIcon = createLockIcon(korIcon);
        } else {
            bonusText = createBonusValue(korIcon, "5");
        }

        camIcon.setPosition(col3, row1);
        if (currentShoesScore < 2) {
            lockIcon = createLockIcon(camIcon);
        } else {
            bonusText = createBonusValue(camIcon, "10");
        }

        japIcon.setPosition(col4, row1);
        if (currentShoesScore < 3) {
            lockIcon = createLockIcon(japIcon);
        } else {
            bonusText = createBonusValue(japIcon, "15");
        }

        nigIcon.setPosition(col1, row2);
        if (currentShoesScore < 4) {
            lockIcon = createLockIcon(nigIcon);
        } else {
            bonusText = createBonusValue(nigIcon, "20");
        }

        iraIcon.setPosition(col2, row2);
        if (currentShoesScore < 5) {
            lockIcon = createLockIcon(iraIcon);
        } else {
            bonusText = createBonusValue(iraIcon, "30");
        }

        ghaIcon.setPosition(col3, row2);
        if (currentShoesScore < 6) {
            lockIcon = createLockIcon(ghaIcon);
        } else {
            bonusText = createBonusValue(ghaIcon, "40");
        }

        honIcon.setPosition(col4, row2);
        if (currentShoesScore < 7) {
            lockIcon = createLockIcon(honIcon);
        } else {
            bonusText = createBonusValue(honIcon, "50");
        }

        cosIcon.setPosition(col1, row3);
        if (currentShoesScore < 8) {
            lockIcon = createLockIcon(cosIcon);
        } else {
            bonusText = createBonusValue(cosIcon, "70");
        }

        ecuIcon.setPosition(col2, row3);
        if (currentShoesScore < 9) {
            lockIcon = createLockIcon(ecuIcon);
        } else {
            bonusText = createBonusValue(ecuIcon, "90");
        }

        cdmIcon.setPosition(col3, row3);
        if (currentShoesScore < 10) {
            lockIcon = createLockIcon(cdmIcon);
        } else {
            bonusText = createBonusValue(cdmIcon, "110");
        }

        algIcon.setPosition(col4, row3);
        if (currentShoesScore < 11) {
            lockIcon = createLockIcon(algIcon);
        } else {
            bonusText = createBonusValue(algIcon, "130");
        }

        bosIcon.setPosition(col1, row4);
        if (currentShoesScore < 12) {
            lockIcon = createLockIcon(bosIcon);
        } else {
            bonusText = createBonusValue(bosIcon, "150");
        }

        mexIcon.setPosition(col2, row4);
        if (currentShoesScore < 13) {
            lockIcon = createLockIcon(mexIcon);
        } else {
            bonusText = createBonusValue(mexIcon, "180");
        }

        rusIcon.setPosition(col3, row4);
        if (currentShoesScore < 14) {
            lockIcon = createLockIcon(rusIcon);
        } else {
            bonusText = createBonusValue(rusIcon, "210");
        }

        croIcon.setPosition(col4, row4);
        if (currentShoesScore < 15) {
            lockIcon = createLockIcon(croIcon);
        } else {
            bonusText = createBonusValue(croIcon, "240");
        }

        fraIcon.setPosition(col1, row5);
        if (currentShoesScore < 16) {
            lockIcon = createLockIcon(fraIcon);
        } else {
            bonusText = createBonusValue(fraIcon, "270");
        }
        holIcon.setPosition(col2, row5);
        if (currentShoesScore < 17) {
            lockIcon = createLockIcon(holIcon);
        } else {
            bonusText = createBonusValue(holIcon, "300");
        }

        chiIcon.setPosition(col3, row5);
        if (currentShoesScore < 18) {
            lockIcon = createLockIcon(chiIcon);
        } else {
            bonusText = createBonusValue(chiIcon, "340");
        }

        usaIcon.setPosition(col4, row5);
        if (currentShoesScore < 19) {
            lockIcon = createLockIcon(usaIcon);
        } else {
            bonusText = createBonusValue(usaIcon, "380");
        }

        greIcon.setPosition(col1, row6);
        if (currentShoesScore < 20) {
            lockIcon = createLockIcon(greIcon);
        } else {
            bonusText = createBonusValue(greIcon, "420");
        }

        belIcon.setPosition(col2, row6);
        if (currentShoesScore < 21) {
            lockIcon = createLockIcon(belIcon);
        } else {
            bonusText = createBonusValue(belIcon, "460");
        }

        engIcon.setPosition(col3, row6);
        if (currentShoesScore < 22) {
            lockIcon = createLockIcon(engIcon);
        } else {
            bonusText = createBonusValue(engIcon, "500");
        }

        itaIcon.setPosition(col4, row6);
        if (currentShoesScore < 23) {
            lockIcon = createLockIcon(itaIcon);
        } else {
            bonusText = createBonusValue(itaIcon, "550");
        }

        colIcon.setPosition(col1, row7);
        if (currentShoesScore < 24) {
            lockIcon = createLockIcon(colIcon);
        } else {
            bonusText = createBonusValue(colIcon, "600");
        }

        uruIcon.setPosition(col2, row7);
        if (currentShoesScore < 25) {
            lockIcon = createLockIcon(uruIcon);
        } else {
            bonusText = createBonusValue(uruIcon, "650");
        }

        swiIcon.setPosition(col3, row7);
        if (currentShoesScore < 26) {
            lockIcon = createLockIcon(swiIcon);
        } else {
            bonusText = createBonusValue(swiIcon, "700");
        }

        argIcon.setPosition(col4, row7);
        if (currentShoesScore < 27) {
            lockIcon = createLockIcon(argIcon);
        } else {
            bonusText = createBonusValue(argIcon, "750");
        }

        porIcon.setPosition(col1, row8);
        if (currentShoesScore < 28) {
            lockIcon = createLockIcon(porIcon);
        } else {
            bonusText = createBonusValue(porIcon, "800");
        }

        gerIcon.setPosition(col2, row8);
        if (currentShoesScore < 29) {
            lockIcon = createLockIcon(gerIcon);
        } else {
            bonusText = createBonusValue(gerIcon, "850");
        }

        espIcon.setPosition(col3, row8);
        if (currentShoesScore < 30) {
            lockIcon = createLockIcon(espIcon);
        } else {
            bonusText = createBonusValue(espIcon, "900");
        }

        braIcon.setPosition(col4, row8);
        if (currentShoesScore < 31) {
            lockIcon = createLockIcon(braIcon);
        } else {
            bonusText = createBonusValue(braIcon, "1000");
        }

        createChallengePopup();

        menuScene.setOnMenuItemClickListener(this);
        setChildScene(menuScene);
    }

    private Text createBonusValue(IMenuItem pShoesIcon, String pBonusValue) {
        final Text bonusText = new Text(0,0,resourceManager.gameScoreFont, "+" + pBonusValue, vbom);
        menuScene.attachChild(bonusText);
        float posX = Float.valueOf(pBonusValue) >= 100f ? pShoesIcon.getX() - 30f : pShoesIcon.getX() - 20f;
        posX = Float.valueOf(pBonusValue) > 900f ? pShoesIcon.getX() - 40f : posX;
        bonusText.setPosition(posX, pShoesIcon.getY() - 10f);
        bonusText.setScale(0.5f);
        bonusList.add(bonusText);
        return bonusText;
    }

    private Sprite createLockIcon(IMenuItem pShoesIcon) {
        final Sprite lockIcon = new Sprite(0, 0, resourceManager.lockIconTextureRegion, vbom);
        menuScene.attachChild(lockIcon);
        lockIcon.setScale(0.5f);
        lockIcon.setPosition(pShoesIcon.getX() - lockIcon.getWidth() * 0.2f, pShoesIcon.getY() - lockIcon.getHeight() * 0.2f);
        lockList.add(lockIcon);
        return lockIcon;
    }

    private void createChallengePopup() {

        challengeTitle = new Text(0, 0, resourceManager.titleFont, CHALLENGE_TITTLE + "0123456789", 25, vbom);
        challengeTitle.setScale(0.8f);
        challengeTitle.setSkewCenter(1, 1);
        menuScene.attachChild(challengeTitle);
        challengeTitle.setVisible(false);

        challengeText = new Text(0, 0, resourceManager.font, CHALLENGE_TEXT, 1000, vbom);
        challengeText.setScale(0.8f);
        challengeText.setPosition(-25f, 300f);
        challengeText.setSkewCenter(1, 1);
        menuScene.attachChild(challengeText);
        challengeText.setVisible(false);

        shoesName = new Text(0, 0, resourceManager.gameScoreFont, CHUTEIRA_DA, 1000, vbom);
        shoesName.setSkewCenter(1, 1);
        shoesName.setScale(0.8f);
        menuScene.attachChild(shoesName);
        shoesName.setVisible(false);

        challengeDetails = new Text(0, 0, resourceManager.titleFont, CHALLENGE_DETAILS_TEXT_PART_1 + "0123456789" + CHALLENGE_DETAILS_TEXT_PART_2, 1000, vbom);
        challengeDetails.setSkewCenter(1, 1);
        challengeDetails.setScale(0.7f);
        menuScene.attachChild(challengeDetails);
        challengeDetails.setVisible(false);

        initChallengeBtn = ButtonUtils.shortButton(INIT_CHALLENGE_BTN, "Encarar", resourceManager.button, vbom, 1.2f, 1f);
        menuScene.addMenuItem(initChallengeBtn);
        initChallengeBtn.setPosition(15f, 500f);
        initChallengeBtn.setVisible(false);

        noBtn = ButtonUtils.shortButton(NO_BTN, "Tô fora!", resourceManager.button, vbom, 1.2f, 1f);
        menuScene.addMenuItem(noBtn);
        noBtn.setPosition(initChallengeBtn.getX() + initChallengeBtn.getWidth() + 10f, 500f);
        noBtn.setVisible(false);

        okBtn = ButtonUtils.shortButton(NO_BTN, "Voltar", resourceManager.button, vbom, 1.2f, 1f);
        menuScene.addMenuItem(okBtn);
        okBtn.setPosition((MainActivity.CAMERA_WIDTH - okBtn.getWidth()) * 0.5f, 400f);
        okBtn.setVisible(false);
    }

    private void showChallengePopup (final String pChallengeNumber, final String pEmbaixadinhasNumber, final String pShoesName, final float pNamePosX, final float pChallengePosX) {
        popupVisible = true;

        int i = 0;
        while (i < lockList.size()) {
            lockList.get(i).setVisible(false);
            i++;
        }

        i = 0;
        while (i < bonusList.size()) {
            bonusList.get(i).setVisible(false);
            i++;
        }

        showButtons(false);

        challengeTitle.setText(CHALLENGE_TITTLE + pChallengeNumber);
        float challengeTitlePosX = (MainActivity.CAMERA_WIDTH - challengeTitle.getWidth()) * 0.5f;
        challengeTitle.setPosition(challengeTitlePosX, 200f);
        challengeTitle.setVisible(true);

        challengeText.setText(CHALLENGE_TEXT);
        challengeText.setVisible(true);

        shoesName.setText(pShoesName);
        shoesName.setPosition(pNamePosX, 400f);
        shoesName.setVisible(true);

        challengeDetails.setText(CHALLENGE_DETAILS_TEXT_PART_1 + pEmbaixadinhasNumber + CHALLENGE_DETAILS_TEXT_PART_2);
        challengeDetails.setPosition((MainActivity.CAMERA_WIDTH - challengeDetails.getWidth()) * 0.5f, 250f);
        challengeDetails.setVisible(true);

        initChallengeBtn.setVisible(true);
        noBtn.setVisible(true);
    }

    private void hidePopup () {
        popupVisible = false;

        int i = 0;
        while (i < lockList.size()) {
            lockList.get(i).setVisible(true);
            i++;
        }

        i = 0;
        while (i < bonusList.size()) {
            bonusList.get(i).setVisible(true);
            i++;
        }

        showButtons(true);

        challengeTitle.setVisible(false);
        challengeText.setVisible(false);
        shoesName.setVisible(false);
        challengeDetails.setVisible(false);
        initChallengeBtn.setVisible(false);
        noBtn.setVisible(false);
        okBtn.setVisible(false);
    }

    private void showButtons(boolean pShow) {
        title.setVisible(pShow);

        ausIcon.setVisible(pShow);
        korIcon.setVisible(pShow);
        camIcon.setVisible(pShow);
        japIcon.setVisible(pShow);
        nigIcon.setVisible(pShow);
        iraIcon.setVisible(pShow);
        ghaIcon.setVisible(pShow);
        honIcon.setVisible(pShow);
        cosIcon.setVisible(pShow);
        ecuIcon.setVisible(pShow);
        cdmIcon.setVisible(pShow);
        algIcon.setVisible(pShow);
        bosIcon.setVisible(pShow);
        mexIcon.setVisible(pShow);
        rusIcon.setVisible(pShow);
        croIcon.setVisible(pShow);
        fraIcon.setVisible(pShow);
        holIcon.setVisible(pShow);
        chiIcon.setVisible(pShow);
        usaIcon.setVisible(pShow);
        greIcon.setVisible(pShow);
        belIcon.setVisible(pShow);
        engIcon.setVisible(pShow);
        itaIcon.setVisible(pShow);
        colIcon.setVisible(pShow);
        uruIcon.setVisible(pShow);
        swiIcon.setVisible(pShow);
        argIcon.setVisible(pShow);
        porIcon.setVisible(pShow);
        gerIcon.setVisible(pShow);
        espIcon.setVisible(pShow);
        braIcon.setVisible(pShow);
    }

    @Override
    public void onBackKeyPressed() {
        camera.clearUpdateHandlers();
        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, ResourceManager.getInstance().currentTeam);
        SceneManager.getInstance().currentChallenge = "none";
        SceneManager.getInstance().currentChallengeShoes = "";
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_SHOES;
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
        int currentShoeLevel = activity.getShoesScore();
        switch (pMenuItem.getID()) {
            case AUS_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "aus");
                    SceneManager.getInstance().currentChallenge = "none";
                }
                return true;
            case KOR_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (currentShoeLevel == 0) {
                        showChallengePopup("1", "60", KOREA, 50f, -60f);
                        SceneManager.getInstance().currentChallenge = "kor";
                    } else if (currentShoeLevel >= 1) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "kor");
                        SceneManager.getInstance().currentShoes = "kor";
                        SceneManager.getInstance().currentChallenge = "none";
                    }
                }
                return true;
            case CAM_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 1) {
                        showChallengePopup("2", "80", CAMEROON, 100f, -60f);
                        SceneManager.getInstance().currentChallenge = "cam";
                    } else if (currentShoeLevel >= 2) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cam");
                        SceneManager.getInstance().currentShoes = "cam";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("1");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cam");
                return true;
            case JAP_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 2) {
                        showChallengePopup("3", "100", JAPAN, 150f, -80f);
                        SceneManager.getInstance().currentChallenge = "jap";
                    } else if (currentShoeLevel >= 3) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "jap");
                        SceneManager.getInstance().currentShoes = "jap";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("2");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "jap");
                return true;
            case NIG_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 3) {
                        showChallengePopup("4", "130", NIGERIA, 150f, -80f);
                        SceneManager.getInstance().currentChallenge = "nig";
                    } else if (currentShoeLevel >= 4) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "nig");
                        SceneManager.getInstance().currentShoes = "nig";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("3");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "nig");
                return true;
            case IRA_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 4) {
                        showChallengePopup("5", "160", IRAN, 200f, -80f);
                        SceneManager.getInstance().currentChallenge = "ira";
                    } else if (currentShoeLevel >= 5) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ira");
                        SceneManager.getInstance().currentShoes = "ira";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("4");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ira");
                return true;
            case GHA_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 5) {
                        showChallengePopup("6", "190", GHANA, 200f, -80f);
                        SceneManager.getInstance().currentChallenge = "gha";
                    } else if (currentShoeLevel >= 6) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "gha");
                        SceneManager.getInstance().currentShoes = "gha";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("5");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "gha");
                return true;
            case HON_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 6) {
                        showChallengePopup("7", "230", HONDURAS, 100f, -85f);
                        SceneManager.getInstance().currentChallenge = "hon";
                    } else if (currentShoeLevel >= 7) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "hon");
                        SceneManager.getInstance().currentShoes = "hon";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("6");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "hon");
                return true;
            case COS_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 7) {
                        showChallengePopup("8", "270", COSTA_RICA, 100f, -85f);
                        SceneManager.getInstance().currentChallenge = "cos";
                    } else if (currentShoeLevel >= 8) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cos");
                        SceneManager.getInstance().currentShoes = "cos";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("7");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cos");
                return true;
            case ECU_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 8) {
                        showChallengePopup("9", "310", ECUADOR, 130f, -80f);
                        SceneManager.getInstance().currentChallenge = "ecu";
                    } else if (currentShoeLevel >= 9) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ecu");
                        SceneManager.getInstance().currentShoes = "ecu";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("8");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ecu");
                return true;
            case CDM_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 9) {
                        showChallengePopup("10", "360", COSTA_DO_MARFIM, 30f, -85f);
                        SceneManager.getInstance().currentChallenge = "cdm";
                    } else if (currentShoeLevel >= 10) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cdm");
                        SceneManager.getInstance().currentShoes = "cdm";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("9");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cdm");
                return true;
            case ALG_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 10) {
                        showChallengePopup("11", "410", ALGERIA, 150f, -80f);
                        SceneManager.getInstance().currentChallenge = "alg";
                    } else if (currentShoeLevel >= 11) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "alg");
                        SceneManager.getInstance().currentShoes = "alg";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("10");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "alg");
                return true;
            case BOS_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 11) {
                        showChallengePopup("12", "460", BOSNIA, -45f, -85f);
                        SceneManager.getInstance().currentChallenge = "bos";
                    } else if (currentShoeLevel >= 12) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bos");
                        SceneManager.getInstance().currentShoes = "bos";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("11");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bos");
                return true;
            case MEX_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 12) {
                        showChallengePopup("13", "510", MEXICO, 150f, -85f);
                        SceneManager.getInstance().currentChallenge = "mex";
                    } else if (currentShoeLevel >= 13) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "mex");
                        SceneManager.getInstance().currentShoes = "mex";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("12");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "mex");
                return true;
            case RUS_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 13) {
                        showChallengePopup("14", "560", RUSSIA, 150f, -80f);
                        SceneManager.getInstance().currentChallenge = "rus";
                    } else if (currentShoeLevel >= 14) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "rus");
                        SceneManager.getInstance().currentShoes = "rus";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("13");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "rus");
                return true;
            case CRO_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 14) {
                        showChallengePopup("15", "620", CROATIA, 150f, -85f);
                        SceneManager.getInstance().currentChallenge = "cro";
                    } else if (currentShoeLevel >= 15) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cro");
                        SceneManager.getInstance().currentShoes = "cro";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("14");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cro");
                return true;
            case FRA_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 15) {
                        showChallengePopup("16", "680", FRANCE, 150f, -85f);
                        SceneManager.getInstance().currentChallenge = "fra";
                    } else if (currentShoeLevel >= 16) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "fra");
                        SceneManager.getInstance().currentShoes = "fra";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("15");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "fra");
                return true;
            case HOL_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 16) {
                        showChallengePopup("17", "740", NETHERLAND, 150f, -80f);
                        SceneManager.getInstance().currentChallenge = "hol";
                    } else if (currentShoeLevel >= 17) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "hol");
                        SceneManager.getInstance().currentShoes = "hol";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("16");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "hol");
                return true;
            case CHI_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 17) {
                        showChallengePopup("18", "800", CHILE, 150f, -85f);
                        SceneManager.getInstance().currentChallenge = "chi";
                    } else if (currentShoeLevel >= 18) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "chi");
                        SceneManager.getInstance().currentShoes = "chi";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("17");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "chi");
                return true;
            case USA_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 18) {
                        showChallengePopup("19", "860", USA, 30f, -85f);
                        SceneManager.getInstance().currentChallenge = "usa";
                    } else if (currentShoeLevel >= 19) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "usa");
                        SceneManager.getInstance().currentShoes = "usa";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("18");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "usa");
                return true;
            case GRE_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 19) {
                        showChallengePopup("20", "920", GREECE, 150f, -85f);
                        SceneManager.getInstance().currentChallenge = "gre";
                    } else if (currentShoeLevel >= 20) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "gre");
                        SceneManager.getInstance().currentShoes = "gre";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("19");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "gre");
                return true;
            case BEL_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 20) {
                        showChallengePopup("21", "980", BELGIUM, 150f, -85f);
                        SceneManager.getInstance().currentChallenge = "bel";
                    } else if (currentShoeLevel >= 21) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bel");
                        SceneManager.getInstance().currentShoes = "bel";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("20");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bel");
                return true;
            case ENG_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 21) {
                        showChallengePopup("22", "1050", ENGLAND, 100f, -90f);
                        SceneManager.getInstance().currentChallenge = "eng";
                    } else if (currentShoeLevel >= 22) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "eng");
                        SceneManager.getInstance().currentShoes = "eng";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("21");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "eng");
                return true;
            case ITA_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 22) {
                        showChallengePopup("23", "1120", ITALY, 150f, -85f);
                        SceneManager.getInstance().currentChallenge = "ita";
                    } else if (currentShoeLevel >= 23) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ita");
                        SceneManager.getInstance().currentShoes = "ita";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("22");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ita");
                return true;
            case COL_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 23) {
                        showChallengePopup("24", "1190", COLOMBIA, 120f, -85f);
                        SceneManager.getInstance().currentChallenge = "col";
                    } else if (currentShoeLevel >= 24) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "col");
                        SceneManager.getInstance().currentShoes = "col";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("23");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "col");
                return true;
            case URU_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 24) {
                        showChallengePopup("25", "1260", URUGUAY, 120f, -90f);
                        SceneManager.getInstance().currentChallenge = "uru";
                    } else if (currentShoeLevel >= 25) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "uru");
                        SceneManager.getInstance().currentShoes = "uru";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("24");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "uru");
                return true;
            case SUI_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 25) {
                        showChallengePopup("26", "1330", SWEETZERLAND, 150f, -90f);
                        SceneManager.getInstance().currentChallenge = "sui";
                    } else if (currentShoeLevel >= 26) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "sui");
                        SceneManager.getInstance().currentShoes = "sui";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("25");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "sui");
                return true;
            case ARG_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 26) {
                        showChallengePopup("27", "1400", ARGENTINA, 110f, -90f);
                        SceneManager.getInstance().currentChallenge = "arg";
                    } else if (currentShoeLevel >= 27) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "arg");
                        SceneManager.getInstance().currentShoes = "arg";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("26");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "arg");
                return true;
            case POR_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 27) {
                        showChallengePopup("28", "1470", PORTUGAL, 120f, -90f);
                        SceneManager.getInstance().currentChallenge = "por";
                    } else if (currentShoeLevel >= 28) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "por");
                        SceneManager.getInstance().currentShoes = "por";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("27");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "por");
                return true;
            case GER_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 28) {
                        showChallengePopup("29", "1540", GERMANY, 100f, -90f);
                        SceneManager.getInstance().currentChallenge = "ger";
                    } else if (currentShoeLevel >= 29) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ger");
                        SceneManager.getInstance().currentShoes = "ger";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("28");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ger");
                return true;
            case SPA_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 29) {
                        showChallengePopup("30", "1620", SPAIN, 110f, -90f);
                        SceneManager.getInstance().currentChallenge = "spa";
                    } else if (currentShoeLevel >= 30) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "spa");
                        SceneManager.getInstance().currentShoes = "spa";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("29");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "spa");
                return true;
            case BRA_BUTTON:
                if (!popupVisible) {
                    resourceManager.playSound.play();
                    if (activity.getShoesScore() == 30) {
                        showChallengePopup("31", "1800", BRAZIL, 130f, -90f);
                        SceneManager.getInstance().currentChallenge = "bra";
                    } else if (currentShoeLevel >= 31) {
                        SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bra");
                        SceneManager.getInstance().currentShoes = "bra";
                        SceneManager.getInstance().currentChallenge = "none";
                    } else {
                        showWarningPopup("30");
                    }
                }
//                activity.showChallenge();
//                SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bra");
                return true;
            case INIT_CHALLENGE_BTN:
                if(popupVisible) {
                    hidePopup();
                    initChallenge();
                    resourceManager.playSound.play();
                }
                return true;
            case NO_BTN:
                if(popupVisible) {
                    resourceManager.playSound.play();
                    hidePopup();
                    SceneManager.getInstance().currentChallenge = "none";
                    SceneManager.getInstance().currentChallengeShoes = "";
                }
                return true;
            case OK_BTN:
                if(popupVisible) {
                    resourceManager.playSound.play();
                    hidePopup();
                    SceneManager.getInstance().currentChallenge = "none";
                    SceneManager.getInstance().currentChallengeShoes = "";
                }
                return true;
            default:
                return false;
        }
    }

    private void showWarningPopup(final String pChallengeNumber) {
        popupVisible = true;

        int i = 0;
        while (i < lockList.size()) {
            lockList.get(i).setVisible(false);
            i++;
        }

        i = 0;
        while (i < bonusList.size()) {
            bonusList.get(i).setVisible(false);
            i++;
        }

        showButtons(false);

        challengeTitle.setText("DESAFIO BLOQUEADO");
        float challengeTitlePosX = (MainActivity.CAMERA_WIDTH - challengeTitle.getWidth()) * 0.5f;
        challengeTitle.setPosition(challengeTitlePosX, 200f);

        challengeDetails.setText("Para desbloquear");
        challengeDetails.setPosition((MainActivity.CAMERA_WIDTH - challengeDetails.getWidth()) * 0.5f, 250f);
        challengeDetails.setVisible(true);
        challengeTitle.setVisible(true);

        challengeText.setText("  complete o desafio #" + pChallengeNumber);
        challengeText.setVisible(true);

        okBtn.setVisible(true);

//        shoesName.setText(pShoesName);
//        shoesName.setPosition(pNamePosX, 400f);
//        shoesName.setVisible(true);

//        initChallengeBtn.setVisible(true);
//        noBtn.setVisible(true);
    }

    private void initChallenge () {
        final String currentChallenge = SceneManager.getInstance().currentChallenge;
        if (currentChallenge.equals("kor")) {
            SceneManager.getInstance().currentChallengeShoes = KOREA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "aus");
            SceneManager.getInstance().currentShoes = "aus";
        } else if (currentChallenge.equals("cam")) {
            SceneManager.getInstance().currentChallengeShoes = CAMEROON;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "kor");
            SceneManager.getInstance().currentShoes = "kor";
        } else if (currentChallenge.equals("jap")) {
            SceneManager.getInstance().currentChallengeShoes = JAPAN;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cam");
            SceneManager.getInstance().currentShoes = "cam";
        } else if (currentChallenge.equals("nig")) {
            SceneManager.getInstance().currentChallengeShoes = NIGERIA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "jap");
            SceneManager.getInstance().currentShoes = "jap";
        } else if (currentChallenge.equals("ira")) {
            SceneManager.getInstance().currentChallengeShoes = IRAN;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "nig");
            SceneManager.getInstance().currentShoes = "nig";
        } else if (currentChallenge.equals("gha")) {
            SceneManager.getInstance().currentChallengeShoes = GHANA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ira");
            SceneManager.getInstance().currentShoes = "ira";
        } else if (currentChallenge.equals("hon")) {
            SceneManager.getInstance().currentChallengeShoes = HONDURAS;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "gha");
            SceneManager.getInstance().currentShoes = "gha";
        } else if (currentChallenge.equals("cos")) {
            SceneManager.getInstance().currentChallengeShoes = COSTA_RICA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "hon");
            SceneManager.getInstance().currentShoes = "hon";
        } else if (currentChallenge.equals("ecu")) {
            SceneManager.getInstance().currentChallengeShoes = ECUADOR;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cos");
            SceneManager.getInstance().currentShoes = "cos";
        } else if (currentChallenge.equals("cdm")) {
            SceneManager.getInstance().currentChallengeShoes = COSTA_DO_MARFIM;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ecu");
            SceneManager.getInstance().currentShoes = "ecu";
        } else if (currentChallenge.equals("alg")) {
            SceneManager.getInstance().currentChallengeShoes = ALGERIA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cdm");
            SceneManager.getInstance().currentShoes = "cdm";
        } else if (currentChallenge.equals("bos")) {
            SceneManager.getInstance().currentChallengeShoes = BOSNIA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "alg");
            SceneManager.getInstance().currentShoes = "alg";
        } else if (currentChallenge.equals("mex")) {
            SceneManager.getInstance().currentChallengeShoes = MEXICO;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bos");
            SceneManager.getInstance().currentShoes = "bos";
        } else if (currentChallenge.equals("rus")) {
            SceneManager.getInstance().currentChallengeShoes = RUSSIA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "mex");
            SceneManager.getInstance().currentShoes = "mex";
        } else if (currentChallenge.equals("cro")) {
            SceneManager.getInstance().currentChallengeShoes = CROATIA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "rus");
            SceneManager.getInstance().currentShoes = "rus";
        } else if (currentChallenge.equals("fra")) {
            SceneManager.getInstance().currentChallengeShoes = FRANCE;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "cro");
            SceneManager.getInstance().currentShoes = "cro";
        } else if (currentChallenge.equals("hol")) {
            SceneManager.getInstance().currentChallengeShoes = NETHERLAND;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "fra");
            SceneManager.getInstance().currentShoes = "fra";
        } else if (currentChallenge.equals("chi")) {
            SceneManager.getInstance().currentChallengeShoes = CHILE;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "hol");
            SceneManager.getInstance().currentShoes = "hol";
        } else if (currentChallenge.equals("usa")) {
            SceneManager.getInstance().currentChallengeShoes = USA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "chi");
            SceneManager.getInstance().currentShoes = "chi";
        } else if (currentChallenge.equals("gre")) {
            SceneManager.getInstance().currentChallengeShoes = GREECE;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "usa");
            SceneManager.getInstance().currentShoes = "usa";
        } else if (currentChallenge.equals("bel")) {
            SceneManager.getInstance().currentChallengeShoes = BELGIUM;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "gre");
            SceneManager.getInstance().currentShoes = "gre";
        } else if (currentChallenge.equals("eng")) {
            SceneManager.getInstance().currentChallengeShoes = ENGLAND;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "bel");
            SceneManager.getInstance().currentShoes = "bel";
        } else if (currentChallenge.equals("ita")) {
            SceneManager.getInstance().currentChallengeShoes = ITALY;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "eng");
            SceneManager.getInstance().currentShoes = "eng";
        } else if (currentChallenge.equals("col")) {
            SceneManager.getInstance().currentChallengeShoes = COLOMBIA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ita");
            SceneManager.getInstance().currentShoes = "ita";
        } else if (currentChallenge.equals("uru")) {
            SceneManager.getInstance().currentChallengeShoes = URUGUAY;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "col");
            SceneManager.getInstance().currentShoes = "col";
        } else if (currentChallenge.equals("sui")) {
            SceneManager.getInstance().currentChallengeShoes = SWEETZERLAND;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "uru");
            SceneManager.getInstance().currentShoes = "uru";
        } else if (currentChallenge.equals("arg")) {
            SceneManager.getInstance().currentChallengeShoes = ARGENTINA;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "sui");
            SceneManager.getInstance().currentShoes = "sui";
        } else if (currentChallenge.equals("por")) {
            SceneManager.getInstance().currentChallengeShoes = PORTUGAL;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "arg");
            SceneManager.getInstance().currentShoes = "arg";
        } else if (currentChallenge.equals("ger")) {
            SceneManager.getInstance().currentChallengeShoes = GERMANY;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "por");
            SceneManager.getInstance().currentShoes = "por";
        } else if (currentChallenge.equals("spa")) {
            SceneManager.getInstance().currentChallengeShoes = SPAIN;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "ger");
            SceneManager.getInstance().currentShoes = "ger";
        } else if (currentChallenge.equals("bra")) {
            SceneManager.getInstance().currentChallengeShoes = BRAZIL;
            SceneManager.getInstance().loadEmbaixadinhasGameScene(engine, "spa");
            SceneManager.getInstance().currentShoes = "spa";
        }
    }
}
