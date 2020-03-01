package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import java.lang.StringBuilder;

/**
 * A fish, also the parent class of all other alive items.
 */
class Fish extends FishTankItem{

    /**
     * How this fish appears on the screen.
     */
    private String appearance;

    /**
     * Indicates whether this fish is moving right.
     */
    private boolean goingRight;

    /**
     * This fish's paint setting.
     */
    private Paint paintText = new Paint();


    /**
     * Constructs a new fish at the specified cursor location (x, y).
     * @param x the first coordinate of the cursor.
     * @param y the second coordinate of the cursor.
     * @param manager the manager of the tank this fish belongs to.
     */
    Fish(int x, int y, FishTankManager manager) {
        this(x, y);
        setManager(manager);
    }

    /**
     * Another constructor at the specified cursor location (x, y),
     * mainly used for subclass instance construction.
     * @param x the first coordinate of the cursor.
     * @param y the second coordinate of the cursor.
     */
    Fish(int x, int y) {
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        appearance = "><>";
        paintText.setColor(Color.CYAN);
        goingRight = true;
        setX(x);
        setY(y);
    }

    /**
     * Change the color of the fish.
     * @param color int representing the color.
     */
    void setColor(int color){
        paintText.setColor(color);
    }

    /**
     * Change the appearance of the fish.
     * @param appearance the appearance of the fish.
     */
    void setAppearance(String appearance){
        this.appearance = appearance;
    }

    /**
     * Draws this fish tank item.
     * @param canvas the canvas on which to draw this item.
     */
    @Override
    void draw(Canvas canvas) {
        drawString(canvas, appearance, getX(), getY());
    }

    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
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
     * Causes this fish to blow a bubble.
     */
    void blowBubble() {
        getManager().getFishTankItems().add(new Bubble(getY(), getX(), getManager()));
    }

    /**
     * Build and initialize this fish's backward appearances.
     */
    private String reverseAppearance() {
        StringBuilder reverse = new StringBuilder();
        for (int i = appearance.length() - 1; i >= 0; i--) {
            switch (appearance.charAt(i)) {
                case ')':
                    reverse.append("(");
                    break;
                case '(':
                    reverse.append(")");
                    break;
                case '>':
                    reverse.append("<");
                    break;
                case '<':
                    reverse.append(">");
                    break;
                case '}':
                    reverse.append("{");
                    break;
                case '{':
                    reverse.append("}");
                    break;
                case '[':
                    reverse.append("]");
                    break;
                case ']':
                    reverse.append("[");
                    break;
                default:
                    reverse.append(appearance.charAt(i));
                    break;
            }
        }
        return reverse.toString();
    }

    /**
     * Turns this fish around, causing it to reverse direction.
     */
    void turnAround() {
        goingRight = !goingRight;
        appearance = reverseAppearance();
    }

    /**
     * Helper function of move(), the common part shared by all subclasses.
     */
    void regularMove(){
        if (getY() <= 0){
            moveVertically(-1);
        }
        if (getY() >= getManager().getGridHeight()){
            moveVertically(1);
        }
        if (goingRight) {
            moveHorizontally(1);
        }
        else {
            moveHorizontally(-1);
        }
        double decision = Math.random();
        if (decision < 0.1) {
            blowBubble();
        }
    }

    /**
     * Control the movement of the fish within the range of the fish tank.
     */
    void moveControl(){
        regularMove();
        double decision = Math.random();
        //4 and 1 are tested result that best fits the Pixel3 API 29
        // screen to display the whole fish.
        if (decision < 0.1 && getY() < getManager().getGridHeight()-4){
            moveVertically(-1);
        }
        else if (decision < 0.2 && getY() > 1) {
            moveVertically(1);
        }
    }

    /**
     * Causes this fish to take its turn in the fish-tank simulation.
     */
    @Override
    void move() {
        if (getX() + appearance.length() / 2 >= getManager().getGridWidth()
                || getX() - appearance.length() / 2 <= 0) {
            turnAround();
        }
        moveControl();
    }
}



