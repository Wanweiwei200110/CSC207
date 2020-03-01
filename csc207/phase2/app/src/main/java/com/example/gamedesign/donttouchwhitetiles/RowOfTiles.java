package com.example.gamedesign.donttouchwhitetiles;

import android.graphics.Canvas;

import java.io.Serializable;
import java.util.List;

/** A row of tiles in the game BlackWhiteBlock */
public class RowOfTiles implements Serializable {
  /** y-position of these tiles */
  private int y;

  /** index of the black tile rest of the tiles are white */
  private int indexOfBlack;

  /** how many tiles in this row */
  private int cols;

  /** the height of each tile */
  private int height;

  /** the width of each tile */
  private int width;

  /** whether user clicked on the black tile */
  private boolean gotBlack;

  /** A list of tiles */
  private List<TilePiece> listOfTiles;

  /**
   * Creating a row of tiles with given number of column, y-coord, width and height
   *
   * @param y y-coord of this row
   * @param cols number of tiles in this row
   * @param width width of each tile
   * @param height height of each tile
   */
  RowOfTiles(
      int y, int cols, int width, int height, int indexOfBlack, List<TilePiece> listOfTiles) {
    this.y = y;
    this.listOfTiles = listOfTiles;
    this.width = width;
    this.height = height;
    this.cols = cols;
    gotBlack = false;
    this.indexOfBlack = indexOfBlack;
  }

  /**
   * make the whole row move down by speed
   *
   * @param speed speed of y-coord moving down
   */
  void moveDown(int speed) {
    y += speed;
    for (int i = 0; i < cols; i++) {
      TilePiece curr_block = listOfTiles.get(i);
      int original_y = curr_block.getY();
      curr_block.setY(original_y + speed);
    }
  }

  /**
   * draw this row of tiles on given canvas
   *
   * @param c canvas to draw on
   */
  protected void draw(Canvas c) {
    for (int i = 0; i < cols; i++) {
      listOfTiles.get(i).draw(c);
    }
  }

  /** @return the lower x-coord of the black tile in this row */
  int getBlackBlockXLowerBound() {
    return listOfTiles.get(indexOfBlack).getX();
  }

  /** @return the upper x-coord of the black tile in this row */
  int getBlackBlockXUpperBound() {
    return listOfTiles.get(indexOfBlack).getX() + width;
  }

  /** @return the lower y-coord of the black tile in this row */
  int getBlackBlockYLowerBound() {
    return listOfTiles.get(indexOfBlack).getY();
  }

  /** @return the upper y-coord of the black tile in this row */
  int getBlackBlockYUpperBound() {
    return listOfTiles.get(indexOfBlack).getY() + height;
  }

  /**
   * set the black tile in this row to grey change gotBlack to true which indicates user get the
   * correct black tile
   */
  void setGotBlack() {
    gotBlack = true;
    listOfTiles.get(indexOfBlack).setColor("#7a7a7a");
  }

  /** return if the user get the correct black tile */
  boolean getGotBlack() {
    return gotBlack;
  }

  /** @return y-coord of this row of tiles */
  public int getY() {
    return y;
  }

  /** Initialize the color of the tiles. */
  void initColor() {
    for (int i = 0; i < cols; i++) {
      TilePiece curr = listOfTiles.get(i);
      curr.initColor();
      if (gotBlack && i == indexOfBlack) {
        curr.setColor("#7a7a7a");
      }
    }
  }
}
