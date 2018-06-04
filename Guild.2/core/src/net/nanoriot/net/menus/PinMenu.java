package net.nanoriot.net.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import net.nanoriot.net.Game;
import net.nanoriot.net.entities.Location;
import net.nanoriot.net.handler.Content;
import net.nanoriot.net.handler.MyInput;


/**
 * Created by Nathan on 12/21/2016.
 */

public class PinMenu {

    private Location loc;

    private Texture bg;
    private Texture select;
    private Texture cancel;

    private BitmapFont font;

    private int x,y;

    private boolean s;
    private boolean c;

    public PinMenu(Location loc){

        bg = Content.pmBG;
        select = Content.pmSELECT;
        cancel = Content.pmCANCEL;

        s = c = false;

        this.loc = loc;

        x = (Game.V_WIDTH-bg.getWidth())/2;
        y = (Game.V_HEIGHT-bg.getHeight())/2;



        /////////////////////

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fipps-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 25;

        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.WHITE);

    }

    public void update() {
        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();

        //button hover
        if(MyInput.isDown(MyInput.LEFTM)) {


        }

        //button action
        if(MyInput.isReleased(MyInput.LEFTM)){

            if(mx > x && mx < x + select.getWidth() && my > y && my < y + select.getHeight()) {
                s = true;
            }
            if(mx > x + select.getWidth() && mx < x + cancel.getWidth() + select.getWidth() && my > y && my < y + cancel.getHeight()) {
                c = true;
            }
        }
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(bg,x,y);
        font.draw(batch,"Name: " + loc.getName() ,x + 10, y - 10);
        font.draw(batch,"Rank: " + loc.getDif(),x + 10, y - 40);
        font.draw(batch,"Region: " + loc.getRegion(),x + 10, y - 70);
        batch.draw(select,x , y);
        batch.draw(cancel,x + select.getWidth(), y);
        batch.end();
    }

    public boolean getS(){return s;}
    public boolean getC(){return c;}

    public void setSC(boolean a,boolean b){
        s = a;
        c = b;
    }
}
