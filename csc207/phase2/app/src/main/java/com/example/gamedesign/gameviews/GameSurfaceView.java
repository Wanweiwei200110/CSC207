package com.example.gamedesign.gameviews;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.gamecommon.AnimatedGameController;
import com.example.gamedesign.gamecommon.AnimatedGamePresenter;
import com.example.gamedesign.gamecommon.GameController;
import com.example.gamedesign.scoringsystem.ScoreRecordPresenter;
import com.example.gamedesign.scoringsysteminterface.OnRecordListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;

/** Parent class of the view of three games. */
public abstract class GameSurfaceView extends SurfaceView
    implements Runnable, SurfaceHolder.Callback, OnRecordListener {

  /** an AnimatedGamePresenter */
  protected AnimatedGamePresenter gamePresenter;
  /** The surface holder of the view. */
  SurfaceHolder mHolder;
  /** The context of the view. */
  Context context;
  /** the canvas given by this surfaceholder */
  Canvas canvas;
  /** If the game is running. */
  boolean isRunning = true;
  /** The thread of the view. */
  Thread t;
  /** The status of the game. */
  GameStatus gameStatus = GameStatus.PAUSED;
  /** the paint for painting score text. */
  private Paint scorePaint;
  /** the paint for painting gold text. */
  private Paint goldPaint;
  private ScoreRecordPresenter scoreRecordPresenter;
  /** handler for posting message */
  private myHandler handler;

  /**
   * Construct a GameSurfaceView using the given context.
   *
   * @param context the context given
   * @param fileSaverLoader object helping game saving and loading
   * @param loggedUser user currently logged in
   */
  public GameSurfaceView(
      Context context,
      FileSaverLoader fileSaverLoader,
      User loggedUser,
      GameController controller) {
    super(context, null, 0);
    this.context = context;
    mHolder = this.getHolder();
    mHolder.addCallback(this);
    handler = new myHandler(this);
    scoreRecordPresenter = new ScoreRecordPresenter(this, fileSaverLoader);
    gamePresenter =
        new AnimatedGamePresenter((AnimatedGameController) controller, this, loggedUser);
    setPaint();
    setZOrderOnTop(false);
  }

  /**
   * draw score and gold on the given canvas
   *
   * @param canvas the canvas to draw on
   */
  protected void drawScoreGold(Canvas canvas) {
    String score = "Score: " + gamePresenter.getScore();
    String gold = "Gold: " + gamePresenter.getGold();
    canvas.drawText(score, 50, 100, getScorePaint());
    canvas.drawText(gold, 50, 200, getGoldPaint());
  }

  /** @return score paint */
  protected Paint getScorePaint() {
    return scorePaint;
  }

  /** @return gold paint */
  protected Paint getGoldPaint() {
    return goldPaint;
  }

  /** set up Paints for scorePaint and goldPaint. */
  private void setPaint() {
    scorePaint = new Paint();
    scorePaint.setColor(Color.parseColor("#dea6b1"));
    scorePaint.setAntiAlias(true);
    scorePaint.setStrokeWidth(600);
    scorePaint.setTextSize(60);
    scorePaint.setStyle(Paint.Style.FILL);

    goldPaint = new Paint();
    goldPaint.setColor(Color.parseColor("#edcc0e"));
    goldPaint.setAntiAlias(true);
    goldPaint.setStrokeWidth(600);
    goldPaint.setTextSize(60);
    goldPaint.setStyle(Paint.Style.FILL);
  }

  /**
   * Operations performed when the view is created.
   *
   * @param holder the surface holder of the view.
   */
  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    t = new Thread(this);
    isRunning = true;
    t.start();
  }

  /**
   * changing surface for game
   *
   * @param holder the surface holder of the view.
   * @param format format of game
   * @param width width
   * @param height height
   */
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  /**
   * Operations performed when the view is destroyed.
   *
   * @param holder the surface holder of the view
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    isRunning = false;
  }

  /** Resume the game. */
  public void resume() {
    if (gameStatus == GameStatus.PAUSED) {
      gameStatus = GameStatus.PLAYING;
    }
  }

  /** Pause the game. */
  public void pause() {
    if (gameStatus == GameStatus.PLAYING) {
      gameStatus = GameStatus.PAUSED;
    }
  }

  /**
   * Operations performed when the screen is touched.
   *
   * @param event touching event
   */
  public abstract void onTouch(MotionEvent event);

  /** game logic */
  private void logic() {
    gamePresenter.logic();
  }

  /** draw game */
  protected abstract void drawGame();

  /** @return GameStatus */
  public GameStatus getGameStatus() {
    return gameStatus;
  }

  /** Draw the game pieces. */
  protected void draw() {
    try {
      canvas = mHolder.lockCanvas();
      if (canvas != null) {
        drawGame();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (canvas != null) mHolder.unlockCanvasAndPost(canvas);
    }
  }

  /** @return my customized handler */
  @Override
  public myHandler getHandler() {
    return handler;
  }

  /** create dialogue ask user to record score */
  public void createRecordScore() {
    handler.sendEmptyMessage(3);
  }

  /** create dialogue ask user to record score board */
  private void createRecordScoreDialogue() {
    final EditText editText = new EditText(getContext());
    editText.setSingleLine();
    editText.setHint("your name here");
    new AlertDialog.Builder(getContext())
        .setTitle("Would you like to upload your score?")
        .setMessage("Your name you would like to be displayed on the score board")
        .setView(editText)
        .setPositiveButton(
            "ok",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                String name = editText.getText().toString();
                gamePresenter.recordScore(name);
              }
            })
        .setNegativeButton(
            "Cancel",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                gamePresenter.notRecordScore();
              }
            })
        .show();
  }

  /** navigate to start game activity */
  public abstract void goToStartGameActivity();

  /** @return the string referring to the corresponding address of score board of the game */
  @Override
  public abstract String getScoreBoardName();

  /** action taken by the view whatsoever */
  @Override
  public void run() {
    while (isRunning) {
      logic();
      draw();
      sleep();
    }
  }

  /** abstract method time sleep after display */
  protected abstract void sleep();

  /** pump out if user does not have enough magical item */
  public void createNotEnough() {
    handler.sendEmptyMessage(2);
  }

  /** create dialogue ask user to user resurrection key to revive */
  private void createNotEnoughDialogue() {
    AlertDialog alertDialog =
        new AlertDialog.Builder(context)
            .setTitle("You don't have enough resurrection key to revive")
            .setMessage("Exit to menu?")
            .setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    gamePresenter.notRevive();
                  }
                })
            .setNegativeButton(
                "no, but you have to",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    gamePresenter.notRevive();
                  }
                })
            .create();
    alertDialog.setCanceledOnTouchOutside(false);
    alertDialog.show();
  }

  /** Create a dialogue when game is over. */
  public void createGameOver() {
    handler.sendEmptyMessage(1);
  }

  private void createGameOverDialogue() {
    AlertDialog alertDialog =
        new AlertDialog.Builder(context)
            .setTitle("GAME OVER")
            .setMessage("Revive using resurrection key?")
            .setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    gamePresenter.revive();
                  }
                })
            .setNegativeButton(
                "No and exit to menu",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    gamePresenter.notRevive();
                  }
                })
            .create();
    alertDialog.setCanceledOnTouchOutside(false);
    alertDialog.show();
  }

  /** change gamestatus to PLAYING */
  public void revive() {
    gameStatus = GameStatus.PLAYING;
  }

  /** change gamestatus to GAMEOVER */
  public void setGameStatusToGameOver() {
    gameStatus = GameStatus.GAMEOVER;
  }

  /**
   * use scoreRecordPresenter to record score
   *
   * @param name the name input by user
   * @param score score earned by user
   */
  @Override
  public void recordGameScore(String name, int score) {
    scoreRecordPresenter.recordGameScore(name, score);
  }

  /** Enum for three statuses of the game: playing, paused and gameover. */
  public enum GameStatus {
    PLAYING,
    PAUSED,
    GAMEOVER
  }

  /** a static class preventing memory leak handle gameover messages */
  protected static class myHandler extends Handler {
    GameSurfaceView view;

    /**
     * Construct a handler given the view.
     *
     * @param view the view given
     */
    myHandler(GameSurfaceView view) {
      this.view = view;
    }

    /**
     * Respond to the message received.
     *
     * @param msg the message sent by the view.
     */
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
        case 1:
          view.createGameOverDialogue();
          break;
        case 2:
          view.createNotEnoughDialogue();
          break;
        case 3:
          view.createRecordScoreDialogue();
          break;
      }
    }
  }
}
