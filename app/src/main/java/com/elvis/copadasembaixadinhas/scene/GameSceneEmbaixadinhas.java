package com.elvis.copadasembaixadinhas.scene;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.elvis.copadasembaixadinhas.MainActivity;
import com.elvis.copadasembaixadinhas.manager.ResourceManager;
import com.elvis.copadasembaixadinhas.manager.SceneManager;
import com.elvis.copadasembaixadinhas.utils.ButtonUtils;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

/**
 * Created by com.copadasembaixadinhas.elvis on 11/06/14.
 */
public class GameSceneEmbaixadinhas extends BaseScene implements MenuScene.IOnMenuItemClickListener, ContactListener {
    private static final int TIME_TO_RESSURECTION = 200;
    private static final int SHOES_BUTTON =  0;
    private final String TAP_TO_PLAY = "TAP TO PLAY";
    private final String TAP_TO_PLAY_AGAIN = "TAP TO PLAY AGAIN";
    private final String YOUR_SCORE = "Your Score: ";
    private final String HIGH_SCORE = "High Score: ";
    public final String SCORE = "";
    public final static String BODY_ACTOR = "actor";
    public final static String BODY_FLOOR = "floor";
    public final static String BODY_BALL = "ball";
    public final static String BODY_SENSOR = "sensor";

    public static final FixtureDef PLAYER_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, false);
    public static final FixtureDef BALL_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, false);
    public static final FixtureDef CEILLING_FIXTURE = PhysicsFactory.createFixtureDef(0f, 0f, 0f, false);//(1f, 0f, 0f, false);
    public static final FixtureDef SENSOR_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, true);
    public static final FixtureDef ITEM_SENSOR_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, true);

    private AutoParallaxBackground autoParallaxBackground;
    public PhysicsWorld physicsWorld;
    private Sprite background;
    private MenuScene menuScene;
    private HUD gameHUD;
    public Text scoreText, bonusText, yourPointsText, yourRecordText, currentChallengeText, currentChallengeText2;
    private TiledSprite player;
    protected Body playerBody, ballBody;
    private Glove glove;
    private Ball ball;
    private int upIndex = 0;
    private int currentScore = 0;
    private boolean scored = false;
    private int jumpControl = 0;
    private int controler = 0;
    private boolean ballStop = false;
    private boolean physicsRegistry = false;
    private int ballController = 0;
    private Sprite shoesBtn;
    private int shoesBtnScaleIndex = 0;
    private boolean shoesBtnActionDown = false;

    public enum STATE {
        NEW, PAUSED, PLAY, DEAD, AFTERLIFE
    }

    public enum SHOOT_STATE {
        UP, DOWN, STOP
    }

    public enum BALL_STATE {
        UP, DOWN, STOP
    }

    long timestamp = 0;
    private STATE state = STATE.NEW;
    private STATE lastState;
    private SHOOT_STATE shootState = SHOOT_STATE.STOP;
    private BALL_STATE ballState = BALL_STATE.DOWN;
    public static final float SPEED_X = 14;
    public static final float GRAVITY = 70;
    public static final float SPEED_Y = -13;

    @Override
    public void createScene() {
        createPhysics();
        createBackground();
        createHUD();
        createBounds();
//        createChildsScene();
        createActor();
        creatBall();

        try {
            activity.getHighScore();
        } catch (Exception e) {
            activity.setHighScore(0);
        }

        try {
            activity.getShoesScore();
        } catch (Exception e) {
            activity.setShoesScore(0);
        }

        MainActivity.getInstance().showLoginInfo();
    }

    private void createPhysics() {
        physicsWorld = new PhysicsWorld(new Vector2(0, 0), true);
        physicsWorld.setContactListener(this);
        registerUpdateHandler(physicsWorld);
    }

    private void createBackground() {
        autoParallaxBackground = new AutoParallaxBackground(0f, 0f, 5f, 60);
        this.setBackground(autoParallaxBackground);

        final Sprite parallaxBackLayerSprite = new Sprite(0, 0, resourceManager.gamingBgRegion, vbom);
        parallaxBackLayerSprite.setSkewCenter(0, 0);

        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0.0f, parallaxBackLayerSprite));

        this.setBackground(autoParallaxBackground);
        this.setBackgroundEnabled(true);
