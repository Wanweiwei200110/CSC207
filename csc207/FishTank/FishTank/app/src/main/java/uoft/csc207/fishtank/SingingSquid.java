package uoft.csc207.fishtank;

import android.graphics.Color;

import java.util.List;

/**
 * A squid that start to sing as it meets a tadpole.
 */
class SingingSquid extends Fish {

    /**
     * Weather this squid is singing.
     */
    private boolean singing;

    /**
     * Constructs a squid at the specified cursor location (x, y).
     * @param x the first coordinate of the cursor.
     * @param y the second coordinate of the cursor.
     * @param manager the manager of the tank this squid belongs to.
     */
    SingingSquid(int x, int y, FishTankManager manager) {
        super(x, y);
        setAppearance("*~=(i:)");
        setX(x);
        setY(y);
        setManager(manager);
        setColor(Color.MAGENTA);
    }

    /**
     * The status of the squid.
     */
    boolean isSinging(){
        return singing;
    }

    /**
     * The helper function of move(), decide weather to sing and control
     * the movement wile singing and decide when to stop singing.
     */
    @Override
    void moveControl(){
        FishTankManager manager = getManager();
        List<FishTankItem> items = manager.getFishTankItems();
        if (singing){
            moveVertically(1);
            if (getY() <= 0) {
                singing = false;
                setAppearance("*~=(i:)");
                setColor(Color.MAGENTA);
                moveVertically(-1);
            }
        }
        else{
            regularMove();
            double decision = Math.random();
            //4 and 1 are tested result that best fits the Pixel3 API 29
            // screen to display the whole fish.
            if (decision > 0.5 && getY() < manager.getGridHeight()-4) moveVertically(-1);
            else if (decision < 0.9 && getY() > 1) moveVertically(1);

            for (int i = 0; i < items.size(); i++) {
                FishTankItem item = items.get(i);
                int yDistance = item.getY() - getY();
                int xDistance = item.getX() - getX();
                int distance = (int)Math.abs(Math.round(
                        Math.sqrt(xDistance*xDistance+yDistance*yDistance))); //calculate distance.
                if (distance <= 3 && item instanceof TimidTadpole) sing();
            }
        }
    }

    /**
     * Switch the singing appearance and blow bubbles.
     */
    private void sing(){
        singing = true;
        setAppearance("*~=(O;)");
        setColor(Color.RED);
        for (int a = 0; a < 20; a ++){
            blowBubble();
        }
    }

}
