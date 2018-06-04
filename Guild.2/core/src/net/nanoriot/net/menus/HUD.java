package net.nanoriot.net.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;



import net.nanoriot.net.entities.Enemy;
import net.nanoriot.net.entities.Hero;
import net.nanoriot.net.handler.Content;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;

/**
 * Created by Nathan on 1/15/2017.
 */

public class HUD {

    private int hhealth;
    private int ehealth;

    private int mhhealth;
    private int mehealth;

    private float hMult;
    private float eMult;

    private String hname;
    private String ename;

    private int yOff;
    private int xOff;

    private int maxChat;

    private int hlvl;
    private int elvl;

    private int yGap;

    private ArrayList<chatMsg> chat;
    private int size;

    BitmapFont font;

    private Texture heart;



    public HUD(Hero h, Enemy e, BitmapFont font) {

        heart = Content.HEART;
        hname = h.getName();
        ename = e.getName();
        mhhealth = h.getMaxHP();
        mehealth = e.getMaxHP();
        hhealth = h.getHP();
        ehealth = e.getHP();
        hlvl = h.getLvl();
        elvl = e.getLvl();
        this.font = font;

        hMult = hhealth/mhhealth;
        eMult = ehealth/mehealth;

        chat = new ArrayList<chatMsg>();
        chat.add(new chatMsg("You have entered the Dungeon",0));
        size = 1;
        maxChat = 12;
        yOff = 200;
        xOff = 30;
        yGap = 40;
    }

    public void setFont(BitmapFont font){
        this.font = font;
    }

    private class chatMsg{

        private String s1,s2;
        private Color c1, c2;
        private int num;
        private  int type;

        GlyphLayout layout1, layout2, layout3, layout4;
        float width1,width2,width3,width4;



        public chatMsg(String s1, String s2, Color c1, Color c2, int num, int type){
            this.s1 = s1;
            this.s2 = s2;
            this.c1 = c1;
            this.c2 = c2;
            this.num = num;
            this.type = type;

            layout1 = new GlyphLayout(font, this.s1);
            layout2 = new GlyphLayout(font, this.s2);
            layout3 = new GlyphLayout(font, Integer.toString(this.num));

            width1 = layout1.width;
            width2 = layout2.width;
            width3 = layout3.width;

        }

        public chatMsg(String s,int type) {
            this.s1 = s;
            this.type = type;
        }

        public chatMsg(String s, Color c1,int type) {
            this.s1 = s;
            this.type = type;
            this.c1 = c1;
        }

        public chatMsg(String s, int i, Color c1,int type) {
            this.s1 = s;
            this.type = type;
            this.c1 = c1;
            this.num = i;

            layout1 = new GlyphLayout(font, s1);
            layout2 = new GlyphLayout(font, Integer.toString(i));
            layout3 = new GlyphLayout(font, " attacks for ");

            width1 = layout1.width;
            width2 = layout2.width;
            width3 = layout3.width;
        }


    }

    public void update(int i, int j) {
        hhealth = i;
        ehealth = j;

        hMult = (float)hhealth/(float)mhhealth;
        eMult = (float)ehealth/(float)mehealth;



    }

    public void setNames(String h, String e) {
        this.hname = h;
        this.ename = e;
    }

    public void setEnemy(Enemy e){
        ename = e.getName();
        mehealth = e.getMaxHP();
        ehealth = e.getHP();
        elvl = e.getLvl();
        eMult = ehealth/mehealth;
    }

    public void render(SpriteBatch b, ShapeRenderer sr, BitmapFont font) {

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.rect(90,1020,200 * hMult,20);

        sr.rect(410 + 200 -(200 * eMult),1020,200 * eMult,20);
        sr.end();

        b.begin();
        b.draw(heart,0,950);
        b.draw(heart,560,950);
        font.setColor(Color.WHITE);
        font.draw(b,hname,110,1070);
        font.draw(b,ename,440,1070);

        font.draw(b,Integer.toString(hhealth),110,1010);
        font.draw(b,Integer.toString(ehealth),530,1010);



        b.end();

        drawChat(font, b);

    }

    private void drawChat(BitmapFont font, SpriteBatch b) {
        b.begin();

        for(int i=0;i < size; i++){
            chatMsg msg = chat.get(i);
            font.setColor(Color.WHITE);
            switch (msg.type) {
                case 0: //plain text
                    font.setColor(Color.WHITE);
                    font.draw(b, msg.s1,20+xOff,500 - yGap*i+yOff);
                    break;
                case 1: //dmg
                    font.setColor(msg.c1);
                    font.draw(b,msg.s1,20+xOff,500-yGap*i+yOff);
                    font.setColor(Color.WHITE);
                    font.draw(b," attacked for ",20+msg.width1+xOff,500-yGap*i+yOff);
                    font.setColor(Color.ORANGE);
                    GlyphLayout L1 = new GlyphLayout(font, " attacked for ");
                    font.draw(b,"" +msg.num,20+msg.width1+L1.width+xOff,500-yGap*i+yOff);
                    GlyphLayout L2 = new GlyphLayout(font, "" +msg.num);
                    font.setColor(Color.WHITE);
                    font.draw(b," damage.",20+msg.width1+L1.width+L2.width+xOff,500-yGap*i+yOff);
                    break;
                case 2: //color text
                    font.setColor(msg.c1);
                    font.draw(b,msg.s1,20+xOff,500-yGap*i+yOff);
                    font.setColor(Color.WHITE);
                    break;
                case 3: //aquire
                    font.draw(b,"You aquired ",20+xOff,500-yGap*i+yOff);
                    GlyphLayout L3 = new GlyphLayout(font, "You aquired ");
                    font.setColor(msg.c1);
                    font.draw(b,Integer.toString(msg.num),20+L3.width+xOff,500-yGap*i+yOff);
                    font.setColor(Color.WHITE);
                    font.draw(b,msg.s1,20+L3.width+msg.width2+xOff,500-yGap*i+yOff);
                    break;
                case 4:

                    break;
            }

        }

        b.end();
    }

    public void addPost(String s1, Color c1, int i, String s2, Color c2,int type) {
        chat.add(new chatMsg(s1,s2,c1,c2,i,type));
        size++;
        if(size > maxChat) {
            chat.remove(0);
            size--;
        }
    }
    public void addPost(String s1, int type) {
        chat.add(new chatMsg(s1, type));
        size++;
        if(size > maxChat) {
            chat.remove(0);
            size--;
        }
    }
    public void addPost(String s1,Color c1, int type) {
        chat.add(new chatMsg(s1, c1,type));
        size++;
        if(size > maxChat) {
            chat.remove(0);
            size--;
        }
    }

    public void addPost(String s1, int i, Color c1, int type) {
        chat.add(new chatMsg(s1, i, c1,type));
        size++;
        if(size > maxChat) {
            chat.remove(0);
            size--;
        }
    }
}
