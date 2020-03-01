package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * A bubble.
 */
class Bubble extends FishTankItem{

    /**
     * How this bubble appears on the screen.
     */
    private String appearance;

    /**
     * This bubble's paint setting.
     */
    private Paint paintText = new Paint();

    /**
     * Constructs a new bubble at the specified cursor location (x, y).
     * @param x the first coordinate of the cursor.
     * @param y the second coordinate of the cursor.
     * @param manager the manager of the tank this bubble belongs to.
     */
    Bubble(int x, int y, FishTankManager manager) {
            paintText.setTextSize(36);
            paintText.setTypeface(Typeface.DEFAULT_BOLD);
            //Get a nice-looking grey for the bubble
            paintText.setColor(Color.LTGRAY);
            //start off with . as the appearance
            appearance = ".";
            setX(x);
            setY(y);
            setManager(manager);
    }

    /**
     * Draw this bubble.
     * @param canvas the graphics context in which to draw this item.
     */
    @Override
    void draw(Canvas canvas) {
        drawString(canvas, appearance, getX(), getY());
    }

    /**
     * Draws the given string in the given graphics context at the given cursor location.
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
     * Causes this bubble to take its turn in the fish-tank simulation, moving up,
     left up, and right up.
     */
    @Override
    void move() {
        double decision = Math.random();
        moveVertically(1);
        if (0.33 <= decision && decision < 0.66) {
            moveHorizontally(1);
        }
        if (decision >= 0.66){
            moveHorizontally(-1);
        }
        decision = Math.random();
        if (decision < 0.05) {
            if (appearance.equals(".")) appearance = "o";
                // If the appearance is an o, change it to a O
            else if (appearance.equals("o")) appearance = "O";
        }
        if (getY() < 0){
            getManager().getFishTankItems().remove(this);
            this.setManager(null);
        }
    }
}
