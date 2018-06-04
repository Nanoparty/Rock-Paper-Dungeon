package net.nanoriot.net.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.nanoriot.net.handler.Content;
import net.nanoriot.net.handler.GameData;

import java.util.Random;


/**
 * Created by Nathan on 1/3/2017.
 */

public class Hero {

    private int health;
    private int mhealth;

    private String name;

    private int str;
    private int agl;
    private int dex;

    private int swd;
    private int arm;
    private int shld;

    private int lvl;

    private Random ran;


    private Texture image;

    private int x;
    private int y;

    public  Hero() {

        image = Content.HERO;

        ran = new Random();

        name = GameData.getName();

        x = 100;
        y = 800;

        mhealth = (GameData.getVit()*10);
        health = mhealth;

        str = GameData.getStr();
        dex = GameData.getDex();
        agl = GameData.getAgl();
        swd = GameData.getSwd();
        arm = GameData.getArm();
        shld = GameData.getShld();

    }

    public void update() {


    }

    public void render(SpriteBatch batch) {

        batch.begin();
        batch.draw(image,x,y);
        batch.end();
    }

    public int getHP(){return health;}
    public int getMaxHP(){return mhealth;}
    public int getSTR(){return str;}
    public int getLvl() {return lvl;}
    public int getAGL(){return agl;}
    public int getDEX(){return dex;}
    public String getName(){return name;}

    public Texture getImage(){return image;}

    public int getX(){return x;}
    public int getY(){return y;}

    public boolean setDamage(String s, int i) {
        health-=i;
        return false;
    }
    public int getDamage(String s) {
        if (s.equals("rock")) {
            return str+swd;
        }
        if (s.equals("paper")) {
            return dex+swd;
        }
        if (s.equals("scissor")) {
            return agl+swd;
        }
        return 0;
    }
}
