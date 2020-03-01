package com.example.gamedesign.serializableclass;

import android.graphics.Rect;

import java.io.Serializable;

/** A serializable Rect */
public class MyRect implements Serializable {

  /** x-position of this Rect */
  private int x;

  /** y-position of this Rect */
  private int y;

  /** width of Rect */
  private int width;

  /** height of Rect */
  private int height;

  /**
   * Construct a Rect at (x,y) with width and height
   *
   * @param x x-position of this Rect
   * @param y y-position of this Rect
   * @param width width
   * @param height height
   */
  public MyRect(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /** an empty constructor */
  public MyRect() {}

  /** @return a real Rect for drawing purpose */
  public Rect getRect() {
    return new Rect(x, y, width, height);
  }

  /**
   * set the size of Rect to given x, y, width and height
   *
   * @param x x-position of this Rect
   * @param y y-position of this Rect
   * @param width width
   * @param height height
   */
  public void set(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
}
