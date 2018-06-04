package net.nanoriot.net;



import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.nanoriot.net.handler.GameStateManager;
import net.nanoriot.net.handler.InputProcessor;
import net.nanoriot.net.handler.MyInput;


public class Game implements ApplicationListener {

	public static final String TITLE = "LOL TITLE";
	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 1280;
	public static final int SCALE = 1;

	public static final float STEP = 1 / 60f;
	private float accum;

	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera cam2;
	private OrthographicCamera hudCam;

	private GameStateManager gsm;

	public void create() {



		Gdx.input.setInputProcessor(new InputProcessor());

		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		cam2 = new OrthographicCamera();
		cam2.setToOrtho(false,V_WIDTH,V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);

	}

	public void render() {

		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			//System.out.println("" + STEP);
			gsm.render();

			MyInput.update();
		}

	}

	public void dispose() {

	}

	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getCamera2(){return cam2;}
	public OrthographicCamera getHUDCamera() { return hudCam; }

	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}

}
