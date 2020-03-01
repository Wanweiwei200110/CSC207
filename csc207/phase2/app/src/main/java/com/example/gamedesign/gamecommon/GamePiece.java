package com.example.gamedesign.gamecommon;

import android.graphics.Canvas;

import java.io.Serializable;

public abstract class GamePiece implements Serializable {
  /** x-position of this block on the screen */
  protected int x;

  /** y-position of this block on the screen */
  protected int y;

  /**
   * Construct a new game piece at (x, y).
   *
   * @param x the x coord of the piece
   * @param y the y coord of the piece
   */
  protected GamePiece(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** getter for x */
  public int getX() {
    return x;
  }

  /**
   * setter for x
   *
   * @param x a new x
   */
  public void setX(int x) {
    this.x = x;
  }

  /** getter for y */
  public int getY() {
    return y;
  }

  /**
   * setter for y
   *
   * @param y a new y
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Draw the game piece.
   *
   * @param canvas canvas to draw on
   */
  protected abstract void draw(Canvas canvas);
}
