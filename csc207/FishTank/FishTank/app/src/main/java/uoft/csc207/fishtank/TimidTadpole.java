package uoft.csc207.fishtank;

import android.graphics.Color;

import java.util.List;

/**
 * A timid tadpole that dies when the squids nearby are singing.
 */
class TimidTadpole extends Fish {

    /**
     * The status of the tadpole.
     */
    private boolean alive = true;

    /**
     * Constructs a new tadpole at the specified cursor location (x, y).
     * @param x the first coordinate of the cursor.
     * @param y the second coordinate of the cursor.
     * @param manager the manager of the tank this tadpole belongs to.
     */
    TimidTadpole(int x, int y, FishTankManager manager) {
        super(x, y);
        setAppearance("~(oo)");
        setX(x);
        setY(y);
        setManager(manager);
        setColor(Color.YELLOW);
    }

    /**
     * The helper function of move(), decide weather to die and control the operations upon death.
     */
    @Override
    void moveControl() {
        FishTankManager manager = getManager();
        List<FishTankItem> items = manager.getFishTankItems();
        if (!alive){
            moveVertically(-1); //keep falling until falls out of the screen,
            // and the instance is removed from the manager to save space.
            if (getY() > manager.getGridHeight()) {
                manager.getFishTankItems().remove(this);
                this.setManager(null);
            }
        }
        else {
            regularMove();
            double decision = Math.random();
            //4 and 1 are tested result that best fits the Pixel3 API 29
            // screen to display the whole fish.
            if (decision > 0.8 && getY() < manager.getGridHeight() - 4) moveVertically(-1);
            else if (decision < 0.7 && getY() > 1) moveVertically(1);

            for (int i = 0; i < items.size(); i++) {
                FishTankItem item = items.get(i);
                int yDistance = item.getY() - getY();
                int xDistance = item.getX() - getX();
                int distance = (int)Math.abs(Math.round(
                        Math.sqrt(xDistance*xDistance+yDistance*yDistance))); //calculate distance
                if (distance <= 2 && item instanceof SingingSquid
                        && ((SingingSquid) item).isSinging()) scareToDeth();
                }
            }
        }

    /**
     * Switch to dead appearance.
     */
    private void scareToDeth(){
        setAppearance("-{xx}");
        alive = false;
        setColor(Color.WHITE);
    }
}
