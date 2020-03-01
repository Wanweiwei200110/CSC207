package com.example.gamedesign.donttouchwhitetiles;

import java.util.ArrayList;
import java.util.Random;

/** a Don'tTouchWhiteTilesBuilder class */
public class DontTouchWhiteTilesBuilder {

  /** screen's width */
  private int screenX;

  /** screen's height */
  private int screenY;

  /** the number of columns */
  private int cols;

  /** the number of rows */
  private int rows;

  /** width */
  private int width;

  /** height */
  private int height;

  /** array list of screen of tiles */
  private ArrayList<RowOfTiles> screenOfTiles;

  /** an empty constructor for builder */
  public DontTouchWhiteTilesBuilder() {}

  /**
   * @param screenX the width of screen
   * @return this builder
   */
  public DontTouchWhiteTilesBuilder setScreenX(int screenX) {
    this.screenX = screenX;
    return this;
  }

  /**
   * @param screenY the height of the screen
   * @return this builder
   */
  public DontTouchWhiteTilesBuilder setScreenY(int screenY) {
    this.screenY = screenY;
    return this;
  }

  /**
   * @param cols the number of columns
   * @return this builder
   */
  public DontTouchWhiteTilesBuilder setCols(int cols) {
    this.cols = cols;
    return this;
  }

  /**
   * @param rows the number of rows
   * @return this builder
   */
  public DontTouchWhiteTilesBuilder setRows(int rows) {
    this.rows = rows;
    return this;
  }

  /**
   * build the game controller
   *
   * @return game controller
   */
  public DontTouchWhiteTilesController build() {
    setWidth();
    setHeight();
    createScreenOfTiles();
    return new DontTouchWhiteTilesController(screenX, screenY, cols, rows, screenOfTiles);
  }

  /**
   * build the game controller
   *
   * @return game controller
   */
  private RowOfTiles createRowOfTiles(int y) {
    ArrayList<TilePiece> listOfTiles = new ArrayList<>();
    Random ran = new Random();
    boolean isBlack;
    int indexOfBlack = ran.nextInt(cols);
    for (int i = 0; i < cols; i++) {
        // the randomly chosen black tile
        isBlack = i == indexOfBlack;
      TilePiece currBlock = new TilePiece(i * width, y, width, height, isBlack);
      listOfTiles.add(currBlock);
    }
    return new RowOfTiles(y, cols, width, height, indexOfBlack, listOfTiles);
  }

  /** set width */
  private void setWidth() {
    width = screenX / cols;
  }

  /** set height */
  private void setHeight() {
    height = screenY / rows;
  }

  /** create screen of tiles */
  private void createScreenOfTiles() {
    screenOfTiles = new ArrayList<>();
    for (int i = rows - 1; i >= 0; i--) { // creating new RowOfTiles from bottom to top
      RowOfTiles curr_row = createRowOfTiles(i * height);
      screenOfTiles.add(curr_row);
    }
  }
}
