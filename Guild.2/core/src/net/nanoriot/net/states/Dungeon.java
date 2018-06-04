package net.nanoriot.net.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;

import net.nanoriot.net.Game;
import net.nanoriot.net.entities.Enemy;
import net.nanoriot.net.entities.Hero;
import net.nanoriot.net.entities.Location;
import net.nanoriot.net.handler.Content;
import net.nanoriot.net.handler.GameData;
import net.nanoriot.net.handler.GameStateManager;
import net.nanoriot.net.handler.MyInput;
import net.nanoriot.net.menus.HUD;


/**
 * Created by Nathan on 12/25/2016.
 */

public class Dungeon extends GameState {

    private SpriteBatch batch;
    private ShapeRenderer sr;

    private Hero h;
    private Queue<Enemy> enemies;

    private Enemy curEnemy;

    private HUD hud;

    private Texture rButton;
    private Texture pButton;
    private Texture sButton;

    private Texture back;

    private Texture border;

    private Location loc;

    private BitmapFont font,font2,font3,font4;

    private String pChoice;
    private String eChoice;
    private String result;

    private boolean countdown;
    private boolean fight;
    private boolean canAttack;
    private boolean matchOver;
    private boolean transition;
    private boolean win;
    private boolean moveDelay;
    private boolean intro;
    private boolean tick1;

    private int bHover;

    private int count;

    private int delay;
    private int curTime;

    private int introy;

    private int coinTotal;
    private int expTotal;
    private int monstersKilled;
    private int floor;

    private boolean canClick;

    public Dungeon(GameStateManager gsm) {
        super(gsm);

        curEnemy = new Enemy(1);
        h = new Hero();
        hud = new HUD(h, curEnemy,font);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();

        rButton = Content.RBUTTON;
        pButton = Content.PBUTTON;
        sButton = Content.SBUTTON;
        back = Content.BACK;

        this.loc = GameData.getLocation();

        border = Content.BORDER;

        //hud.setNames("Knight", curEnemy.getName());

        pChoice = "";
        eChoice = "";
        result = "";

        bHover = -1;

        countdown = false;
        fight = false;
        matchOver = false;
        transition = false;
        win = false;
        moveDelay = false;
        intro = true;
        tick1 = false;

        count = 3;
        delay = 2;
        curTime = 0;

        introy = -400;

        monstersKilled = 0;
        floor = 1;

        canClick = true;
        canAttack = true;

        //Font Factory
        //////////////////////////////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("alagard.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 40;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.WHITE);

        parameter.size = 100;
        font2 = generator.generateFont(parameter);
        font2.setColor(Color.BLUE);

        parameter.size = 100;
        font3 = generator.generateFont(parameter);
        font3.setColor(Color.RED);

        parameter.size = 80;
        font4 = generator.generateFont(parameter);
        font4.setColor(Color.WHITE);

        /////////////////////////////////////////////

        hud.setFont(font);

    }

    public void handleInput() {

        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();

        if (MyInput.isPressed(MyInput.LEFTM)) {
            //rock
            if (mx > 40 && mx < 240 && my > 60 && my < 140) {
                bHover = 1;
            }
            //paper
            if (mx > 260 && mx < 460 && my > 60 && my < 140) {
                bHover = 2;
            }
            //scissor
            if (mx > 480 && mx < 680 && my > 60 && my < 140) {
                bHover = 3;
            }
        }
        if (MyInput.isReleased(MyInput.LEFTM)) {
            //rock
            if (mx > 40 && mx < 240 && my > 60 && my < 140) {
                bHover = -1;
            }
            //paper
            if (mx > 260 && mx < 460 && my > 60 && my < 140) {
                bHover = -1;
            }
            //scissor
            if (mx > 480 && mx < 680 && my > 60 && my < 140) {
                bHover = -1;
            }
        }
        if(!matchOver) {


            if (MyInput.isReleased(MyInput.LEFTM) && canClick) {
                //rock
                if (mx > 40 && mx < 240 && my > 60 && my < 140) {
                    startFight("rock");
                }
                //paper
                if (mx > 260 && mx < 460 && my > 60 && my < 140) {
                    startFight("paper");
                }
                //scissor
                if (mx > 480 && mx < 680 && my > 60 && my < 140) {
                    startFight("scissor");
                }
            }
        }else if(!transition){
            if (MyInput.isReleased(MyInput.LEFTM)){
                transition = true;
            }
        } else{

            if(win) {
                nextMatch();
            }else{
                goHome();
            }
        }
    }

    public void update(float t) {
        if(intro){
            introy += 30;
            if(introy > Game.V_HEIGHT){
                intro = false;
            }
        }else {
            handleInput();
            hud.update(h.getHP(), curEnemy.getHP());
        }

    }