//        background = new Sprite(0, 0, 480, 800, resourceManager.gamingBgRegion, vbom);
    }

    private void createHUD() {
        gameHUD = new HUD();
        shoesBtn = new Sprite(370, 150, 90, 90, resourceManager.shoesBtn, vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
              //  this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                if (pSceneTouchEvent.isActionDown()) {
                    this.setScale(1.2f);
                    shoesBtnActionDown = true;
                }
                if (pSceneTouchEvent.isActionUp()) {
                    this.setScale(1f);
                    resourceManager.playSound.play();
                    shoesBtnActionDown = false;

                    SceneManager.getInstance().loadChooseShoesScene(engine);
                    gameHUD.setVisible(false);
                    gameHUD.unregisterTouchArea(shoesBtn);
/*
                    if (resourceManager.gameActionMusic.isPlaying())
                    {
                        musicIsOn = false;
                        if (resourceManager.gameActionMusic.isPlaying())
                            resourceManager.gameActionMusic.pause();
                    }
                    else
                    {
                        musicIsOn = true;
                        if (!resourceManager.gameActionMusic.isPlaying())
                            resourceManager.gameActionMusic.play();
                    }
*/
                }
                return true;
            }
        };
        gameHUD.attachChild(shoesBtn);
        gameHUD.registerTouchArea(shoesBtn);

        scoreText = new Text(50f, 100f, resourceManager.gameScoreFont, "0123456789", 20, vbom);
        scoreText.setScale(1.25f);
        scoreText.setSkewCenter(1, 1);
        scoreText.setText(String.valueOf(currentScore));
        gameHUD.attachChild(scoreText);

        final int currentBonusValue = activity.getBonus(SceneManager.getInstance().currentShoes);
        if (currentBonusValue > 0) {
            bonusText = new Text(0, 0, resourceManager.gameScoreFont, "0123456789", 10, vbom);
            bonusText.setScale(0.6f);
            bonusText.setSkewCenter(1, 1);
            bonusText.setText("+" + String.valueOf(currentBonusValue));
            float bonusTextPosX = currentBonusValue <= 10 ? 35f : currentBonusValue < 1000 ? 15f : 0f;
            bonusText.setPosition(bonusTextPosX, 145f);
            gameHUD.attachChild(bonusText);
        }

        if (activity.hasCurrentChallenge()) {
            currentChallengeText = new Text(0, 0, resourceManager.titleFont, "DESAFIO" , 10, vbom);
            currentChallengeText.setScale(0.7f);
            currentChallengeText.setSkewCenter(1, 1);
            currentChallengeText.setPosition((MainActivity.CAMERA_WIDTH - currentChallengeText.getWidth()) * 0.5f, 700f);
            gameHUD.attachChild(currentChallengeText);

            currentChallengeText2 = new Text(0, 0, resourceManager.titleFont, SceneManager.getInstance().currentChallengeShoes , 30, vbom);
            currentChallengeText2.setScale(0.7f);
            currentChallengeText2.setSkewCenter(1, 1);
            currentChallengeText2.setPosition((MainActivity.CAMERA_WIDTH - currentChallengeText2.getWidth()) * 0.5f, 735f);
            gameHUD.attachChild(currentChallengeText2);
        }

        yourPointsText = new Text(100f, 250f, resourceManager.gameScoreFont, "0123456789", 20, vbom);
        yourPointsText.setScale(1.25f);
        yourPointsText.setSkewCenter(1, 1);
        yourPointsText.setText("Pontos: " + String.valueOf(currentScore));
        gameHUD.attachChild(yourPointsText);
        yourPointsText.setVisible(false);

        yourRecordText = new Text(100f, 350f, resourceManager.gameScoreFont, "0123456789", 20, vbom);
        yourRecordText.setScale(1.25f);
        yourRecordText.setSkewCenter(1, 1);
        yourRecordText.setText("Recorde: " + String.valueOf(currentScore));
        gameHUD.attachChild(yourRecordText);
        yourRecordText.setVisible(false);

