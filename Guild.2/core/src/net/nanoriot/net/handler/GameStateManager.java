package net.nanoriot.net.handler;

import java.util.Stack;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

import net.nanoriot.net.Game;
import net.nanoriot.net.states.CharCreate;
import net.nanoriot.net.states.CharSelect;
import net.nanoriot.net.states.Dungeon;
import net.nanoriot.net.states.Exp;
import net.nanoriot.net.states.GameState;
import net.nanoriot.net.states.MMenu;
import net.nanoriot.net.states.Stats;
import net.nanoriot.net.states.WorldMap;


public class GameStateManager {

    public static int tick = 0;
    //player = 0
    //mobs = 1;

    private Game game;

    private Stack<GameState> gameStates;

    public static final int SLOT = 0;
    public static final int MENU = 1;
    public static final int CREATE = 2;
    public static final int WMAP = 3;
    public static final int DUN = 4;
    public static final int EXP = 5;
    public static final int STATS = 6;

    public Music music;


    public GameStateManager(Game game) {
        this.game = game;

        gameStates = new Stack<GameState>();
        pushState(DUN);

        Preferences prefs = Gdx.app.getPreferences("HighScores");


    }

    public Game game() { return game; }

    public void update(float dt) {
        gameStates.peek().update(dt);
        if(tick == 2){
            tick = 0;
        }

    }



    public void render() {
        gameStates.peek().render();
    }

    private GameState getState(int state) {


        if(state == MENU)return new MMenu(this);
        if(state == SLOT)return new CharSelect(this);
        if(state == CREATE)return new CharCreate(this);
        if(state == WMAP)return new WorldMap(this);
        if(state == DUN)return new Dungeon(this);
        if(state == EXP)return new Exp(this);
        if(state == STATS)return new Stats(this);

        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

    public void setMusic(boolean b){
        if(b){
            music.setLooping(true);
            music.play();
        }else{
            music.pause();
        }
    }



}















