package net.nanoriot.net.handler;

import com.badlogic.gdx.Input;

/**
 * Created by Nathan on 11/8/2016.
 */

public class MyTextInputListener implements Input.TextInputListener {

    private String name;

    public MyTextInputListener(){
        name = "Nanashi";
    }

    @Override
    public void input (String text) {
        name = text;
    }

    @Override
    public void canceled () {
    }

    public String getName() {
        return name;
    }
}
