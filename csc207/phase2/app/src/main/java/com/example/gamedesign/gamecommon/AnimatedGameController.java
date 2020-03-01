package com.example.gamedesign.gamecommon;

import android.view.MotionEvent;

/** Controller for JumpingBall and DoNotTouchWhiteTiles */
public abstract class AnimatedGameController extends GameController {

  /** Boolean value indicating if the game is in progress. */
  protected boolean isRunning;

  /**
   * Initialize a game controller with given screen size.
   *
   * @param screenX width of the screen
   * @param screenY height of the screen
   */
  protected AnimatedGameController(int screenX, int screenY) {
    super(screenX, screenY);
  }

  /**
   * Return if thr game is running.
   *
   * @return boolean representing the game status
   */
  protected boolean isRunning() {
    return isRunning;
  }

  /**
   * Set the isRunning to state.
   *
   * @param state boolean representing the status of the game
   */
  protected void setRunning(boolean state) {
    isRunning = state;
  }

  /**
   * Main logic of the game.
   *
   * @param onGamePlayListener presenter for animated game
   */
  public abstract void logic(OnGamePlayListener onGamePlayListener);

  /**
   * Operations performed when the screen is touched.
   *
   * @param onGamePlayListener presenter for animated game
   * @param motionEvent motion detected
   */
  public abstract void onTouch(OnGamePlayListener onGamePlayListener, MotionEvent motionEvent);

  /** Initialize color. */
  public void initColor() {}
}
