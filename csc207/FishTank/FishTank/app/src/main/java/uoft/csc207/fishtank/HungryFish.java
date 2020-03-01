package uoft.csc207.fishtank;

/**
 * A hungry fish.
 */
class HungryFish extends Fish{

    /**
     * Constructs a new hungry fish at the specified cursor location (x, y).
     * @param x the first coordinate of the cursor.
     * @param y the second coordinate of the cursor.
     * @param manager the manager of the tank this hungry fish belongs to.
     */
    HungryFish(int x, int y, FishTankManager manager) {
        super(x, y);
        setAppearance("><MEHUNGRY>");
        setX(x);
        setY(y);
        setManager(manager);
    }

    /**
     * Causes this fish to take its turn in the fish-tank simulation.
     */
    @Override
    void move() {
        //7 and 1 are tested result that best fits the Pixel3 API 29
        // screen to display the whole fish.
        if (getX() + 7 >= getManager().getGridWidth() || getX() - 1 <= 0) {
            turnAround();
        }
        moveControl();
    }
}
