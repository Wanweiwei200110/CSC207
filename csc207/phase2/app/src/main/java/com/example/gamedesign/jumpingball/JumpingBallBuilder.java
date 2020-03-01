package com.example.gamedesign.jumpingball;

/** Builder for JumpingBallController. */
public class JumpingBallBuilder {
  /** The width of the screen. */
  private int screenX;

  /** The height of the screen. */
  private int screenY;

  /** The ball in the game. */
  private BallPiece ball;

  /** The bat in the game. */
  private BatPiece bat;

  /** The number that screenX divides to get the width of the bat. */
  private int divider;

  /** Half of the screen width. */
  private int center;

  /** The height of the bat. */
  private int batHeight;

  /** Construct a new JumpingBallBuilder. */
  public JumpingBallBuilder() {}

  /**
   * Store the screen width.
   *
   * @param x screen width
   * @return JumpingBallBuilder
   */
  public JumpingBallBuilder setScreenX(int x) {
    screenX = x;
    return this;
  }

  /**
   * Store the screen height.
   *
   * @param y screen height
   * @return JumpingBallBuilder
   */
  public JumpingBallBuilder setScreenY(int y) {
    screenY = y;
    return this;
  }

  /**
   * Store half of the screen width.
   *
   * @return JumpingBallBuilder
   */
  public JumpingBallBuilder setCenter() {
    center = screenX / 2;
    return this;
  }

  /**
   * Set the height of the bat to be 1/10 of the screen height.
   *
   * @return Builder
   */
  public JumpingBallBuilder setBatHeight() {
    batHeight = screenY / 10;
    return this;
  }

  /**
   * Set the number that screenX divides to get the width of the bat.
   *
   * @param divider divider
   * @return Builder
   */
  public JumpingBallBuilder setDivider(int divider) {
    this.divider = divider;
    return this;
  }

  /** Create and store the ball used in the game. */
  private void createBall() {
    ball = new BallPiece(center, screenY - batHeight - (screenY / 50), screenY / 50);
  }

  /** Create and store the bat used in the game. */
  private void createBat() {
    int halfBlockWidth = screenX / divider;
    bat =
        new BatPiece(
            center,
            screenY - (batHeight / 2),
            center - halfBlockWidth,
            screenY - batHeight,
            center + halfBlockWidth,
            screenY);
  }

  /**
   * Build the controller.
   *
   * @return JumpingBallController
   */
  public JumpingBallController build() {
    createBall();
    createBat();
    return new JumpingBallController(screenX, screenY, ball, bat);
  }
}
