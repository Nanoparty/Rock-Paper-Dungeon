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


public class CharSelect extends GameState{

    private SpriteBatch batch;
    private ShapeRenderer sr;
    private BitmapFont font,font2;
    private Texture bg;

    private boolean slot1,slot2,slot3;

    private String name1 = "Empty";
    private String name2 = "Empty";
    private String name3 = "Empty";


    private int hover;

    private String[] buttons = {name1 , name2 , name3};
    //comment
    public CharSelect(GameStateManager gsm) {
        super(gsm);

        //primitives
        hover = 0;

        //objects
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        bg = Content.DOOR;

        slot1 = false;
        slot2 = false;
        slot3 = false;

        //Font Factory
        //////////////////////////////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fipps-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 70;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.WHITE);

        parameter.size = 60;
        font2 = generator.generateFont(parameter);
        font2.setColor(Color.WHITE);

    }

    @Override
    public void handleInput() {

        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();

        //button hover
        if(MyInput.isDown(MyInput.LEFTM)) {

            if (my > 830 && my < 885) {
                hover = 1;
            } else if (my > 580 && my < 635) {
                hover = 2;
            } else if(my > 330 && my < 385){
                hover = 3;
            }else if(my > 40 && my < 100){
                hover = 4;
            }else {
                hover = 0;
            }
        }else{
            hover = 0;
        }

        //button action
        if(MyInput.isReleased(MyInput.LEFTM)){
            if (my > 830 && my < 885) {
                GameData.setCurSlot(1);
                if(slot1){
                    GameData.load();
                    gsm.setState(GameStateManager.DUN);
                }else {
                    gsm.setState(GameStateManager.CREATE);
                }
            } else if (my > 580 && my < 635) {
                GameData.setCurSlot(2);
                if(slot2){
                    GameData.load();
                    gsm.setState(GameStateManager.DUN);
                }else {
                    gsm.setState(GameStateManager.CREATE);
                }
            } else if (my > 330 && my < 385) {
                GameData.setCurSlot(3);
                if(slot3){
                    GameData.load();
                    gsm.setState(GameStateManager.DUN);
                }else {
                    gsm.setState(GameStateManager.CREATE);
                }
            }else if(my > 40 && my < 100){
                gsm.setState(GameStateManager.MENU);
            }
        }

    }



    @Override
    public void update(float dt) {

        handleInput();
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
        //batch.draw(bg,0,0);
        batch.end();

        //hover animation
        sr.begin(ShapeType.Filled);
        sr.setColor(Color.WHITE);
        switch(hover){
            case 1:
                sr.rect(0,830, Game.V_WIDTH,55);
                break;
            case 2:
                sr.rect(0,580,Game.V_WIDTH,55);
                break;
            case 3:
                sr.rect(0,330,Game.V_WIDTH,55);
                break;
            case 4:
                sr.rect(0,40,Game.V_WIDTH,55);
        }
        sr.end();

        batch.begin();


        //draw title
        font.draw(batch, "Choose", 150,1240);
        font.draw(batch, "Character",80,1150);

        //draw buttons
        for(int i = 0; i < buttons.length;i++){
            if(i+1 == hover){
                font2.setColor(Color.BLACK);
            }else{
                font2.setColor(Color.WHITE);
            }
            font2.draw(batch,buttons[i],50,900-i*250);
        }
        if(hover == 4)font2.setColor(Color.BLACK);
        else font2.setColor(Color.WHITE);
        font2.draw(batch,"Back",250,100);
        batch.end();


    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
