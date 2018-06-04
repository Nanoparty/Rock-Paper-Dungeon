package net.nanoriot.net.states;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.nanoriot.net.Game;
import net.nanoriot.net.handler.GameStateManager;
import net.nanoriot.net.handler.MyInput;
import net.nanoriot.net.handler.MyTextInputListener;
import net.nanoriot.net.utils.Textbox;


public class CharCreate extends GameState{

    private SpriteBatch batch;
    private ShapeRenderer sr;
    private BitmapFont font,font2;

    private int hover;

    private String[] buttons = {"Confirm Name"};

    private int inc;
    private int line;

    private boolean done;
    private boolean stage1;
    private boolean stage2;
    private boolean stage3;

    private boolean stored_name;
    private String char_name;
    private int max_length;

    private Textbox text1;
    private Textbox text2;
    MyTextInputListener listener;



    //comment
    public CharCreate(GameStateManager gsm) {
        super(gsm);

        //primitives
        hover = 0;

        done = false;
        stage1 = true;
        stage2 = false;
        stage3 = false;
        stored_name = false;
        char_name = "Nanashi";
        max_length = 10;

        //objects
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        listener = new MyTextInputListener();

        //Font Factory
        //////////////////////////////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fipps-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 75;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.WHITE);

        parameter.size = 25;
        font2 = generator.generateFont(parameter);
        font2.setColor(Color.WHITE);

        text1 = new Textbox(batch, "In times of Great Chaos and Destruction,\nthe People will turn to a Hero to guide\nthem out of the Darkness and usher in an\nEra of lasting Peace and Prosperity.\nYou are the Hero... ", font2, 10, 80, System.nanoTime());
        text2 = new Textbox(batch, "This is Your Tale as a Hero of the Land.\nWhat will be your Name?", font2, 10, 80, System.nanoTime());
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

            if(done && stage1){
                done = false;
                stage1 = false;
                stage2 = true;
                text2.start(System.nanoTime());
            }

            if(done && stage2){

                //name box selection
                if(MyInput.isReleased(MyInput.LEFTM)){
                    if(my < 540 && my > 500){
                        if(mx > 150 && mx < 450){
                            Gdx.input.getTextInput(listener, "Character Creation", "", "Something Heroic");
                        }
                    }
                }

                //confirm hover
                //button hover
                if(MyInput.isDown(MyInput.LEFTM)) {

                    if (my > 260 && my < 305) {
                        hover = 1;
                    } else {
                        hover = 0;
                    }
                }

                //button action name confirm
                if(MyInput.isReleased(MyInput.LEFTM)){
                    if (my > 260 && my < 305) {
                        //next part
                    }
                }
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

        //hover animation
        sr.begin(ShapeType.Filled);
        sr.setColor(Color.WHITE);

        sr.end();

        if(stage1) {
            done = text1.render(150,Game.V_HEIGHT);
        }

        if(stage2) {
            done = text2.render(150,Game.V_HEIGHT);
            if(done){

                char_name = listener.getName();

                sr.begin(ShapeType.Line);
                sr.setColor(Color.WHITE);
                sr.rect(150,500,300,40);
                sr.end();


                sr.begin(ShapeType.Filled);
                if(true){
                    sr.rect(0,260,Game.V_WIDTH,45);
                }

                sr.end();
                batch.begin();
                font2.draw(batch,char_name,155,535);

                if(1 == hover){
                    font2.setColor(Color.BLACK);
                }else{
                    font2.setColor(Color.WHITE);
                }
                font2.draw(batch,"Confirm Name",150,300);

                batch.end();
            }



        }

        //draw title




    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