/*
        final Rectangle controllLeft = new Rectangle(0, 0, 100, 100, vbom) {
            public boolean onAreaTouched(TouchEvent touchEvent, float X, float Y) {
                if (touchEvent.isActionUp()) {

                    if (glove.getY() >= 525f && shootState.equals(SHOOT_STATE.DOWN)) {

                        shootState = SHOOT_STATE.UP;

                        Vector2 v = playerBody.getLinearVelocity();
                        v.y = SPEED_Y;
                        playerBody.setLinearVelocity(v);
                    }

//                    if (glove.getY() - (ball.getY() + ball.getHeight()) <= 5) {
//                        Vector2 bv = ballBody.getLinearVelocity();
//                        bv.y = SPEED_Y * 2.5f;
//                        ballBody.setLinearVelocity(bv);
//                    }
                }
                return true;
            }
        };
        controllLeft.setPosition(20, MainActivity.CAMERA_HEIGHT - controllLeft.getHeight() - 20);
        controllLeft.setColor(Color.BLUE);

        final Rectangle controllRight = new Rectangle(0, 0, 100, 100, vbom) {
            public boolean onAreaTouched(TouchEvent touchEvent, float X, float Y) {
                if (touchEvent.isActionUp()) {

                    if (glove.getY() >= 525f && shootState.equals(SHOOT_STATE.DOWN)) {

                        shootState = SHOOT_STATE.UP;

                        Vector2 v = playerBody.getLinearVelocity();
                        v.y = SPEED_Y;
                        playerBody.setLinearVelocity(v);
                    }

//                    if (glove.getY() - (ball.getY() + ball.getHeight()) <= 5) {
//                        Vector2 bv = ballBody.getLinearVelocity();
//                        bv.y = SPEED_Y * 2.5f;
//                        ballBody.setLinearVelocity(bv);
//                    }
                }
                return true;
            }
        };
        controllRight.setPosition(MainActivity.CAMERA_WIDTH - controllRight.getWidth() - 20, controllLeft.getY());
        controllRight.setColor(Color.BLUE);

        final Rectangle startBtn = new Rectangle(0, 0, 100, 75, vbom) {
            public boolean onAreaTouched(TouchEvent touchEvent, float X, float Y) {
                if (touchEvent.isActionUp()) {
                    reset();

                    registerUpdateHandler(physicsWorld);
                    state = STATE.PLAY;
                    physicsWorld.setGravity(new Vector2(0, GRAVITY));
                }
                return true;
            }
        };
        startBtn.setPosition((MainActivity.CAMERA_WIDTH - startBtn.getWidth()) * 0.5f, (controllLeft.getY() + (MainActivity.CAMERA_HEIGHT - controllLeft.getY()) * 0.5f) - startBtn.getHeight() * 0.5f);
        startBtn.setColor(Color.CYAN);

        gameHUD.registerTouchArea(controllLeft);
        gameHUD.registerTouchArea(controllRight);
        gameHUD.registerTouchArea(startBtn);
        gameHUD.attachChild(controllLeft);
        gameHUD.attachChild(controllRight);
        gameHUD.attachChild(startBtn);
*/
        camera.setHUD(gameHUD);
    }

    private void createBounds () {
        Body ceillingBody = PhysicsFactory.createBoxBody(
                physicsWorld, 240, 675, 480, 55, BodyDef.BodyType.StaticBody, CEILLING_FIXTURE);
        ceillingBody.setUserData(BODY_FLOOR);
    }

    private void createChildsScene() {
        menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);
        menuScene.buildAnimations();
