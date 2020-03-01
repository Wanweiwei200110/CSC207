package com.example.gamedesign.gameviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.activities.RankingActivity;
import com.example.gamedesign.gamestartactivity.JumpingBallActivityStart;
import com.example.gamedesign.jumpingball.JumpingBallController;
import com.example.gamedesign.systemmanager.FileSaverLoader;

/** Visual part of the jumping ball game. */
public class JumpingBallView extends GameSurfaceView implements SurfaceHolder.Callback {

  /** The tag of the view. */
  private static final String TAG = "JumpingBallView";

  /**
   * Construct a new view.
   *
   * @param context GameJumpingBallActivity
   * @param loggedUser user currently logged in
   * @param controller controller of the game
   */
  public JumpingBallView(
      Context context,
      User loggedUser,
      JumpingBallController controller,
      FileSaverLoader fileSaverLoader) {
    super(context, fileSaverLoader, loggedUser, controller);
    setFocusable(true);
    getHolder().addCallback(this);
    Log.i(TAG, "JumpingBallView: init");
    gameStatus = GameStatus.PLAYING;
  }

  /**
   * Move the bat to the x coord of where the screen is touched.
   *
   * @param event touch event
   */
  @Override
  public void onTouch(MotionEvent event) {
    gamePresenter.onTouch(event);
  }

  /** go to start game activity */
  @Override
  public void goToStartGameActivity() {
    Intent i = new Intent().setClass(getContext(), JumpingBallActivityStart.class);
    gamePresenter.resetGame();
    getContext().startActivity(i);
  }

  /** @return the string referring to the corresponding address of score board of the game */
  @Override
  public String getScoreBoardName() {
    return RankingActivity.JUMPINGBALL_SCORE_BOARD;
  }

  /** draw game */
  @Override
  protected void drawGame() {
    canvas.drawColor(Color.WHITE);
    gamePresenter.draw(canvas);
    drawScoreGold(canvas);
  }

  /** time sleep after display */
  @Override
  protected void sleep() {
    try {
      Thread.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
