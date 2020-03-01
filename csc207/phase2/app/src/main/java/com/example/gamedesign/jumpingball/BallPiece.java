package com.example.gamedesign.jumpingball;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.gamedesign.gamecommon.GamePiece;
import com.example.gamedesign.serializableclass.MyPaint;

import java.io.Serializable;

/** The ball in JumpingBall Game. */
public class BallPiece extends GamePiece implements Serializable {

  /** The radius of the ball. */
  private int radius;

  /** The horizontal speed of the ball. */
  private int horSpeed;

  /** The vertical speed of the ball. */
  private int verSpeed;

  /** The paint of the ball. */
  private MyPaint paint;

  /**
   * Construct a new ball at (x, y) with the given radius.
   *
   * @param x the x coord of the ball.
   * @param y the y coord of the ball.
   * @param radius the radius of the ball.
   */
  BallPiece(int x, int y, int radius) {
    super(x, y);
    this.radius = radius;
    horSpeed = 0;
    verSpeed = 0;
    paint = new MyPaint();
    paint.setColor(Color.BLACK);
  }

  /**
   * Returen the radius of the ball.
   *
   * @return radius
   */
  int getRadius() {
    return radius;
  }

  /**
   * Set the ball's color.
   *
   * @param color integer representing the color of the ball
   */
  public void setColor(int color) {
    paint.setColor(color);
  }

  /**
   * Return the horizontal speed of the ball.
   *
   * @return horizontal speed
   */
  int getHorSpeed() {
    return horSpeed;
  }

  /**
   * Set the horizontal speed of the ball.
   *
   * @param speed horizontal speed
   */
  void setHorSpeed(int speed) {
    horSpeed = speed;
  }

  /**
   * Return the vertical speed of the ball.
   *
   * @return vertical speed
   */
  int getVerSpeed() {
    return verSpeed;
  }

  /**
   * Set the vertical speed of the ball.
   *
   * @param speed vertical speed
   */
  void setVerSpeed(int speed) {
    verSpeed = speed;
  }

  /** Change the position of the ball according to its speed. */
  void update() {
    setX(getX() + horSpeed);
    setY(getY() + verSpeed);
  }

  /** Stop the ball. */
  void setIsStopped(boolean status) {
    if (status) {
      horSpeed = 0;
      verSpeed = 0;
    }
  }

  /** Change the speed of the ball to increase difficulty (will be effective later) */
  void changeSpeed(int speed) {
    if (verSpeed < 0) {
      verSpeed -= speed;
    } else {
      verSpeed += speed;
    }
    if (horSpeed < 0) {
      horSpeed -= speed;
    } else {
      horSpeed += speed;
    }
  }

  /**
   * Draw the ball.
   *
   * @param canvas canvas to draw on
   */
  protected void draw(Canvas canvas) {
    canvas.drawCircle(getX(), getY(), radius, paint);
  }
}
