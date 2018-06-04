package net.nanoriot.net.utils;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ardap on 11/8/2016.
 */

public class Textbox {

    private String text;
    private SpriteBatch batch;
    private BitmapFont font;
    private int charDelay;
    private int lineSpacing;
    private long initTime;

    public Textbox(SpriteBatch batch, String text, BitmapFont font, int charDelay, int lineSpacing, long initTime) {
        this.initTime = initTime;
        this.text = text;
        this.batch = batch;
        this.font = font;
        this.charDelay = charDelay;
        this.lineSpacing = lineSpacing;
    }

    // Reset the start time (to replay the text scrolling)
    public void start(long systemTime) {
        this.initTime = systemTime;
    }

    // X and Y position to start drawing text
    // Returns true when it is done scrolling
    public boolean render(int xPos, int yPos) {
        long startTime = initTime;
        // Queue of lines to print out
        String[] splitText = new String[0];
        Queue<String> lines = splitIntoLines(text);
        batch.begin();

        for (String line : lines) {
            scrollLine(line, startTime, xPos, yPos);
            // Increment startTime by the time it takes to scroll a line
            startTime += (long)charDelay * (long)line.length() * 1000000;
            yPos -= lineSpacing;
        }

        batch.end();
        System.out.println("sysTime = " + Integer.toString((int)(System.nanoTime()/1000000)) + " startTime = " + Integer.toString((int)(startTime/1000000)));
        if (startTime < System.nanoTime()) {
            return true;
        } else {
            return false;
        }
    }

    private void scrollLine(String line, long startTime, int x, int y) {
        startTime = (startTime / 1000000) + 1;   // Convert to milliseconds (rounding up)
        long currentTime = System.nanoTime() / 1000000;  // Convert to milliseconds (rounding down)
        int pos = (int)(currentTime - startTime) / charDelay;   // Calculates how many characters should be displayed

        if (pos <= line.length() && pos > 0) {  // While partially drawing the line
            font.draw(batch, line.substring(0,pos), x, y);
            System.out.println("Scrolling " + line.substring(0,pos) + " @ " + Integer.toString((int) startTime / 1000000));
        } else if (pos > line.length()){
            font.draw(batch, line, x, y);
        }
    }

    private Queue<String> splitIntoLines(String input) {
        Queue<String> lines = new LinkedList<String>();
        int startSegment = 0;
        for (int endSegment = 0; endSegment < input.length()-1; endSegment++) {
            if (input.charAt(endSegment) == '\n') {
                lines.add(input.substring(startSegment, endSegment));
                startSegment = endSegment+1;
            } else if (endSegment == input.length()-2) {
                lines.add(input.substring(startSegment, input.length()));
            }
        }
        return lines;
    }
}
