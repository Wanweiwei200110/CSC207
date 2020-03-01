package com.example.gamedesign.jumpingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.example.gamedesign.gamecommon.GamePiece;
import com.example.gamedesign.serializableclass.MyPaint;
import com.example.gamedesign.serializableclass.MyRect;

import java.io.Serializable;

/** The bat in JumpingBall game to catch the ball. */
public class BatPiece extends GamePiece implements Serializable {

  /** The width of the bat. */
  private int width;

  /** The paint of the bat. */
  private MyPaint paint;

  /** The rectangle used to represent the shape of the bat. */
  private MyRect rect;

  /**
   * Construct a new bat at (x, y) with the given parameter of the rect.
   *
   * @param x the x coord of the bat
   * @param y the y coord of the bat
   * @param left the x coord of the left side of the bat
   * @param top the y coord of the top of the bat
   * @param right the x coord of the right side of the bat
   * @param bottom the y coord of the bottom of the rect
   */
  BatPiece(int x, int y, int left, int top, int right, int bottom) {
    super(x, y);
    width = right - left;
    rect = new MyRect(left, top, right, bottom);
    paint = new MyPaint();
    paint.setColor(Color.BLACK);
  }

  /**
   * Return the rectangle representation of the bat.
   *
   * @return the rectangle representing the bat
   */
  Rect getRect() {
    return rect.getRect();
  }

  /**
   * Draw the bat.
   *
   * @param canvas canvas the bat is drew on
   */
  @Override
  protected void draw(Canvas canvas) {
    canvas.drawRect(rect.getRect(), paint);
  }

  /**
   * Update the position of the bat to the given x coord, y position is not changed.
   *
   * @param x x-position
   */
  void update(int x) {
    setX(x);
    rect = new MyRect(x - (width / 2), rect.getRect().top, x + (width / 2), rect.getRect().bottom);
  }
}