//        menuScene.setBackgroundEnabled(true);
//        SpriteBackground sBg = new SpriteBackground(background);
//        menuScene.setBackground(sBg);

        setControls();

        menuScene.setOnMenuItemClickListener(this);
        setChildScene(menuScene);
    }

    private void createActor() {
        player = new TiledSprite(0f, 0f, resourceManager.gloveTextureRegion.getWidth(), resourceManager.gloveTextureRegion.getHeight(), resourceManager.gloveTextureRegion, vbom);
//        player.setPosition((MainActivity.CAMERA_WIDTH - player.getWidth()) * 0.5f, 550f);
        player.setCurrentTileIndex(0);
        player.setSkewCenter(0, 0);
//        player.setZIndex(999);
        player.registerUpdateHandler(new IUpdateHandler() {
//
            @Override
            public void onUpdate(float pSecondsElapsed) {
              /*  if (deadState.equals("dead")) {
                    player.setCurrentTileIndex(3);
                } else if (state == STATE.PLAY) {
                    if (playerBody.getLinearVelocity().y > -0.01f) {
/*                        if (jumpControl == 0) {
                            if (controler <= 5) {
//                                player.setCurrentTileIndex(0);
                                controler++;
                            } else if (controler >= 6 && controler <= 11) {
//                                player.setCurrentTileIndex(1);
                                controler++;
                            } else {
//                                player.setCurrentTileIndex(2);
                                controler++;
                                controler = controler == 17 ? 0 : controler;
                            }
                        } else {
                            player.setCurrentTileIndex(2);
                        }

                    } else {
                        player.setCurrentTileIndex(1);
                    }
                }*/
            }

            @Override
            public void reset() { }
        });
//        playerBody = PhysicsFactory.createCircleBody(physicsWorld, MainActivity.CAMERA_WIDTH * 0.5f, 625f, 30f, BodyDef.BodyType.DynamicBody, PLAYER_FIXTURE);
//        playerBody.setFixedRotation(true);
//        playerBody.setUserData(BODY_ACTOR);
//        physicsWorld.registerPhysicsConnector(new PhysicsConnector(player, playerBody));

//        attachChild(player);

        final int centerX = (int)(MainActivity.CAMERA_WIDTH - resourceManager.gloveTextureRegion.getWidth()) / 2;
        final int centerY = (int)(MainActivity.CAMERA_HEIGHT - resourceManager.gloveTextureRegion.getHeight()) / 2;
        glove = new Glove(centerX, centerY, resourceManager.gloveTextureRegion);
        glove.setSkewCenter(0, 0);
        glove.setZIndex(999);
        glove.setCurrentTileIndex(2);
//        final PhysicsHandler physicsHandler = new PhysicsHandler(glove);
        glove.registerUpdateHandler(new IUpdateHandler() {

            @Override
            public void onUpdate(float pSecondsElapsed) {

                if (state.equals(STATE.NEW)) {
                    player.setCurrentTileIndex(2);
                } else if (state.equals(STATE.PLAY)) {
                    if (playerBody.getLinearVelocity().y > -0.01f) {
                        glove.setCurrentTileIndex(2);
                        glove.setPosition(200f, 570f);
                    /*    if (jumpControl == 0) {
                            glove.setCurrentTileIndex(2);
                            glove.setPosition(200f, 570f);
                            if (controler <= 5) {
                                glove.setCurrentTileIndex(0);
                                controler++;
                            } else if (controler >= 6 && controler <= 11) {
                                glove.setCurrentTileIndex(1);
                                controler++;
                            } else {
                                glove.setCurrentTileIndex(2);
                                controler++;
                                controler = controler == 17 ? 0 : controler;
                            }
                    */
                    /*    } else {
                            glove.setCurrentTileIndex(1);
                            glove.setPosition(glove.getX(), 520f);
                        }
                    */
                    }/* else if (shootState.equals(SHOOT_STATE.DOWN)) {
                        glove.setCurrentTileIndex(1);
                    }*/ else {
                        glove.setCurrentTileIndex(0);
                        glove.setPosition(215f, 530f);
                    }
                }
            }
            @Override
            public void reset() { }
        });//physicsHandler);
//        physicsHandler.setVelocity(50, 0);
        glove.setPosition(200f, 570f);
//        glove.setScale(0.5f);

        playerBody = PhysicsFactory.createBoxBody(physicsWorld, MainActivity.CAMERA_WIDTH * 0.5f, 650f, 100f, 20f, BodyDef.BodyType.DynamicBody, PLAYER_FIXTURE);//).createCircleBody(physicsWorld, MainActivity.CAMERA_WIDTH * 0.5f, 625f, 30f, BodyDef.BodyType.DynamicBody, PLAYER_FIXTURE);
        playerBody.setFixedRotation(true);
        playerBody.setUserData(BODY_ACTOR);
//        physicsWorld.registerPhysicsConnector(new PhysicsConnector(player, playerBody));
        attachChild(glove);
    }

    public void creatBall () {
        final int centerX = (int)(MainActivity.CAMERA_WIDTH - resourceManager.ballTextureRegion.getWidth()) / 2;
        final int centerY = (int)(MainActivity.CAMERA_HEIGHT - resourceManager.ballTextureRegion.getHeight()) / 2;
        ball = new Ball(0, 0, resourceManager.ballTextureRegion, physicsWorld, vbom);
        ball.setSkewCenter(0, 0);
        ball.setZIndex(998);
//        final PhysicsHandler physicsHandler = new PhysicsHandler(ball);
        ball.registerUpdateHandler(new IUpdateHandler() {

            @Override
            public void onUpdate(float pSecondsElapsed) {
//                if (ball.getY() > 500f) {
//                    ball.setCurrentTileIndex(0);
//                }
                if (state.equals(STATE.PLAY)) {
//                    ballState = ball.getY() >= 400f ? BALL_STATE.STOP : ballState;
                    if (ballState.equals(BALL_STATE.UP) || ballState.equals(BALL_STATE.DOWN)) {
                        if (ballBody.getLinearVelocity().y > -0.01f) {
                            //         if (jumpControl == 0) {
                            if (ballController <= 2) {
                                ball.setCurrentTileIndex(0);
                                ballController++;
                            } else if (ballController >= 3 && ballController <= 5) {
                                ball.setCurrentTileIndex(1);
                                ballController++;
                            } else if (ballController >= 6 && ballController <= 8) {
                                ball.setCurrentTileIndex(2);
                                ballController++;
                            } else if (ballController >= 9 && ballController <= 11) {
                                ball.setCurrentTileIndex(3);
                                ballController++;
                            } else if (ballController >= 12 && ballController <= 14) {
                                ball.setCurrentTileIndex(4);
                                ballController++;
                            } else {
                                ball.setCurrentTileIndex(5);
                                ballController++;
                                ballController = ballController == 16 ? 0 : ballController;
                            }
                        }
                    } else {
                        ball.setCurrentTileIndex(0);
                    }
                }else {
                    ball.setCurrentTileIndex(0);
                }
            }
            @Override
            public void reset() { }
        });//physicsHandler);
//        physicsHandler.setVelocity(50, 50);
        ballBody = PhysicsFactory.createCircleBody(physicsWorld, MainActivity.CAMERA_WIDTH * 0.5f, 600f, 41f, BodyDef.BodyType.DynamicBody, BALL_FIXTURE);
        ballBody.setFixedRotation(true);
        ballBody.setUserData(BODY_BALL);
        physicsWorld.registerPhysicsConnector(new PhysicsConnector(ball, ballBody));

        attachChild(ball);
    }

    private void setControls() {
        final IMenuItem leftBtn = ButtonUtils.gameButton(0, resourceManager.bgGameBtn, vbom);
        menuScene.addMenuItem(leftBtn);
        leftBtn.setPosition(0, MainActivity.CAMERA_HEIGHT - leftBtn.getHeight());
        final IMenuItem rightBtn = ButtonUtils.gameButton(1, resourceManager.bgGameBtn, vbom);
        menuScene.addMenuItem(rightBtn);
        rightBtn.setPosition(MainActivity.CAMERA_WIDTH - leftBtn.getWidth(), leftBtn.getY());
    }

    @Override
    public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionDown()) {
            if (state.equals(STATE.NEW)) {
//                reset();

                if (!physicsRegistry) {
                    physicsRegistry = true;
                    registerUpdateHandler(physicsWorld);
                    physicsWorld.setGravity(new Vector2(0, GRAVITY));
                }

                shootState = SHOOT_STATE.UP;

                ballState = BALL_STATE.UP;

                glove.setPosition(glove.getX() + 15f, 530f);

                state = STATE.PLAY;

                resourceManager.jumpSound.play();
//                Vector2 v = playerBody.getLinearVelocity();
//                v.y = SPEED_Y;
//                playerBody.setLinearVelocity(v);

//                ballState = BALL_STATE.UP;

//                Vector2 bv = ballBody.getLinearVelocity();
//                bv.y = SPEED_Y * 2.5f;
//                ballBody.setLinearVelocity(bv);
//                glove.setPosition(glove.getX() + 15f, 530f);

//                shootState = SHOOT_STATE.UP;

            } else if(state.equals(STATE.PLAY)) {

                resourceManager.jumpSound.play();

                if (shootState.equals(SHOOT_STATE.STOP)) {

//                glove.setPosition(glove.getX() + 15f, 530f);

                    shootState = SHOOT_STATE.UP;

//                Vector2 v = playerBody.getLinearVelocity();
//                v.y = SPEED_Y;
//                playerBody.setLinearVelocity(v);

//                if (ball.getY() + ball.getHeight() >= glove.getY() - 10) {

//                    if( glove.getY() - (ball.getY() + ball.getHeight()) <= 1) {

                    ballState = ball.getY() + ball.getHeight() >= glove.getY() - 75 ? BALL_STATE.UP : ballState;

//                        Vector2 bv = ballBody.getLinearVelocity();
//                        bv.y = SPEED_Y * 2f;
//                        ballBody.setLinearVelocity(bv);
//                    }
//                }
                } else if (shootState.equals(SHOOT_STATE.UP)) {
//                if (ball.getY() + ball.getHeight() >= glove.getY() - 10) {

                    ballState = ball.getY() + ball.getHeight() >= glove.getY() - 75 ? BALL_STATE.UP : ballState;
//                }
                }
            } /*else if (state.equals(STATE.DEAD)) {
            } */else if (state.equals(STATE.AFTERLIFE)) {
                glove.setCurrentTileIndex(2);
                glove.setPosition(200f, 570f);
                currentScore = 0;
                scoreText.setText(String.valueOf(currentScore));
                scoreText.setVisible(true);
                if (bonusText != null) bonusText.setVisible(true);
                yourPointsText.setVisible(false);
                yourRecordText.setVisible(false);
                state = STATE.NEW;
            }
//            if (ballState.equals(BALL_STATE.DOWN) && ball.getY() + ball.getHeight() >= glove.getY() - 10) {
//                ballState = ballState.equals(BALL_STATE.DOWN) && ball.getY() + ball.getHeight() >= glove.getY() - 10 ? BALL_STATE.UP : ballState;
//            }
        }
        if (pSceneTouchEvent.isActionUp()) {
            shoesBtnActionDown = false;
        }
        return false;
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {

        super.onManagedUpdate(pSecondsElapsed);

        if (!shoesBtnActionDown) {
            shoesBtnScaleIndex++;
            if (shoesBtnScaleIndex <= 20) {
                shoesBtn.setScale(1.1f);
            } else {
                shoesBtn.setScale(1f);
                shoesBtnScaleIndex = shoesBtnScaleIndex >= 40 ? 0 : shoesBtnScaleIndex;
            }
        }

        if (state.equals(STATE.PLAY)) {
            if(ball.getY() > 400f && !scored) {
                scored = true;
                currentScore++;
                scoreText.setText(String.valueOf(currentScore));
                resourceManager.activity.score = currentScore;
            } else if (ball.getY() < 400f && scored) {
                scored = false;
            }
            if (ballState.equals(BALL_STATE.UP)) {
                Vector2 bv = ballBody.getLinearVelocity();
                bv.y = SPEED_Y * 2.5f;
                ballBody.setLinearVelocity(bv);
            }
            if (shootState.equals(SHOOT_STATE.UP)) {

                Vector2 sv = playerBody.getLinearVelocity();
                sv.y = SPEED_Y ;
                playerBody.setLinearVelocity(sv);

//                if(glove.getY() - (ball.getY() + ball.getHeight()) <= 50) {

//                    Vector2 bv = ballBody.getLinearVelocity();
//                    bv.y = SPEED_Y * 2f;
//                    ballBody.setLinearVelocity(bv);
//                }

                shootState = glove.getY() >= 530f && glove.getY() < 570f ? SHOOT_STATE.DOWN : shootState;
            }
            shootState = glove.getY() >= 569f ? SHOOT_STATE.STOP : shootState;
            ballState = ball.getY() <= 250f ? BALL_STATE.DOWN : ballState;
            ballState = ballState.equals(BALL_STATE.DOWN) && ball.getY() >= 550f ? BALL_STATE.STOP : ballState;

            if (ballState.equals(BALL_STATE.STOP)) {
                jumpControl++;
                if(jumpControl >= 16) {
                    jumpControl = 0;
                    scoreText.setVisible(false);
                    if (bonusText != null) bonusText.setVisible(false);
                    final int bonusValue = activity.getBonus(SceneManager.getInstance().currentShoes);
                    final int recordValue = currentScore + bonusValue;
                    yourRecordText.setText("Recorde: " + String.valueOf(recordValue));
                    yourPointsText.setText("Pontos: " + String.valueOf(currentScore));
                    state = STATE.DEAD;

                    timestamp = System.currentTimeMillis();

                    resourceManager.activity.setHighScore(currentScore);

//                    showPoints();
                }
            }
        }

        if (state == STATE.DEAD && timestamp + TIME_TO_RESSURECTION < System.currentTimeMillis()) {
            state = STATE.AFTERLIFE;

            if (!resourceManager.activity.shared) {
//                resourceManager.activity.shared = true;
                if (resourceManager.activity.hasChallenge()) {
                    resourceManager.activity.showChallengeWinner();
                    if (currentChallengeText != null) currentChallengeText.setVisible(false);
                    if (currentChallengeText2 != null) currentChallengeText2.setVisible(false);
                } else {
                    resourceManager.activity.showScore();
                }

            }
        }

/*
        if (scored) {
//            addPillar();
//            sortChildren();
            scoreText.setText(String.valueOf(currentScore + 1));
            scored = false;
        }

        // if first pillar is out of the screen, delete it
        if (!pillars.isEmpty()) {
            Pillar fp = pillars.getFirst();
            if (fp.getX() + fp.getWidth() < resourceManager.camera.getXMin()) {
                PillarFactory.getInstance().recycle(fp);
                pillars.remove();
            }
        }

        if (state == STATE.DEAD && timestamp + TIME_TO_RESSURECTION < System.currentTimeMillis()) {
            state = STATE.AFTERLIFE;

            if (!resourceManager.activity.shared) {
                resourceManager.activity.shared = true;
                resourceManager.activity.showScore();
            }
        }
*/
    }

    private void showPoints () {
        yourPointsText.setVisible(true);
        yourRecordText.setVisible(true);
    }

    @Override
    public void onBackKeyPressed() {
        gameHUD.setVisible(false);
        camera.clearUpdateHandlers();
        SceneManager.getInstance().currentShoes = "aus";
        SceneManager.getInstance().currentChallenge = "none";
        SceneManager.getInstance().currentChallengeShoes = "";
        SceneManager.getInstance().loadMainMenuScene(engine);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_GAMING;
    }

    @Override
    public void reset() {
        super.reset();

        physicsWorld.setGravity(new Vector2(0, 0));

//        playerBody.setTransform(200 / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
//                570 / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);

        sortChildren();

        unregisterUpdateHandler(physicsWorld);
        physicsWorld.onUpdate(0);

        state = STATE.NEW;
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
        return false;
    }

    @Override
    public void beginContact(Contact contact) {
        if (BODY_SENSOR.equals(contact.getFixtureA().getBody().getUserData()) ||
                BODY_SENSOR.equals(contact.getFixtureB().getBody().getUserData())) {
            if (state.equals(STATE.PLAY)) {
//                currentScore = ballState.equals(BALL_STATE.UP) ? currentScore + 1 : currentScore;
                ballStop = ballState.equals(BALL_STATE.STOP);
//                scoreText.setText(String.valueOf(currentScore));
                jumpControl = 0;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    private static class Ball extends AnimatedSprite {
//        private final PhysicsHandler mPhysicsHandler;
        private String stateX = "r";
        private String stateY = "d";
        private int faceIndex = 0;

        public Ball(final float pX, final float pY, final ITiledTextureRegion pTextureRegion, PhysicsWorld pPhysics, VertexBufferObjectManager vbom) {
            super(pX, pY, pTextureRegion, ResourceManager.getInstance().vbom);
            Rectangle r = new Rectangle(-240f + pTextureRegion.getWidth() * 0.5f, pTextureRegion.getHeight() + 1, 480, 1, vbom);
            r.setColor(Color.RED);
            r.setAlpha(0f);
            attachChild(r);

            Body scoreSensor = PhysicsFactory.createBoxBody(pPhysics, r, BodyDef.BodyType.StaticBody, SENSOR_FIXTURE);
            scoreSensor.setUserData(BODY_SENSOR);
//            this.mPhysicsHandler = new PhysicsHandler(this);
//            this.registerUpdateHandler(this.mPhysicsHandler);
        }

        @Override
        protected void onManagedUpdate(final float pSecondsElapsed) {
/*
            if (this.mX < 0 && this.stateX.equals("l")) {
                this.stateX = "r";
                this.mPhysicsHandler.setVelocityX(50f);
                this.setCurrentTileIndex(1);
                this.faceIndex = 1;
            } else if (this.stateX.equals("r") && this.mX + this.getWidth() < MainActivity.CAMERA_WIDTH) {
                this.mPhysicsHandler.setVelocityX(50f);
                if(this.faceIndex >= 1) {
                    this.faceIndex++;
                    if(this.faceIndex == 10) {
                        this.faceIndex = 0;
                        this.setCurrentTileIndex(0);
                    }
                }
            } else if (this.mX + this.getWidth() > MainActivity.CAMERA_WIDTH) {
                this.stateX = "l";
                this.mPhysicsHandler.setVelocityX(-150f);
                this.setCurrentTileIndex(1);
                this.faceIndex = 1;
            } else {
                this.mPhysicsHandler.setVelocityX(-150f);
                if(this.faceIndex >= 1) {
                    this.faceIndex++;
                    if(this.faceIndex == 10) {
                        this.faceIndex = 0;
                        this.setCurrentTileIndex(0);
                    }
                }
            }

            if (this.mY < 0 && this.stateY.equals("u")) {
                this.stateY = "d";
                this.mPhysicsHandler.setVelocityY(50f);
                this.setCurrentTileIndex(1);
                this.faceIndex = 1;
            } else if (this.stateY.equals("d") && this.mY/* + this.getHeight()*//* < MainActivity.CAMERA_HEIGHT - 148f) {
                this.mPhysicsHandler.setVelocityY(50f);
                if(this.faceIndex >= 1) {
                    this.faceIndex++;
                    if(this.faceIndex == 10) {
                        this.faceIndex = 0;
                        this.setCurrentTileIndex(0);
                    }
                }
            } else if (this.mY/* + this.getHeight()*//* > MainActivity.CAMERA_HEIGHT - 148f) {
                this.stateY = "u";
                this.mPhysicsHandler.setVelocityY(-150f);
                this.setCurrentTileIndex(1);
                this.faceIndex = 1;
//                scoreText.setText(SCORE + "0");

            } else {
                this.mPhysicsHandler.setVelocityY(-150f);
                if(this.faceIndex >= 1) {
                    this.faceIndex++;
                    if(this.faceIndex == 10) {
                        this.faceIndex = 0;
                        this.setCurrentTileIndex(0);
                    }
                }
            }
*/
//            if (this.mX >= 650f) {
//                this.setCurrentTileIndex(1);
//            } else if (this.mX < 650) {
//                this.setCurrentTileIndex(2);
//            }
            super.onManagedUpdate(pSecondsElapsed);
        }
    }

    private static class Glove extends AnimatedSprite {
//        private final PhysicsHandler mPhysicsHandler;
        private final String LEFT = "left";
        private final String RIGHT = "right";
        private String direction = "center";
        private int directionIndex = 0;

        private String state;

        public Glove(final float pX, final float pY, final ITiledTextureRegion pTextureRegion) {
            super(pX, pY, pTextureRegion, ResourceManager.getInstance().vbom);
//            this.mPhysicsHandler = new PhysicsHandler(this);
//            this.registerUpdateHandler(new IUpdateHandler() {
//
//                @Override
//                public void onUpdate(float pSecondsElapsed) {
              /*  if (deadState.equals("dead")) {
                    player.setCurrentTileIndex(3);
                } else if (state == STATE.PLAY) {
                    if (playerBody.getLinearVelocity().y > -0.01f) {
/*                        if (jumpControl == 0) {
                            if (controler <= 5) {
//                                player.setCurrentTileIndex(0);
                                controler++;
                            } else if (controler >= 6 && controler <= 11) {
//                                player.setCurrentTileIndex(1);
                                controler++;
                            } else {
//                                player.setCurrentTileIndex(2);
                                controler++;
                                controler = controler == 17 ? 0 : controler;
                            }
                        } else {
                            player.setCurrentTileIndex(2);
                        }

                    } else {
                        player.setCurrentTileIndex(1);
                    }
                }*/
//                }
//
//                @Override
//                public void reset() { }
//            });//this.mPhysicsHandler);
//            this.state = RIGHT;
        }

        @Override
        protected void onManagedUpdate(final float pSecondsElapsed) {
/*            if(this.direction.equals("center") && this.mX < 150 && this.state.equals(LEFT)) {
                this.state = RIGHT;
                this.mPhysicsHandler.setVelocityX(200);
            } else if(this.direction.equals("center") && this.state.equals(RIGHT) && this.mX + this.getWidth() < 330) {//MainActivity.CAMERA_WIDTH) {
                this.mPhysicsHandler.setVelocityX(200);
            } else if(this.direction.equals("center") && this.mX + this.getWidth() > 330) {//MainActivity.CAMERA_WIDTH) {
                this.state = LEFT;
                this.mPhysicsHandler.setVelocityX(-300);
            } else if (this.direction.equals("left")) {
                this.mPhysicsHandler.setVelocityX(-580);
                this.directionIndex = this.directionIndex == 50 ? 0 : this.directionIndex + 1;
                this.direction = this.directionIndex == 50 ? "center" : this.direction;
            } else if (this.direction.equals("right")) {
                this.mPhysicsHandler.setVelocityX(480);
                this.directionIndex = this.directionIndex == 50 ? 0 : this.directionIndex + 1;
                this.direction = this.directionIndex == 50 ? "center" : this.direction;
            } else {
                this.mPhysicsHandler.setVelocityX(-300);
            }
*/            super.onManagedUpdate(pSecondsElapsed);
        }

        public void setDirection (String pDirection) {
            this.directionIndex = 0;
            this.direction = pDirection;
        }
    }
}
