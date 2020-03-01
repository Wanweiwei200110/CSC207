package com.example.gamedesign.jumpingball;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.gamedesign.gamecommon.GamePiece;
import com.example.gamedesign.serializableclass.MyPaint;

import java.io.Serializable;

/**
 * Item that increases score earned in the game and speeds up the movement of the ball if caught by
 * the bat.
 */
public class SpeedDroppingItem extends GamePiece implements Serializable {

  /** The paint of the item. */
  private MyPaint paint = new MyPaint();

  /**
   * Construct a new speed item at (x, y).
   *
   * @param x the x coord of the item
   * @param y the y coord of the item
   */
  SpeedDroppingItem(int x, int y) {
    super(x, y);
    paint.setColor(Color.YELLOW);
  }

  /**
   * Draw the item.
   *
   * @param canvas the canvas to draw on
   */
  @Override
  protected void draw(Canvas canvas) {
    canvas.drawCircle(getX(), getY(), 30, paint);
  }

  /** Update the y coord of the item. */
  void update() {
    setY(getY() + 5);
  }
}
