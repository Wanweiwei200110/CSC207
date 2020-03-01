package com.example.gamedesign.jumpingball;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.gamedesign.gamecommon.AnimatedGameController;
import com.example.gamedesign.gamecommon.OnGamePlayListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** The controller of the game JumpingBall that controls the logic of the game. */
public class JumpingBallController extends AnimatedGameController {

  /** The ball used in the game. */
  private BallPiece ball;

  /** The bat of the game. */
  private BatPiece bat;

  /** Arraylist containing all death dropping items. */
  private List<DeathDroppingItem> deathItems = new ArrayList<>();

  /** Arraylist containing all speed dropping items. */
  private List<SpeedDroppingItem> speedItems = new ArrayList<>();

  /** The difficulty of the game(will be effective later). */
  private int difficulty = 1;

  /** The level controlling the probability of the occurrence of falling items. */
  private int fallingItemProb;

  /**
   * Construct a new JumpingBallController using the following parameters passed in.
   *
   * @param screenX the width of the screen
   * @param screenY the height of the screen
   * @param ball the ball in the game
   * @param bat the bat in the game
   */
  JumpingBallController(int screenX, int screenY, BallPiece ball, BatPiece bat) {
    super(screenX, screenY);
    this.ball = ball;
    this.bat = bat;
  }

  /**
   * Determine if the ball touches the bat.
   *
   * @return true iff the ball touches the bat
   */
  private boolean isBallCaught() {
    if (((ball.getY() - ball.getRadius()) < bat.getRect().bottom - 100
        && (ball.getY() + ball.getRadius()) >= bat.getRect().top)) {
        return ball.getX() <= bat.getRect().right && ball.getX() >= bat.getRect().left;
    }
    return false;
  }

  /** Revive from using resurrection key */
  @Override
  public void useResurrectionKey() {
    setRunning(true);
    shootBall();
  }

  /**
   * Generate new falling item according to the input. Helper for method controlFallingItem.
   *
   * @param type decide the type of the item to be generated
   */
  private void createFallingItem(String type) {
    switch (type) {
      case "death":
        deathItems.add(
            new DeathDroppingItem(
                new Random().nextInt(screenX), new Random().nextInt(screenY - 300)));
        break;
      case "speed":
        speedItems.add(
            new SpeedDroppingItem(
                new Random().nextInt(screenX), new Random().nextInt(screenY - 300)));
        break;
    }
  }

  /** Create new falling item according to fallingItemProb. */
  private void controlFallingItem() {
    double decision = Math.random();
    switch (fallingItemProb) {
      case 1:
        if (decision <= 0.004) createFallingItem("death");
        if (decision >= 0.996) createFallingItem("speed");
        break;

      case 2:
        if (decision <= 0.005) createFallingItem("death");
        if (decision >= 0.995) createFallingItem("speed");
        break;

      case 3:
        if (decision <= 0.006) createFallingItem("death");
        if (decision >= 0.992) createFallingItem("speed");
        break;
    }
  }

  /** The main logic of the game. */
  @Override
  public void logic(OnGamePlayListener onGamePlayListener) {
    if (!isRunning()) {
      shootBall();
      setRunning(true);
    }
    controlFallingItem();
    ballMove(onGamePlayListener);
    if ((isDeathCaught())) {
      ball.setIsStopped(true);
      setRunning(false);
      onGamePlayListener.gameOverDialogue();
    }
    if (isSpeedCaught()) {
      ball.changeSpeed(2 * difficulty++);
      updateScore(10);
      if (getScore() % 30 == 0) {
        updateGold(10);
      }
    }
    update();
  }

  /**
   * Determine if the death item is caught by the bat.
   *
   * @return true iff the death item touches the bat
   */
  private boolean isDeathCaught() {
    if (deathItems.size() == 0) {
      return false;
    } else {
      for (DeathDroppingItem deathItem : deathItems) {
        if ((deathItem.getY() < bat.getRect().bottom - 120 && deathItem.getY() > bat.getRect().top)
            && (deathItem.getX() <= bat.getRect().right
                && deathItem.getX() >= bat.getRect().left)) {
          deathItems.remove(deathItem);
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Determine if the speed item is caught by the bat.
   *
   * @return true iff the speed item touches the bat
   */
  private boolean isSpeedCaught() {
    if (speedItems.size() == 0) {
      return false;
    } else {
      for (SpeedDroppingItem speedItem : speedItems) {
        if ((speedItem.getY() < bat.getRect().bottom - 120 && speedItem.getY() > bat.getRect().top)
            && (speedItem.getX() <= bat.getRect().right
                && speedItem.getX() >= bat.getRect().left)) {
          speedItems.remove(speedItem);
          return true;
        }
      }
      return false;
    }
  }

  /** Let the ball start moving. */
  private void shootBall() {
    ball.setX(screenX / 2);
    ball.setY(screenY / 2 - 200);
    ball.setVerSpeed(25);
    ball.setHorSpeed(-30);
    ball.setIsStopped(false);
  }

  /** Control the movement of the ball. */
  private void ballMove(OnGamePlayListener onGamePlayListener) {
    int radius = ball.getRadius();
    if ((ball.getX() + radius >= screenX) || (ball.getX() - radius <= 0)) {
      ball.setHorSpeed(-1 * ball.getHorSpeed());
    } else if (ball.getY() - radius < 10) {
      ball.setVerSpeed(-1 * ball.getVerSpeed());
    } else if (ball.getY() + radius >= screenY - 10) {
      ball.setIsStopped(true);
      setRunning(false);
      onGamePlayListener.gameOverDialogue();
    } else if (isBallCaught()) {
      ball.setVerSpeed(-1 * ball.getVerSpeed());
    }
  }

  /** Update the location of ball and falling items. */
  private void update() {
    ball.update();
    for (SpeedDroppingItem speedItem : speedItems) {
      speedItem.update();
    }
    for (DeathDroppingItem deathItem : deathItems) {
      deathItem.update();
    }
  }

  /**
   * Change the location of the bat based on the touch event.
   *
   * @param onGamePlayListener animated game presenter
   * @param motionEvent motion event controlling the bat position
   */
  @Override
  public void onTouch(OnGamePlayListener onGamePlayListener, MotionEvent motionEvent) {
    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN
        || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
      batMove(motionEvent.getX());
    }
  }

  /** Initialize color. */
  @Override
  public void initColor() {}

  /**
   * Control the movement of the bat.
   *
   * @param x the x position the bat is moved to
   */
  private void batMove(float x) {
    bat.update((int) x);
  }

  /**
   * Draw the ball and the bat.
   *
   * @param canvas the canvas to draw on
   */
  public void draw(Canvas canvas) {
    for (SpeedDroppingItem speedItem : speedItems) {
      speedItem.draw(canvas);
    }
    for (DeathDroppingItem deathItem : deathItems) {
      deathItem.draw(canvas);
    }

    ball.draw(canvas);
    bat.draw(canvas);
  }

  /**
   * Set fallingItemProb according to level
   *
   * @param level the difficulty level of the game
   */
  @Override
  protected void setLevel(int level) {
    fallingItemProb = level;
  }
}
