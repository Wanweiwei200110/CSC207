package com.example.gamedesign.gamecommon;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.donttouchwhitetiles.DontTouchWhiteTilesController;
import com.example.gamedesign.gameviews.GameSurfaceView;
import com.example.gamedesign.jumpingball.JumpingBallController;

import java.io.Serializable;

/**
 * Presenter for animated game(donottouchwhitetiles, jumpingball), separating game controller and
 * game view.
 */
public class AnimatedGamePresenter implements OnGamePlayListener, Serializable {

  /** Controller of the game. */
  private AnimatedGameController gameController;

  /** Surface view of the game. */
  private GameSurfaceView gameSurfaceView;

  /** User currently logged in. */
  private User loggedUser;

  /**
   * Construct a new AnimatedGamePresenter using the given controller, surface view and logged user.
   *
   * @param gameController controller of the game
   * @param gameSurfaceView surface view of the game
   * @param loggedUser user currently logged in
   */
  public AnimatedGamePresenter(
      AnimatedGameController gameController, GameSurfaceView gameSurfaceView, User loggedUser) {
    this.gameController = gameController;
    this.gameSurfaceView = gameSurfaceView;
    this.loggedUser = loggedUser;
  }

  /** Create dialogue when the game is over. */
  @Override
  public void gameOverDialogue() {
    gameSurfaceView.setGameStatusToGameOver();
    gameSurfaceView.createGameOver();
  }

  /** Revive using resurrection key. */
  @Override
  public void revive() {
    int resurrectionKey = loggedUser.getResurrectionKey();
    if (resurrectionKey > 0) {
      gameController.useResurrectionKey();
      loggedUser.setResurrectionKey(resurrectionKey - 1);
      gameSurfaceView.revive();
    } else {
      gameSurfaceView.createNotEnough();
    }
  }

  /**
   * Draw all the game pieces.
   *
   * @param canvas canvas to draw on
   */
  @Override
  public void draw(Canvas canvas) {
    gameController.draw(canvas);
  }

  /** Calculate and record score and gold when player choose not to revive. */
  @Override
  public void notRevive() {
    int user_have = loggedUser.getGold();
    int game_earned = gameController.getGold();
    int total_gold = user_have + game_earned;
    loggedUser.setGold(total_gold);
    gameSurfaceView.createRecordScore();
  }

  /**
   * Record the score in the game.
   *
   * @param name the name the user wish to display on leader board.
   */
  @Override
  public void recordScore(String name) {
    gameSurfaceView.recordGameScore(name, gameController.getScore());
    gameSurfaceView.goToStartGameActivity();
  }

  /** Go to the game start activity if the user choose not to revive. */
  @Override
  public void notRecordScore() {
    gameSurfaceView.goToStartGameActivity();
  }

  /**
   * Operations performed when the screen is touched.
   *
   * @param motionEvent motion detected
   */
  @Override
  public void onTouch(MotionEvent motionEvent) {
    gameController.onTouch(this, motionEvent);
  }

  /** Main logic of the game. */
  @Override
  public void logic() {
    if (gameSurfaceView.getGameStatus() == GameSurfaceView.GameStatus.PLAYING) {
      gameController.logic(this);
    }
  }

  /**
   * Return score in the game.
   *
   * @return score in the game
   */
  @Override
  public int getScore() {
    return gameController.getScore();
  }

  /**
   * Return gold earned in the game.
   *
   * @return gold earned in the game
   */
  @Override
  public int getGold() {
    return gameController.getGold();
  }

  /** Initialize color. */
  @Override
  public void initColor() {
    gameController.initColor();
  }

  /** Reset the game. */
  @Override
  public void resetGame() {
    if (gameController instanceof DontTouchWhiteTilesController) {
      loggedUser.resetBlock(1);
    }
    if (gameController instanceof JumpingBallController) {
      loggedUser.resetJumpingBall(1);
    }
  }
}
