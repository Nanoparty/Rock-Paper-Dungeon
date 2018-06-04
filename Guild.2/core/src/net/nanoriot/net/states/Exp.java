package net.nanoriot.net.states;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.nanoriot.net.Game;
import net.nanoriot.net.handler.Content;
import net.nanoriot.net.handler.GameData;
import net.nanoriot.net.handler.GameStateManager;
import net.nanoriot.net.handler.MyInput;


public class Exp extends GameState{

    private SpriteBatch batch;
    private ShapeRenderer sr;
    private BitmapFont font,font2,font3,font4;

    private int expPoints;
    private int lvl;
    private int exp;
    private int lvlCost;
    private int nExp;
    private int coins;
    private int kills;

    private int barLength;
    private int barFill;
    private int fill;
    private int maxFill;

    private double initTime;
    private double delay;

    private boolean done;

    private int slidey;
    private int introy;
    private boolean slideIn;
    private boolean slideOut;


    //comment
    public Exp(GameStateManager gsm) {
        super(gsm);

        //primitives
        lvl = GameData.getPlayerLvl();
        lvlCost = GameData.getLvlCost();
        exp = GameData.getExp();
        nExp = exp;
        coins = GameData.getNewCoins();
        kills = GameData.getKills();

        expPoints = GameData.getExpPoints();
        done = false;

        delay = 0.005;
        initTime = System.nanoTime() + (delay)*1000000000;

        introy = -400;
        slidey = 0;
        slideIn = true;
        slideOut = false;



        fill = GameData.getExpFill();

        barLength = 350;
        barFill =(int)(barLength * (float)fill/lvlCost);

        //objects
        batch = new SpriteBatch();
        sr = new ShapeRenderer();

        //Font Factory
        //////////////////////////////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("alagard.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 90;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.BLACK);

        parameter.size = 40;
        font2 = generator.generateFont(parameter);
        font2.setColor(Color.BLACK);

        parameter.size = 60;
        font3 = generator.generateFont(parameter);
        font3.setColor(Color.BLACK);

        parameter.size = 60;
        font4 = generator.generateFont(parameter);
        font4.setColor(Color.BLACK);

    }

    @Override
    public void handleInput() {

        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();

        //button hover
        if(MyInput.isReleased(MyInput.LEFTM) && done) {
            GameData.setFill(fill);
            GameData.setExp(exp);
            GameData.setLvlCost(lvlCost);
            GameData.setExpPoints(expPoints);
            GameData.setPlayerLvl(lvl);
            slideOut = true;

        }



    }



    @Override
    public void update(float dt) {

        if(slideIn){
            introy += 30;
            if(introy > Game.V_HEIGHT){
                slideIn = false;
            }
        }else if(slideOut){
            slidey += 30;
            if(slidey >= Game.V_HEIGHT){
                //slidey = 0;
                slideOut = false;
                gsm.setState(GameStateManager.STATS);
            }
        }else {
            handleInput();
            if (exp <= 0) {
                done = true;
            }
            if (!done) {
                if (initTime < System.nanoTime()) {
                    fill += 1;
                    exp -= 1;
                    initTime = System.nanoTime() + (delay) * 1000000000;
                }
                if (fill >= lvlCost) {
                    expPoints += 1;
                    lvl += 1;
                    lvlCost *= 1.2;
                    fill = 0;
                }

            }
            barFill = (int) (barLength * (float) fill / lvlCost);
            System.out.println(fill + ":" + lvlCost + ":" + barFill);
        }
    }

    @Override
    public void render() {
        Gdx.graphics.getGL20().glClearColor(1, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        sr.setProjectionMatrix(cam.combined);

        sr.begin(ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(0,0,Game.V_WIDTH,Game.V_HEIGHT);
        sr.end();

        batch.begin();
        batch.draw(Content.BACKGROUND,0,0);
        batch.draw(Content.RESULTS,0,0 + slidey);
        batch.end();

        sr.begin(ShapeType.Filled);
        sr.setColor(Color.LIME);
        sr.rect(170,520 + slidey,barFill,50);
        sr.end();

        sr.begin(ShapeType.Line);
        sr.setColor(Color.WHITE);
        sr.rect(170,520 + slidey,barLength,50);
        sr.end();



        batch.begin();
        //font3.draw(batch,"Quest Results",10,1250);
        font4.draw(batch,""+kills,450, 975 + slidey);
        font4.draw(batch,"" + coins,450,865 + slidey);
        font4.draw(batch,"" + nExp,450,760 + slidey);

        font.draw(batch,""+ lvl,400,672 + slidey);
        font2.draw(batch,"" + exp, 275, 473 + slidey);
        font2.draw(batch,"" + expPoints, 440, 400 + slidey);

        if(done){
            //font2.draw(batch,"Continue",220,100);
        }
        batch.end();


        if(slideIn){
            batch.begin();

            //background
            batch.draw(Content.SLIDEU,0,introy);
            //tick1 = true;
            batch.end();
        }

    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
