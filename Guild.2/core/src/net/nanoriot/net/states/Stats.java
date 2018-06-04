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

import static net.nanoriot.net.handler.Content.BTNUP;
import static net.nanoriot.net.handler.Content.BTNDOWN;

public class Stats extends GameState{

    private SpriteBatch batch;
    private ShapeRenderer sr;
    private BitmapFont font,font2,font3;

    private Texture button = Content.PLUS;

    private int expPoints;
    private int lvl;
    private int exp;
    private int coins;

    private int swdCost;
    private int armCost;
    private int shldCost;

    private int vit,str,agl,dex,swd,arm,shld;

    private int fill;
    private int maxFill;

    private int clicked;

    private int blackY;
    private int slideY;
    private boolean slideIn;
    private boolean slideOut;

    //comment
    public Stats(GameStateManager gsm) {
        super(gsm);

        //primitives
        lvl = GameData.getPlayerLvl();
        exp = GameData.getExp();
        expPoints = GameData.getExpPoints();

        vit = GameData.getVit();
        str = GameData.getStr();
        dex = GameData.getDex();
        agl = GameData.getAgl();
        swd = GameData.getSwd();
        arm = GameData.getArm();
        shld = GameData.getShld();
        swdCost = GameData.getSwdCost();
        armCost = GameData.getArmCost();
        shldCost = GameData.getShldCost();
        coins = GameData.getCoins();


        clicked = -1;
        slideY = -Game.V_HEIGHT;

        slideIn = true;
        slideOut = false;
        blackY = -Content.SLIDE.getHeight()+400;


        maxFill = 520;
        fill = GameData.getExpFill();

        //objects
        batch = new SpriteBatch();
        sr = new ShapeRenderer();

        //Font Factory
        //////////////////////////////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("alagard.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 53;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.BLACK);

        parameter.size = 40;
        font2 = generator.generateFont(parameter);
        font2.setColor(Color.BLACK);

        parameter.size = 30;
        font3 = generator.generateFont(parameter);
        font3.setColor(Color.BLACK);

    }

    @Override
    public void handleInput() {

        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();

        if(MyInput.isDown(MyInput.LEFTM)){
            if (mx > 140 && mx < 160 && my > 940 && my < 960){
                clicked = 1;
            }
            if (mx > 140 && mx < 160 && my > 860 && my < 880){
                clicked = 2;
            }
            if (mx > 140 && mx < 160 && my > 790 && my < 810){
                clicked = 3;
            }
            if (mx > 140 && mx < 160 && my > 715 && my < 735){
                clicked = 4;
            }
            if (mx > 140 && mx < 160 && my > 630 && my < 650){
                clicked = 5;
            }
            if (mx > 140 && mx < 160 && my > 550 && my < 570){
                clicked = 6;
            }
        }

        if(MyInput.isReleased(MyInput.LEFTM )){

            if (mx > 140 && mx < 160 && my > 940 && my < 960){
                clicked = -1;
            }
            if (mx > 140 && mx < 160 && my > 860 && my < 880){
                clicked = -1;
            }
            if (mx > 140 && mx < 160 && my > 790 && my < 810){
                clicked = -1;
            }
            if (mx > 140 && mx < 160 && my > 715 && my < 735){
                clicked = -1;
            }
            if (mx > 140 && mx < 160 && my > 630 && my < 650){
                clicked = -1;
            }
            if (mx > 140 && mx < 160 && my > 550 && my < 570){
                clicked = -1;
            }

            if(expPoints > 0) {
                if (mx > 140 && mx < 160 && my > 940 && my < 960) {
                    vit++;
                    expPoints--;
                    clicked = -1;
                }
                if (mx > 140 && mx < 160 && my > 860 && my < 880) {
                    str++;
                    expPoints--;
                }
                if (mx > 140 && mx < 160 && my > 790 && my < 810) {
                    dex++;
                    expPoints--;
                }
                if (mx > 140 && mx < 160 && my > 715 && my < 735) {
                    agl++;
                    expPoints--;
                }
            }
            if(mx > 140 && mx < 160 && my > 630 && my < 650){
                if(coins >= swdCost) {
                    swd++;
                    coins -= swdCost;
                    swdCost += 10;
                }
            }
            if(mx > 140 && mx < 160 && my > 550 && my < 570){
                if(coins >= armCost) {
                    arm++;
                    coins -= armCost;
                    armCost *= 1.4;
                }
            }
            if(mx > -25 && mx < 135 && my > 150 && my < 310){
                if(coins >= shldCost) {
                    shld++;
                    coins -= shldCost;
                    shldCost *= 1.5;
                }
            }
            if(my < 300 && my > 200) {
                GameData.setVit(vit);
                GameData.setStr(str);
                GameData.setAgl(agl);
                GameData.setDex(dex);
                GameData.setSwd(swd);
                GameData.setArm(arm);
                GameData.setShd(shld);
                GameData.setExpPoints(expPoints);
                GameData.setCoins(coins);
                GameData.setSwdCost(swdCost);
                GameData.setArmCost(armCost);
                GameData.setShldCost(shldCost);
                //gsm.setState(GameStateManager.DUN);
                slideOut = true;
            }

        }



    }



