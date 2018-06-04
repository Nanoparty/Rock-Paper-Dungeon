package net.nanoriot.net.handler;

import com.badlogic.gdx.Gdx;

import net.nanoriot.net.Game;


public class MyInput {

    public static boolean[] keys;
    public static boolean[] pkeys;

    private static double scaleX;
    private static double scaleY;

    public static final int NUM_KEYS = 7;
    public static final int BUTTON1 = 0;
    public static final int BUTTON2 = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int UP = 4;
    public static final int DOWN = 5;
    public static final int LEFTM = 6;

    private static int x;
    private static int y;

    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
        scaleX = Gdx.graphics.getWidth()/ Game.V_WIDTH;;
        scaleY = Math.abs((double)Gdx.graphics.getHeight()/ Game.V_HEIGHT);
    }

    public static void update() {
        for(int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }

    }

    public static void setKey(int i, boolean b) { keys[i] = b; }
    public static boolean isDown(int i) { return keys[i]; }
    public static boolean isPressed(int i) { return keys[i] && !pkeys[i]; }
    public static boolean isReleased(int i){ return !keys[i] && pkeys[i];}

    public static void setTouchPosition(int xt,int yt){
        x = xt;
        y = yt;
    }
    public static int getTouchX(){
        //System.out.println("realX:" + x +   "realY:" + y);
        return Math.abs((int)(x/scaleX));

    }
    public static int getTouchY(){
        return Math.abs((int)(y/scaleY));
    }

}
















