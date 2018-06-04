package net.nanoriot.net.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Content {


    public static Texture MAP = load("map1.png");
    public static Texture PIN = load("dot.png");

    public static Texture DOOR = load("home.png");
    public static Texture FORESTMENU = load("forestmenu.png");
    public static Texture RESULTS = load("results.png");
    public static Texture CHARACTER = load("characterpage.png");
    public static Texture BACKGROUND = load("backgroundPlain.png");
    public static Texture DUNGEON = load("dungeon.png");

    public static Texture BORDER = load("border.png");
    public static Texture BACK = load("back.png");

    public static Texture SLIDE = load("slide.png");
    public static Texture SLIDEU = load("slideup.png");
    public static Texture SLIDED = load("slidedown.png");

    public static Texture pmBG = load("pmbg.png");
    public static Texture pmSELECT = load("pmselect.png");
    public static Texture pmCANCEL = load("pmcancel.png");

    public static Texture BTNUP = load("btnUp.png");
    public static Texture BTNDOWN = load("btnDown.png");

    public static Texture HERO = load("knight.png");

    public static Texture SKELETON = load("skeleton.png");
    public static Texture ZOMBIE = load("zombie.png");
    public static Texture ORC = load("orc.png");
    public static Texture VAMPIRE = load("vampire.png");
    public static Texture SLIME = load("slime.png");
    public static Texture KOBOLD = load("kobold.png");
    public static Texture BANDIT = load("bandit.png");
    public static Texture GHOST = load("ghost.png");
    public static Texture SLIMEMAN = load("slimeman.png");
    public static Texture WIZARD = load("wizard.png");
    public static Texture WRAITH = load("wraith.png");


    public static Texture PLUS = load("plus.png");
    public static Texture HEART = load("heart.png");

    public static Texture RBUTTON = load("rockBTN.png");
    public static Texture PBUTTON = load("paperBTN.png");
    public static Texture SBUTTON = load("scissorBTN.png");

    public static Texture RBUTTOND = load("rButtD.png");
    public static Texture PBUTTOND = load("pButtD.png");
    public static Texture SBUTTOND = load("sButtD.png");


    /*

    public static int size = 7;
    public static int width = 96;
    public static int speed = 8;

    public static Texture shade = load("shade.png");

    public static Texture empty = load("empty.png");
    public static Texture cross = load("cross.png");

    public static Texture grass = load("empty.png");
    public static Texture tree = load("tree.png");


    public static Texture hero = load("hero.png");

    public static Texture slime = load("slime.png");
    public static TextureRegion slime1 = new TextureRegion(slime,0,0,width,width);

    public static Texture menubg = load("menubg.png");

    //tilesets
    public static Texture grassTiles = load("grassTiles.png");
    public static Texture treeTiles = load("treeTiles.png");

    public static TextureRegion tree1 = new TextureRegion(tree,0,0,width,width);

    public static TextureRegion g00 = new TextureRegion(grassTiles,0,0,width,width);
    public static TextureRegion g01 = new TextureRegion(grassTiles,width,0,width,width);
    public static TextureRegion g02 = new TextureRegion(grassTiles,2*width,0,width,width);
    public static TextureRegion g03 = new TextureRegion(grassTiles,3*width,0,width,width);
    public static TextureRegion g10 = new TextureRegion(grassTiles,0,width,width,width);
    public static TextureRegion g11 = new TextureRegion(grassTiles,width,width,width,width);
    public static TextureRegion g12 = new TextureRegion(grassTiles,width*2,width,width,width);
    public static TextureRegion g13 = new TextureRegion(grassTiles,width*3,width,width,width);
    public static TextureRegion g20 = new TextureRegion(grassTiles,0,width*2,width,width);
    public static TextureRegion g21 = new TextureRegion(grassTiles,width,width*2,width,width);
    public static TextureRegion g22 = new TextureRegion(grassTiles,width*2,width*2,width,width);
    public static TextureRegion g23 = new TextureRegion(grassTiles,width*3,width*2,width,width);
    public static TextureRegion g30 = new TextureRegion(grassTiles,0,width*3,width,width);
    public static TextureRegion g31 = new TextureRegion(grassTiles,1*width,width*3,width,width);
    public static TextureRegion g32 = new TextureRegion(grassTiles,2*width,3*width,width,width);
    public static TextureRegion g33 = new TextureRegion(grassTiles,3*width,3*width,width,width);

    //trees
    public static TextureRegion t00 = new TextureRegion(treeTiles,0,0,width,width);
    public static TextureRegion t01 = new TextureRegion(treeTiles,width,0,width,width);
    public static TextureRegion t02 = new TextureRegion(treeTiles,2*width,0,width,width);
    public static TextureRegion t03 = new TextureRegion(treeTiles,3*width,0,width,width);
    public static TextureRegion t10 = new TextureRegion(treeTiles,0,width,width,width);
    public static TextureRegion t11 = new TextureRegion(treeTiles,width,width,width,width);
    public static TextureRegion t12 = new TextureRegion(treeTiles,width*2,width,width,width);
    public static TextureRegion t13 = new TextureRegion(treeTiles,width*3,width,width,width);
    public static TextureRegion t20 = new TextureRegion(treeTiles,0,width*2,width,width);
    public static TextureRegion t21 = new TextureRegion(treeTiles,width,width*2,width,width);
    public static TextureRegion t22 = new TextureRegion(treeTiles,width*2,width*2,width,width);
    public static TextureRegion t23 = new TextureRegion(treeTiles,width*3,width*2,width,width);
    public static TextureRegion t30 = new TextureRegion(treeTiles,0,width*3,width,width);
    public static TextureRegion t31 = new TextureRegion(treeTiles,1*width,width*3,width,width);
    public static TextureRegion t32 = new TextureRegion(treeTiles,2*width,3*width,width,width);
    public static TextureRegion t33 = new TextureRegion(treeTiles,3*width,3*width,width,width);

    public static int centerW = (Game.V_WIDTH - width)/2;
    public static int centerH = (Game.V_HEIGHT - width)/2;


    //HUD
    public static Texture ibag = load("ichest.png");
    public static Texture iskills = load("iskills.png");
    public static Texture imap = load("imap.png");

    public static Texture ihelp = load("settings.png");

    public static Texture hpt = load("hpt.png");
    public static Texture hpb = load("hpb.png");

    public static Texture chat = load("chat.png");
    */
    /*
        public static Texture BA = load("arrowB.png");
        public static Texture GA = load("arrowG.png");
        public static Texture OA = load("arrowO.png");
        public static Texture GRA = load("arrowGrey.png");
        public static Texture GL = load("loopG.png");
        public static Texture OL = load("loopO.png");
        public static Texture BL = load("loopB.png");
        public static Texture CK = load("check.png");
        public static Texture XX = load("x.png");
        public static Texture CKB = load("checkB.png");
        public static Texture CKO = load("checkO.png");
        public static Texture B = load("back.png");



        public static Music music = Gdx.audio.newMusic(Gdx.files.internal("clockwork.mp3"));
        public static Sound sound = Gdx.audio.newSound(Gdx.files.internal("pc.wav"));
        public static Sound finish = Gdx.audio.newSound(Gdx.files.internal("upgrade.wav"));
        public static Sound bad = Gdx.audio.newSound(Gdx.files.internal("break.wav"));
        */
    public static Texture load(String s){
        Texture image;
        try{

            Texture sprite = new Texture(Gdx.files.internal(s));
            return sprite;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error Loading Graphics");
        }
        return null;

    }

    public static TextureRegion[][] load(String s, int w, int h) {
        TextureRegion[][] ret;
        try {
            Texture spritesheet = new Texture(Gdx.files.internal(s));
            int width = spritesheet.getWidth() / w;
            int height = spritesheet.getHeight() / h;
            ret = new TextureRegion[height][width];
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    ret[i][j] = new TextureRegion(spritesheet,j * w, i * h, w, h);
                }
            }
            return ret;
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error loading graphics.");
            System.exit(0);
        }
        return null;
    }


}
