package net.nanoriot.net.entities;

/**
 * Created by Nathan on 12/21/2016.
 */

public class Location {

    private String name;
    private String difficulty;
    private String region;

    private int x,y;

    public Location(int x, int y,String n, String d, String r) {
        name = n;
        difficulty = d;
        region = r;

        this.x = x;
        this.y = y;
    }

    public String getName(){
        return name;
    }
    public String getDif(){
        return difficulty;
    }
    public String getRegion(){
        return region;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
