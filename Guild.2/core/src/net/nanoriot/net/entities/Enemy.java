package net.nanoriot.net.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.nanoriot.net.handler.Content;

import java.util.Random;


/**
 * Created by Nathan on 1/3/2017.
 */

public class Enemy {

    protected int health;
    protected int mhealth;
    protected String name;

    protected String choice;

    protected int lvl;

    protected int coinDrop;
    protected int expDrop;

    protected int str;
    protected int dex;
    protected int agl;
    protected int shld;
    protected int arm;

    protected Random ran;

    protected int def;

    protected Texture image;

    protected int x;
    protected int y;

    protected int floor;

    protected Item drop;

    public  Enemy(int floor){

        ran = new Random();

        double r = Math.random();
        if(r < .2){
            image = Content.BANDIT;
            name = "Bandit";
        }else if(r < .4){
            image = Content.GHOST;
            name = "Ghost";
        }else if(r < .6){
            image = Content.SLIME;
            name = "Slime";
        }else if(r < .75){
            image = Content.WRAITH;
            name = "Wraith";
        }else if(r < .9){
            image = Content.SLIMEMAN;
            name = "Slime Man";
        }else{
            image = Content.WIZARD;
            name = "Wizard";
        }

        scaleStats(floor);


        x = 500;
        y = 800;

        lvl = 1;

        //setChoice();


    }

    public void update() {


    }
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(image,x,y);
        batch.end();
    }

    private void scaleStats(int floor){
        lvl = 1 + (floor-1)*2;

        health = 100 + (lvl-1)*10;
        mhealth = 100 + (lvl-1)*10;
        coinDrop = 10 + (lvl-1)*10;
        expDrop = 20 + (lvl-1)*20;

        str = (int)(5 + (lvl-1));
        dex = (int)(5 + (lvl-1));
        agl = (int)(5 + (lvl-1));

        shld = (int)(1 + (lvl-1)*0.5);
        arm = (int)(1 + (lvl-1)*0.5);

        if(arm > 75){
            arm = 75;
        }
        if(shld > 50){
            shld = 50;
        }
    }

    public int getHP() {return health;}
    public int getMaxHP(){return mhealth;}

    public String getName(){return name;}

    public int getStr(){return str;}
    public int getDex(){return dex;}
    public int getAgl(){return agl;}



    public int getLvl(){return lvl;}

    public int getCoinDrop() {return coinDrop;}
    public int getExpDrop() {return expDrop;}

    public Texture getImage(){return image;}

    public boolean setDamage(String s, int i) {
        int damage = (i)*((100-arm)/100);
        System.out.println("Arm:" + arm);
        health-=i;
        return false;

    }
    public int getDamage(String s) {
        if (s.equals("rock")) {
            return str;
        }
        if (s.equals("paper")) {
            return dex;
        }
        if (s.equals("scissor")) {
            return agl;
        }
        return 0;
    }


    public int getX(){return x;}
    public int getY(){return y;}

    public String getChoice() {
        String c = choice;
        c = setChoice();
        return c;
    }

    public String setChoice() {
        double i = Math.random();
        if(i < .3)choice = "rock";
        else if(i < .6)choice = "paper";
        else choice = "scissor";
        return choice;
    }
}
