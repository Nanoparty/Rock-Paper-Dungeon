package net.nanoriot.net.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Nathan on 1/5/2017.
 */

public class Item {

    private String name;
    private Texture image;

    private int ratk;
    private int patk;
    private int satk;

    private int rdef;
    private int pdef;
    private int sdef;

    public Item(){
        ratk = 0;
        patk = 0;
        satk = 0;

        rdef = 0;
        pdef = 0;
        sdef = 0;

    }

    public String getName(){return name;}
    public Texture getImage(){return image;}

    public int getRatk(){return ratk;}
    public int getPatk() {return patk;}
    public int getSatk(){return satk;}
    public int getRdef(){return rdef;}
    public int getPdef(){return pdef;}
    public int getSdef(){return sdef;}
}
