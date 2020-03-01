package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

class Seaweed extends FishTankItem{
    private Paint paintText = new Paint();

    /**
     * The number of weed segments.
     */
    private int length;

    /**
     * Indicates whether the bottom segment is leaning right.
     */
    private boolean leanRight;

    /**
     * Constructs a new seaweed item at the specified cursor
     * location (x,y),l segments tall.
     * @param numSegs the number of segments this seaweed is tall.
     * @param x the x coordinate of the bubble's cursor location.
     * @param y the y coordinate of the bubble's cursor location.
     * @param manager the manager of the tank this seaweed belongs to.
     */
    Seaweed(int numSegs, int x, int y, FishTankManager manager) {
            paintText.setTextSize(36);
            paintText.setTypeface(Typeface.DEFAULT_BOLD);
            this.length = numSegs;
            paintText.setColor(Color.GREEN);
            setX(x);
            setY(y);
            setManager(manager);
    }

    /**
     * Draws this seaweed.
     * @param canvas the graphics context in which to draw this item.
     */
    @Override
    void draw(Canvas canvas) {
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                if (leanRight) drawString(canvas, "/", getX(), getY()-i);
                else drawString(canvas, "\\", getX(), getY()-i);
            }
            else {
                if (leanRight) drawString(canvas, "\\", getX(),getY()-i);
                else drawString(canvas, "/", getX(), getY()-i);
            }
        }
    }

    /**
     * Draws the given string in the given graphics context at
     at the given cursor location.
     * @param canvas the graphics context in which to draw the string.
     * @param s      the string to draw.
     * @param x      the x-coordinate of the string's cursor location.
     * @param y      the y-coordinate of the string's cursor location.
     */
    @Override
    void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x * FishTankView.charWidth,
                y * FishTankView.charHeight, paintText);
    }

    /**
     * Causes this seaweed to take its turn in the fish-tank simulation.
     */
    @Override
    void move() {
        leanRight = !leanRight;
    }
}
