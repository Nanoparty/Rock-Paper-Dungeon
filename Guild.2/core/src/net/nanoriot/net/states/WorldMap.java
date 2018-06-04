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

import net.nanoriot.net.entities.Location;
import net.nanoriot.net.entities.Pin;
import net.nanoriot.net.handler.Content;
import net.nanoriot.net.handler.GameStateManager;
import net.nanoriot.net.handler.MyInput;

import java.util.ArrayList;


public class WorldMap extends GameState{

    private SpriteBatch batch;
    private ShapeRenderer sr;
    private BitmapFont font,font2;

    private int hover;

    private ArrayList<Location> locations;
    private ArrayList<Pin> pins;

    //comment
    public WorldMap(GameStateManager gsm) {
        super(gsm);

        //primitives
        hover = 0;

        //objects
        batch = new SpriteBatch();
        sr = new ShapeRenderer();

        //locations
        locations = new ArrayList<Location>();
        locations.add(new Location(100,100,"Castle","A","Plains"));

        pins = new ArrayList<Pin>();
        for(int i = 0;i< locations.size();i++){
            pins.add(new Pin(locations.get(i).getX(),locations.get(i).getY(),locations.get(i)));

        }

        //Font Factory
        //////////////////////////////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fipps-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 100;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.WHITE);

        parameter.size = 25;
        font2 = generator.generateFont(parameter);
        font2.setColor(Color.WHITE);

    }

    @Override
    public void handleInput() {

        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();

        //button hover
        if(MyInput.isDown(MyInput.LEFTM)) {


        }

        //button action
        if(MyInput.isReleased(MyInput.LEFTM)){

        }

    }



    @Override
    public void update(float dt) {

        handleInput();

        //locations
        for(int i = 0; i < pins.size();i++) {
            pins.get(i).update();
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
        sr.rect(0,0, Game.V_WIDTH,Game.V_HEIGHT);
        sr.end();

        //hover animation
        sr.begin(ShapeType.Filled);
        sr.setColor(Color.WHITE);
        switch(hover){

        }
        sr.end();

        batch.begin();


        //draw title
        batch.draw(Content.MAP,0,0);

        batch.end();

        for(Pin p: pins) {
            p.draw(batch);
        }


    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
