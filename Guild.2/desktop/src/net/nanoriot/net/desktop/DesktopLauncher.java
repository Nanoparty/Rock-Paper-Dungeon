package net.nanoriot.net.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.nanoriot.net.Game;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Game.V_WIDTH* Game.SCALE;
		config.height = Game.V_HEIGHT*Game.SCALE;
		new LwjglApplication(new Game(), config);
	}
}
