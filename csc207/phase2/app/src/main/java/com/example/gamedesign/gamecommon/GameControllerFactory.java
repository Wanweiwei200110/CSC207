package com.example.gamedesign.gamecommon;

import com.example.gamedesign.donttouchwhitetiles.DontTouchWhiteTilesBuilder;
import com.example.gamedesign.hangman.HangmanController;
import com.example.gamedesign.jumpingball.JumpingBallBuilder;

import java.io.Serializable;

/** Factory for constructing controllers of the three games. */
public class GameControllerFactory implements Serializable {

  /** The width of the screen. */
  private int screenWidth;

  /** The height of the screen. */
  private int screenHeight;

  /**
   * Construct a new GameControllerFactory using the given screen width and height.
   *
   * @param screenWidth The width of the screen
   * @param screenHeight The height of the screen
   */
  public GameControllerFactory(int screenWidth, int screenHeight) {
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;
  }

  /**
   * Create a game controller according to the given type and difficulty level.
   *
   * @param gameControllerType the type of the game controller to be created
   * @param level indicating the difficulty of the game
   * @return a game controller with given type and level
   */
  public GameController createController(String gameControllerType, int level) {
    GameController gameController;
    if (gameControllerType == null) {
      return null;
    }
    if (level < 1 | level > 3) {
      return null;
    }
    if (gameControllerType.equalsIgnoreCase("HANGMAN")) {
      gameController = new HangmanController(screenWidth, screenHeight);
      gameController.setLevel(level);
      return gameController;
    } else if (gameControllerType.equalsIgnoreCase("JUMPINGBALL")) {
      JumpingBallBuilder jumpingBallBuilder = new JumpingBallBuilder();
      jumpingBallBuilder
          .setScreenX(screenWidth)
          .setScreenY(screenHeight)
          .setCenter()
          .setBatHeight();

      if (level == 1) {
        gameController = jumpingBallBuilder.setDivider(6).build();
      } else if (level == 2) {
        gameController = jumpingBallBuilder.setDivider(8).build();
      } else {
        gameController = jumpingBallBuilder.setDivider(10).build();
      }
      gameController.setLevel(level);
      return gameController;
    } else if (gameControllerType.equalsIgnoreCase("TILES")) {
      DontTouchWhiteTilesBuilder dontTouchWhiteTilesBuilder = new DontTouchWhiteTilesBuilder();
      if (level == 1 | level == 2) {
        gameController =
            dontTouchWhiteTilesBuilder
                .setScreenX(screenWidth)
                .setScreenY(screenHeight)
                .setCols(4)
                .setRows(6)
                .build();
      } else {
        gameController =
            dontTouchWhiteTilesBuilder
                .setScreenX(screenWidth)
                .setScreenY(screenHeight)
                .setCols(6)
                .setRows(6)
                .build();
      }
      gameController.setLevel(level);
      return gameController;
    }
    return null;
  }
}
