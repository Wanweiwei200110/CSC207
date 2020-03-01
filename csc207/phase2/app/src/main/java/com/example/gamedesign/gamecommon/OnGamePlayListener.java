package com.example.gamedesign.gamecommon;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * A presenter for animated game (donottouchwhitetile and jumping ball), separating game controller
 * and game view.
 */
public interface OnGamePlayListener {

  /** Create dialogue when the game is over. */
  void gameOverDialogue();

  /** Revive using resurrection key. */
  void revive();

  /** Operations performed when the user choose not to revive. */
  void notRevive();

  /**
   * Record score in the game.
   *
   * @param name the name the user wish to display on leader board.
   */
  void recordScore(String name);

  /** Operations performed when the user choose not to record the score. */
  void notRecordScore();

  /**
   * Operations performed when the screen is touched.
   *
   * @param motionEvent motion detected
   */
  void onTouch(MotionEvent motionEvent);

  /** Main logic of the game. */
  void logic();

  /**
   * Draw all the game pieces.
   *
   * @param canvas canvas to draw on
   */
  void draw(Canvas canvas);

  /**
   * Return score in the game.
   *
   * @return score in the game
   */
  int getScore();

  /**
   * Return gold earned in the game.
   *
   * @return gold earned in the game
   */
  int getGold();

  /** Initialize color. */
  void initColor();

  /** Reset the game. */
  void resetGame();
}
