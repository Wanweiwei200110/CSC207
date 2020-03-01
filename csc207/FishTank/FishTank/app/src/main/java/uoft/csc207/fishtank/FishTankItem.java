package uoft.csc207.fishtank;

import android.graphics.Canvas;

/**
 * All items in the FishTank.
 */
abstract class FishTankItem {

    /**
     * This item's first coordinate.
     */
    private int x;

    /**
     * This item's second coordinate.
     */
    private int y;

    /**
     * This item's manager.
     */
    private FishTankManager manager;

    /**
     * Getter for x.
     * @return x coordinate of the item.
     */
    int getX(){
        return x;
    }

    /**
     * Getter for y.
     * @return y coordinate of the item.
     */
    int getY(){
        return y;
    }

    /**
     * Setter for x.
     * @param x the new x coordinate.
     */
    void setX(int x){
        this.x = x;
    }

    /**
     * Let the item move to the right by change distance.
     * @param change the distance the item moves.
     */
    void moveHorizontally(int change) {
        x += change;
    }
    /**
     * Setter for y.
     * @param y the new x coordinate.
     */
    void setY(int y) {
        this.y = y;
    }

    /**
     * Let the item move up by change distance.
     * @param change the height the item moves.
     */
    void moveVertically(int change){
        y -= change;
    }
    /**
     * Getter for this item's manager.
     * @return manager of the item.
     */
    FishTankManager getManager(){
        return manager;
    }

    /**
     * Assign a FishTankManager instance to the item.
     * @param manager the manager of the item.
     */
    void setManager(FishTankManager manager){
        this.manager = manager;
    }

    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param canvas where to draw the string.
     * @param s      the string to draw.
     * @param x      the x-coordinate of the string's cursor location.
     * @param y      the y-coordinate of the string's cursor location.
     */
    abstract void drawString(Canvas canvas, String s, int x, int y);

    /**
     * Draw this item.
     * @param canvas the graphics context in which to draw this item.
     */
    abstract void draw(Canvas canvas);

    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    abstract void move();
}
