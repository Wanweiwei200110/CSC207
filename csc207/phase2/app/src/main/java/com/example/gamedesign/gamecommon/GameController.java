package com.example.gamedesign.gamecommon;

import android.graphics.Canvas;

import com.example.gamedesign.gamestatusmanager.StatusManager;

import java.io.Serializable;

/** Controller of the game, parent class for the controller of three games. */
public abstract class GameController implements Serializable {

  /** The width of the screen. */
  protected int screenX;

  /** The height of the screen. */
  protected int screenY;

  /** Status manager managing score and gold. */
  private StatusManager statusManager;

  /**
   * Initialize a game controller with given screen size.
   *
   * @param screenX width of the screen
   * @param screenY height of the screen
   */
  protected GameController(int screenX, int screenY) {
    this.screenX = screenX;
    this.screenY = screenY;
    statusManager = new StatusManager();
  }

  /**
   * Add the gold earned to user's gold.
   *
   * @param i the number of gold earned
   */
  protected void updateGold(int i) {
    statusManager.updateGold(i);
  }

  /**
   * Add the score to user's score.
   *
   * @param i the score got in the game.
   */
  protected void updateScore(int i) {
    statusManager.updateScore(i);
  }

  /**
   * Draw the game.
   *
   * @param canvas the canvas to draw on
   */
  protected abstract void draw(Canvas canvas);

  /**
   * Return the gold earned in the game.
   *
   * @return number of gold earned
   */
  public int getGold() {
    return statusManager.getGold();
  }

  /**
   * Return the score got in the game.
   *
   * @return the score in the game
   */
  public int getScore() {
    return statusManager.getScore();
  }

  /** Use magical item. */
  protected abstract void useResurrectionKey();

  /**
   * Set the game according to the difficulty level.
   *
   * @param level integer value representing the difficulty of the game
   */
  protected abstract void setLevel(int level);
}
