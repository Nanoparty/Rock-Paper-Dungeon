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


public class MMenu extends GameState{

    private SpriteBatch batch;
    private ShapeRenderer sr;
    private BitmapFont font,font2;

    private int hover;
    private int y;

    private boolean held;
    private int pos;
    boolean slide;
    int mousey;


    private String[] buttons = {"  Begin" , "  Stats" , "Settings"};
    //comment
    public MMenu(GameStateManager gsm) {
        super(gsm);

        GameData.load();

        //primitives
        y = -Content.SLIDE.getHeight()+400;
        held = false;
        pos = 0;
        slide = false;
        mousey = 0;


        //objects
        batch = new SpriteBatch();
        sr = new ShapeRenderer();

        //Font Factory
        //////////////////////////////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fipps-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 75;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.WHITE);

        parameter.size = 50;
        font2 = generator.generateFont(parameter);
        font2.setColor(Color.WHITE);

    }

    @Override
    public void handleInput() {

        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();
        System.out.println(mx + ":" + my);
        mousey = my;


        //button hover
        if(MyInput.isDown(MyInput.LEFTM)) {
            if(!held){
                held=true;
                pos = my;
            }
        }

        //button action
        if(MyInput.isReleased(MyInput.LEFTM)){
           if(held){
               held=false;
           }
        }

        if(held){
            if(my - pos > 100){
                slide = true;
            }
        }

    }



    @Override
    public void update(float dt) {

        handleInput();

        if(slide){
            y+=60;
        }
        if(y >= 0){
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
        batch.draw(Content.FORESTMENU,0,0);
        if(!held) {
            batch.draw(Content.SLIDE, 0, y);
        }else if(held && !slide){
            batch.draw(Content.SLIDE, 0, y + mousey - pos);
        }else{
            batch.draw(Content.SLIDE, 0, y);
        }
        batch.end();






    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