    public void render() {
        Gdx.graphics.getGL20().glClearColor(1, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        sr.setProjectionMatrix(cam.combined);



        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(0,0, Game.V_WIDTH,Game.V_HEIGHT);
        sr.end();


        if(tick1) {
            batch.begin();

            //background
            batch.draw(Content.DUNGEON, 0, 0);

            //batch.draw(back, 30, 1150);
            font4.setColor(Color.PURPLE);
            font4.draw(batch, "Floor " + floor, 250, 1230);

            //buttons
            if(bHover == 1)
                batch.draw(Content.RBUTTOND, 40, 60);
            else
                batch.draw(Content.RBUTTON, 40, 60);

            if(bHover == 2)
                batch.draw(Content.PBUTTOND, 260, 60);
            else
                batch.draw(Content.PBUTTON, 260, 60);
            if(bHover == 3)
                batch.draw(Content.SBUTTOND, 480, 60);
            else
                batch.draw(Content.SBUTTON, 480, 60);
            batch.end();

            h.render(batch);
            curEnemy.render(batch);
            hud.render(batch, sr, font);
            renderFight();
        }

        if(intro){
            batch.begin();

            //background
            batch.draw(Content.SLIDEU,0,introy);
            tick1 = true;
            batch.end();
        }
    }

    private void startFight(String s) {
        canClick = false;
        pChoice = s;
        eChoice = curEnemy.getChoice();
        countdown = true;

    }

    private void renderFight() {

        if(countdown){


            fight = true;
            canAttack = true;



        }

        if(fight) {
            if (pChoice.equals("rock")) {

                if (eChoice.equals("rock")){
                    result = "DRAW";
                }
                if (eChoice.equals("paper")){
                    result = "LOSE";
                }
                if (eChoice.equals("scissor")){
                    result = "WIN";
                }

            } else if (pChoice.equals("paper")) {

                if (eChoice.equals("rock")){
                    result = "WIN";
                }
                if (eChoice.equals("paper")){
                    result = "DRAW";
                }
                if (eChoice.equals("scissor")){
                    result = "LOSE";
                }

            } else if (pChoice.equals("scissor")) {

                if (eChoice.equals("rock")){
                    result = "LOSE";
                }
                if (eChoice.equals("paper")){
                    result = "WIN";
                }
                if (eChoice.equals("scissor")){
                    result = "DRAW";
                }

            }
            if(canAttack) {
                if (result.equals("WIN") && canAttack) {
                    curEnemy.setDamage(pChoice, h.getDamage(pChoice));
                    System.out.println("health:"+curEnemy.getHP() + "   damange:" + h.getDamage(pChoice));
                    canAttack = false;
                    //hud.addPost("ATTACK",0);
                    hud.addPost(h.getName(), h.getDamage(pChoice), Color.CYAN,1);
                } else if (result.equals("LOSE") && canAttack) {
                    h.setDamage(eChoice, curEnemy.getDamage(eChoice));
                    canAttack = false;
                    hud.addPost(curEnemy.getName(), curEnemy.getDamage(eChoice), Color.SCARLET,1);
                } else if (result.equals("DRAW") && canAttack) {
                    //nothing happens
                    canAttack=false;
                    hud.addPost("Draw!", Color.PURPLE,2);
                }

            }

            batch.begin();
            font.draw(batch,result,315,1000);

            font.draw(batch,pChoice,180,900);
            font.draw(batch,eChoice,450,900);





            canClick = true;
            countdown = false;


            if(h.getHP() <= 0){
                font3.draw(batch,"DEFEAT",100,1000);
                if(!matchOver){
                    hud.addPost("You Lost!",Color.SALMON,2);
                }
                matchOver = true;
                win = false;

            }
            else if(curEnemy.getHP() <= 0){
                if(!matchOver){
                    hud.addPost("You Won!",Color.ROYAL,2);
                }
                matchOver = true;
                win = true;
                font2.draw(batch,"Victory",60,1000);


            }
            font.setColor(Color.WHITE);
            batch.end();
        }

    }





    private void nextMatch(){
        hud.addPost(" gold coins! ",curEnemy.getCoinDrop(), Color.GOLD,3);
        hud.addPost(" experience! ",curEnemy.getExpDrop(), Color.LIME,3);

        coinTotal += curEnemy.getCoinDrop();
        expTotal += curEnemy.getExpDrop();
        monstersKilled++;
        floor++;
        curEnemy = new Enemy(floor);
        hud.setEnemy(curEnemy);
        pChoice = "";
        eChoice = "";
        result = "";

        countdown = false;
        fight = false;
        matchOver = false;
        count = 3;
        delay = 2;
        curTime = 0;

        canClick = true;
        canAttack = true;
        transition = false;
        win = false;


    }

    private void goHome(){
        GameData.addCoins(coinTotal);
        GameData.addExp(expTotal);
        GameData.setKills(monstersKilled);
        gsm.setState(GameStateManager.EXP);
    }

    public void dispose() {

    }
}
