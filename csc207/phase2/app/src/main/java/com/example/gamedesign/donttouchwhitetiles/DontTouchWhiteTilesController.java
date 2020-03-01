package com.example.gamedesign.donttouchwhitetiles;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.gamedesign.gamecommon.AnimatedGameController;
import com.example.gamedesign.gamecommon.OnGamePlayListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DontTouchWhiteTilesController extends AnimatedGameController {

  /** width of tile piece */
  private int width;

  /** height of tile piece */
  private int height;

  /** speed of the tile piece move */
  private int speed;

  /** number of columns */
  private int cols;

  /** acceleration */
  private int acceleration;

  /** list of RowOfTiles */
  private List<RowOfTiles> screenOfTiles;

  /** boolean for the game start or not */
  private boolean isStarted;

  /** increment of gold */
  private int gold_increment;

  /**
   * Initialize a game controller with given screen size
   *
   * @param screenX width of the screen
   * @param screenY height of the screen
   */
  DontTouchWhiteTilesController(
      int screenX, int screenY, int cols, int rows, List<RowOfTiles> screenOfTiles) {
    super(screenX, screenY);
    this.cols = cols;
    speed = 20;
    acceleration = 5;
    width = screenX / cols;
    height = screenY / rows;
    this.screenOfTiles = screenOfTiles;
    isStarted = false;
    gold_increment = 1;
  }

  /** basic logic of the game */
  @Override
  public void logic(OnGamePlayListener onGamePlayListener) {
    createNewRow();
    deleteLastRow();
    moveDown();
    addSpeed();
    if (!isRunning() && ifStarted()) {
      onGamePlayListener.gameOverDialogue();
    }
  }

  /** randomly add the speed of block moving down */
  private void addSpeed() {
    int curr_score = getScore();
    if (curr_score % 500 == 0) {
      speed += acceleration;
    }
  }

  /** make the block move down */
  private void moveDown() {
    for (int i = 0; i < screenOfTiles.size(); i++) {
      RowOfTiles curr = screenOfTiles.get(i);
      curr.moveDown(speed);
      updateScore(speed / 5);
    }
  }

  /** remove the last row of the block from the list to release memory */
  private void deleteLastRow() {
    RowOfTiles lastRow = screenOfTiles.get(0);
    if (lastRow.getY() >= screenY) {
      if (!lastRow.getGotBlack()) {
        setRunning(false);
      }
      screenOfTiles.remove(lastRow);
    }
  }

  /** create new row of blocks from top */
  private void createNewRow() {
    int listSize = screenOfTiles.size();
    RowOfTiles topRow = screenOfTiles.get(listSize - 1);
    int topY = topRow.getY();
    if (topY >= 0) {
      RowOfTiles newRow = createRowOfTiles(topY - height);
      // RowOfTiles newRow = new RowOfTiles(topY - height, cols, width, height);
      screenOfTiles.add(newRow);
    }
  }

  /** @return RowOfTiles */
  private RowOfTiles createRowOfTiles(int y) {
    ArrayList<TilePiece> listOfTiles = new ArrayList<>();
    Random ran = new Random();
    boolean isBlack;
    int indexOfBlack = ran.nextInt(cols);
    for (int i = 0; i < cols; i++) {
        isBlack = i == indexOfBlack;
      TilePiece currBlock = new TilePiece(i * width, y, width, height, isBlack);
      listOfTiles.add(currBlock);
    }
    return new RowOfTiles(y, cols, width, height, indexOfBlack, listOfTiles);
  }

  /** return if the game is started. */
  private boolean ifStarted() {
    return isStarted;
  }

  /**
   * how to act if user clicks on the screen
   *
   * @param event where user clicked
   */
  public void onClick(MotionEvent event) {
    float eventX = event.getX();
    float eventY = event.getY();
    boolean clickOnBlackBlock = ifClickedRight(eventX, eventY);
    if (!isRunning && clickOnBlackBlock) {
      setRunning(true);
      isStarted = true;
    }
    if (!clickOnBlackBlock) {
      setRunning(false);
    }
  }

  /**
   * how to act if user clicks on the screen
   *
   * @param motionEvent where user clicked
   */
  @Override
  public void onTouch(OnGamePlayListener onGamePlayListener, MotionEvent motionEvent) {
    float eventX = motionEvent.getX();
    float eventY = motionEvent.getY();
    boolean clickOnBlackBlock = ifClickedRight(eventX, eventY);
    if (!isRunning && clickOnBlackBlock) {
      setRunning(true);
      isStarted = true;
    }
    if (!clickOnBlackBlock) {
      setRunning(false);
      onGamePlayListener.gameOverDialogue();
    }
  }

  /**
   * if user clicked on the right black block
   *
   * @param eventX x-coord where user clicked
   * @param eventY y-coored where user clicked
   * @return return if user clicked on the right black block
   */
  private boolean ifClickedRight(float eventX, float eventY) {
    boolean result = false;
    int size = screenOfTiles.size();
    for (int i = 0; i < size; i++) {
      RowOfTiles curr = screenOfTiles.get(i);
      int xLow = curr.getBlackBlockXLowerBound();
      int xUp = curr.getBlackBlockXUpperBound();
      int yLow = curr.getBlackBlockYLowerBound();
      int yUp = curr.getBlackBlockYUpperBound();
      if (eventX > xLow && eventX < xUp && eventY > yLow && eventY < yUp) {
        if (!curr.getGotBlack()) {
          updateGold(gold_increment);
        }
        curr.setGotBlack();
        result = true;
      }
    }
    return result;
  }

  /** draw my blocks */
  @Override
  public void draw(Canvas canvas) {
    int size = screenOfTiles.size();
    for (int i = 0; i < size; i++) {
      RowOfTiles curr = screenOfTiles.get(i);
      curr.draw(canvas);
    }
  }

  /** revive from using magical item */
  @Override
  public void useResurrectionKey() {
    setRunning(true);
    setAllToCorrect();
  }

  /**
   * Change the speed of the tile.
   *
   * @param s the new speed
   */
  private void setSpeed(int s) {
    speed = s;
  }

  /**
   * Set the acceleration of the tile.
   *
   * @param acc the acceleration
   */
  private void setAcceleration(int acc) {
    acceleration = acc;
  }

  /** Set all tiles to correctly clicked. */
  private void setAllToCorrect() {
    int size = screenOfTiles.size();
    for (int i = 0; i < size; i++) {
      RowOfTiles curr = screenOfTiles.get(i);
      curr.setGotBlack();
    }
  }

  /** Initialize the color of the tile. */
  @Override
  public void initColor() {
    int size = screenOfTiles.size();
    for (int i = 0; i < size; i++) {
      RowOfTiles curr = screenOfTiles.get(i);
      curr.initColor();
    }
  }

  /** set level for Dont Touch White Tiles game */
  @Override
  protected void setLevel(int level) {
    switch (level) {
      case 1:
        setSpeed(20);
        setAcceleration(5);
        gold_increment = 1;
        break;
      case 2:
        setSpeed(30);
        setAcceleration(8);
        gold_increment = 5;
        break;
      case 3:
        setSpeed(20);
        setAcceleration(8);
        gold_increment = 10;
        break;
    }
  }
}
