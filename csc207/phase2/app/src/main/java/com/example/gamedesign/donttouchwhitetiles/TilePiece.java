package com.example.gamedesign.donttouchwhitetiles;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.gamedesign.gamecommon.GamePiece;
import com.example.gamedesign.serializableclass.MyPaint;
import com.example.gamedesign.serializableclass.MyRect;

import java.io.Serializable;

/** A tile in game BlackWhiteBlock */
class TilePiece extends GamePiece implements Serializable {

  /** indicates whether this tile is black */
  private boolean isBlack;

  /** the width of this tile */
  private int width;

  /** the height of this tile */
  private int height;

  /** the paint for this tile for drawing edges */
  private MyPaint myGridPaint;

  /** the paint for this tile for drawing color */
  private MyPaint myBlockPaint;

  /** A rectangular to contain this tile */
  private MyRect rect;

  /**
   * constructing a new tile piece
   *
   * @param x x-position
   * @param y y-position
   * @param width width of the tile piece
   * @param height height of the tile piece
   * @param isBlack boolean for the tile piece is black or not
   */
  TilePiece(int x, int y, int width, int height, boolean isBlack) {
    super(x, y);
    this.width = width;
    this.height = height;
    this.isBlack = isBlack;
    myBlockPaint = new MyPaint();
    initColor();
    myGridPaint = new MyPaint();
    myGridPaint.setColor(Color.parseColor("#7a7a7a"));
    myGridPaint.setStrokeWidth(3);
    rect = new MyRect();
  }

  protected void draw(Canvas canvas) {
    rect.set(x, y, x + width, y + height);
    canvas.drawRect(rect.getRect(), myBlockPaint); // draw the color in the rect
    // draw the grid for this block
    canvas.drawLine(x, y, x + width, y, myGridPaint); // top left to top right
    canvas.drawLine(x, y, x, y + height, myGridPaint); // top left to bottom left
    canvas.drawLine(x + width, y, x + width, y + height, myGridPaint);
    // top right to bottom right
    canvas.drawLine(x, y + height, x + width, y + height, myGridPaint);
    // bottom left to bottom right

  }

  /**
   * set the tile's color to desired color
   *
   * @param c new color
   */
  protected void setColor(String c) {
    myBlockPaint.setColor(Color.parseColor(c));
  }

  /** init the color of the tile piece */
  void initColor() {
    if (isBlack) {
      myBlockPaint.setColor(Color.parseColor("#000000"));
    } else {
      myBlockPaint.setColor(Color.parseColor("#ffffff"));
    }
  }
}
