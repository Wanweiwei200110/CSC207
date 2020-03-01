package com.example.gamedesign.accountmanager;

import android.content.res.Resources;

import com.example.gamedesign.donttouchwhitetiles.DontTouchWhiteTilesController;
import com.example.gamedesign.gamecommon.GameControllerFactory;
import com.example.gamedesign.hangman.HangmanController;
import com.example.gamedesign.jumpingball.JumpingBallController;

import java.io.Serializable;

/** A class stored by User Response to the action of user playing game */
class UserGameControllerManager implements Serializable {
  /** Hangman game controller */
  private HangmanController hangmanController;

  /** Don'tTouchWhiteTiles game controller */
  private DontTouchWhiteTilesController dontTouchWhiteTilesController;

  /** JumpingBall game controller */
  private JumpingBallController jumpingBallController;

  /** screen width and height */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** Factory for constructing gamecontrollers */
  private GameControllerFactory gameControllerFactory;

  /** constructing a new UserGameControllerManager */
  UserGameControllerManager() {

    setScreenWidthAndHeight();
    gameControllerFactory = new GameControllerFactory(screenWidth, screenHeight);
    setDefaultControllers();
  }

  /** set screenWidth and screenHeight for initializing GameControllerFactory */
  private void setScreenWidthAndHeight() {
    screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  /** set the default game controllers */
  private void setDefaultControllers() {
    resetHangman();
    resetJumpingBall();
    resetBlock();
  }

  /** reset a new don't touch white tiles controller to level 1 */
  private void resetBlock() {
    resetBlock(1);
  }

  /** reset a new hangman controller to level 1 */
  private void resetHangman() {
    resetHangman(1);
  }

  /** reset a new jumping ball controller to level 1 */
  private void resetJumpingBall() {
    resetJumpingBall(1);
  }

  /** reset a new don't touch white tiles controller to level 1 */
  void resetBlock(int level) {
    dontTouchWhiteTilesController =
        (DontTouchWhiteTilesController) gameControllerFactory.createController("TILES", level);
  }

  /** reset a new hangman controller to level 1 */
  void resetHangman(int level) {
    hangmanController =
        (HangmanController) gameControllerFactory.createController("HANGMAN", level);
  }

  /** reset a new jumping ball controller to level 1 */
  void resetJumpingBall(int level) {
    jumpingBallController =
        (JumpingBallController) gameControllerFactory.createController("JUMPINGBALL", level);
  }

  /** @return * get Don't touch white tiles Controller of the user */
  DontTouchWhiteTilesController getDontTouchWhiteTilesController() {
    return dontTouchWhiteTilesController;
  }

  /** @return get JumpingBall Controller of the user */
  JumpingBallController getJumpingBallController() {
    return jumpingBallController;
  }

  /** @return get Hangman Controller of the user */
  HangmanController getHangmanController() {
    return hangmanController;
  }
}