    @Override
    public void update(float dt) {
        if(!slideIn && !slideOut)
            handleInput();

        if(slideIn){
            slideY+=30;
            if(slideY >= 0){
                slideY = 0;
                slideIn = false;
            }
        }

        if(slideOut){
            blackY+=60;
        }
        if(blackY >= 0){
            gsm.setState(GameStateManager.DUN);
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
        batch.draw(Content.CHARACTER,0,0 +slideY);
        //font.draw(batch,"Character Stats",10,1250);
        font3.draw(batch,"" + expPoints,335,1018 +slideY);
        font3.draw(batch,"" + coins,490,1023 +slideY);
        font2.draw(batch,""+ vit,330, 960 +slideY);
        font3.draw(batch,""+(vit*10)+">"+((vit+1)*10),430,916 +slideY);
        font2.draw(batch,""+ str, 360, 880 +slideY);
        font3.draw(batch,""+(str*10)+">"+((str+1)*10),400,845 +slideY);
        font2.draw(batch,""+ dex, 370, 810 +slideY);
        font3.draw(batch,""+(dex*10)+">"+((dex+1)*10),410,773 +slideY);
        font2.draw(batch,"" + agl, 315,735 +slideY);
        font3.draw(batch,""+(agl*10)+">"+((agl+1)*10),430,688 +slideY);
        font2.draw(batch,"" + swd, 310,650 +slideY);
        font3.draw(batch,swdCost + " coins",380,650 +slideY);
        font3.draw(batch,""+(swd*5)+">"+((swd+1)*5),393,605 +slideY);
        font2.draw(batch,""+ arm, 315,570 +slideY);
        font3.draw(batch,armCost + " coins",380,570 +slideY);
        font3.draw(batch,""+(arm*2)+">"+((arm+1)*2) ,458,527 +slideY);
        //font2.draw(batch,"Shield: " + shld, 100,250);
        //font3.draw(batch,"Block Chance "+ shld +">"+(shld+1) + " $"+shldCost,100,175);
        if(clicked == 1)
            batch.draw(BTNDOWN,140,940 );
        else
            batch.draw(BTNUP,140,940 +slideY);

        if(clicked == 2)
            batch.draw(BTNDOWN,140,860);
        else
            batch.draw(BTNUP,140,860 +slideY);

        if(clicked == 3)
            batch.draw(BTNDOWN,140,790);
        else
            batch.draw(BTNUP,140,790 +slideY);
        if(clicked == 4)
            batch.draw(BTNDOWN,140,715);
        else
            batch.draw(BTNUP,140,715 +slideY);
        if(clicked == 5)
            batch.draw(BTNDOWN,140,630);
        else
            batch.draw(BTNUP,140,630 +slideY);
        if(clicked == 6)
            batch.draw(BTNDOWN,140,550);
        else
            batch.draw(BTNUP,140,550 +slideY);
        //batch.draw(button,-25,150);

        if(slideOut){
            batch.draw(Content.SLIDE, 0, blackY);
        }

        //font2.draw(batch,"Continue",230,80);
        batch.end();




    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
