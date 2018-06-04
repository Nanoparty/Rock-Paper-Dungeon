package net.nanoriot.net.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.nanoriot.net.Game;
import net.nanoriot.net.handler.Content;
import net.nanoriot.net.handler.MyInput;
import net.nanoriot.net.menus.PinMenu;


/**
 * Created by Nathan on 12/21/2016.
 */

public class Pin {

    private int x,y;
    private Texture pin;
    private Location loc;
    private PinMenu pm;

    private boolean selected;

    public Pin(int x, int y, Location loc) {
        this.x = x;
        this.y = y;
        this.loc = loc;

        pm = new PinMenu(loc);

        selected = false;

        pin = Content.PIN;
    }

    public void onSelect(){
        int mx = MyInput.getTouchX();
        int my = Game.V_HEIGHT-MyInput.getTouchY();

        //button hover
        if(MyInput.isDown(MyInput.LEFTM)) {


        }

        //button action
        if(MyInput.isReleased(MyInput.LEFTM)){
            if(mx > x && mx < x + pin.getWidth() && my > y && my < y + pin.getHeight()) {
                selected = true;
            }
        }
    }

    public void update(){

        onSelect();
        pm.update();
        if(pm.getC()){
            selected = false;
            pm.setSC(false,false);
        }

    }

    public void draw(SpriteBatch batch) {

        batch.begin();
        batch.draw(pin,x,y);
        batch.end();

        if(selected) {
            pm.draw(batch);
        }
    }
}
