package net.nanoriot.net.handler;


import net.nanoriot.net.entities.Location;

/**
 * Created by Nathan on 1/7/2017.
 */

public class GameData {

    private static Location loc;
    private static int exp=0;
    private static int expPoints=0;
    private static int coins=0;
    private static int newCoins=0;
    private static int playerLvl=1;
    private static int expFill=0;
    private static int vit=10;
    private static int str=5;
    private static int dex=5;
    private static int agl=5;
    private static int swd=15;
    private static int arm=0;
    private static int shd=0;
    private static int kills=0;
    private static String name = "Hero";
    private static int armCost=10;
    private static int swdCost=10;
    private static int shldCost=10;
    private static int lvlCost=100;
    private static int curSlot=0;

    public static void setName(String s) {name = s;}
    public static void setLocation(Location loc) {loc = loc;}
    public static void setPlayerLvl(int i) {playerLvl = i;}
    public static void addExp(int i) {exp += i;}
    public static void setExp(int i) {exp = i;}
    public static void setExpPoints(int i) {expPoints = i;}
    public static void setCoins(int i) {coins = i;}
    public static void addCoins(int i) {coins += i;}
    public static void setFill(int i) {expFill = i;}
    public static void setVit(int i) {vit = i;}
    public static void setStr(int i) {str = i;}
    public static void setDex(int i) {dex = i;}
    public static void setAgl(int i) {agl = i;}
    public static void setSwd(int i) {swd = i;}
    public static void setArm(int i) {arm = i;}
    public static void setShd(int i) {shd = i;}
    public static void setKills(int i) {kills = i;}
    public static void setSwdCost(int i) {swdCost = i;}
    public static void setArmCost(int i) {armCost = i;}
    public static void setShldCost(int i) {shldCost = i;}
    public static void setLvlCost(int i) {lvlCost = i;}
    public static void setCurSlot(int i) {curSlot = i;}


    public static Location getLocation() {
        return loc;
    }
    public static int getExp() {return exp;}
    public static int getExpPoints() {return expPoints;}
    public static int getCoins() {return coins;}
    public static int getNewCoins() {return coins;}
    public static int getPlayerLvl() {return playerLvl;}
    public static int getExpFill() {return expFill;}
    public static int getVit() {return vit;}
    public static int getStr() {return str;}
    public static int getDex() {return dex;}
    public static int getAgl() {return agl;}
    public static int getSwd() {return swd;}
    public static int getArm() {return arm;}
    public static int getShld() {return shd;}
    public static int getKills() {return kills;}
    public static String getName() {return name;}
    public static int getSwdCost() {return swdCost;}
    public static int getArmCost() {return armCost;}
    public static int getShldCost() {return shldCost;}
    public static int getLvlCost() {return lvlCost;}
    public static int getCurSlot() {return curSlot;}

    public static void save() {

    }

    public static void load() {

    }

    public static void refresh(int vit, int str, int dex, int agl, int swd, int arm, int shld, int expPoints, int coins) {
        GameData.setVit(vit);
        GameData.setStr(str);
        GameData.setAgl(agl);
        GameData.setDex(dex);
        GameData.setSwd(swd);
        GameData.setArm(arm);
        GameData.setShd(shld);
        GameData.setExpPoints(expPoints);
        GameData.setCoins(coins);
    }
}
