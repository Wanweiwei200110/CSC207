package uoft.csc207.fishtank;

import android.graphics.Canvas;

import java.util.ArrayList;

import java.util.List;

/**
 * The manager of a fish tank, controlling the creation and movement of the items in this tank.
 */
class FishTankManager {

    /**
     * ArrayList of all items in the tank.
     */
    private List<FishTankItem> fishTankItems = new ArrayList<>();
    /**
     * The width of the tank.
     */
    private int gridWidth;
    /**
     * The height of the tank.
     */
    private int gridHeight;

    /**
     * Return the width of the tank.
     * @return the width of the tank.
     */
    int getGridWidth() {
        return gridWidth;
    }

    /**
     * Return the height of the tank.
     * @return the height of the tank.
     */
    int getGridHeight() {
        return gridHeight;
    }

    /**
     * Construct a fish tank manager managing the tank with dimention height, width.
     * @param height the height of the tank.
     * @param width the width of the tank.
     */
    FishTankManager(int height, int width) {
        gridHeight = height;
        gridWidth = width;
    }

    /**
     * Return the list containing all items in this tank.
     * @return list of items in this tank.
     */
    List<FishTankItem>  getFishTankItems(){
        return fishTankItems;
    }

    /**
     * Draw all the items in this fish tank.
     * @param canvas used for drawing the items.
     */
    void draw(Canvas canvas) {
        for (int a = 0; a < fishTankItems.size(); a++) {
               (fishTankItems.get(a)).draw(canvas);
        }
    }

    /**
     * Update the movement of items in the tank.
     */
    void update() {
        for (int a = 0; a < fishTankItems.size(); a++) {
            (fishTankItems.get(a)).move();
        }
    }

    /**
     * Add items to this fish tank and assign them this manager.
     */
    void createTankItems() {
        fishTankItems.add(new TimidTadpole(17, 18, this));
        fishTankItems.add(new SingingSquid(23, 8, this));
        fishTankItems.add(new Fish(28, 18, this));
        fishTankItems.add(new Fish(10, 22, this));
        fishTankItems.add(new Fish(17, 14, this));
        fishTankItems.add(new Fish(15, 28, this));
        fishTankItems.add(new Fish(35, 36, this));
        fishTankItems.add(new Fish(16, 5, this));
        fishTankItems.add(new TimidTadpole(20, 30, this));
        fishTankItems.add(new SingingSquid(6, 14, this));
        fishTankItems.add(new Fish(16, 12, this));
        fishTankItems.add(new Fish(16, 18, this));
        fishTankItems.add(new Fish(23, 18, this));
        fishTankItems.add(new Fish(6, 12, this));
        fishTankItems.add(new HungryFish(10, 20, this));
        fishTankItems.add(new Seaweed(9, 4, 33, this));
        fishTankItems.add(new Seaweed(6, 13, 24, this));
        fishTankItems.add(new Seaweed(12, 15, 42, this));
        fishTankItems.add(new Seaweed(5, 20, 13, this));
    }
}
