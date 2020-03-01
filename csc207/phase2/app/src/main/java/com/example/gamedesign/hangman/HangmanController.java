package com.example.gamedesign.hangman;

import android.graphics.Canvas;

import com.example.gamedesign.gamecommon.GameController;

import java.io.Serializable;

/** a hangman controller class */
public class HangmanController extends GameController implements Serializable {

  /** a instance of Judgingwords */
  private JudgingWords judgingWords;

  /** the scale of how much gold or score will be added */
  private int scoreIncrement;

  /** total wrong attempts */
  private int errorLeft = 6;

  /**
   * Construct a game controller with given screen size
   *
   * @param screen_x width of the screen
   * @param screen_y height of the screen
   */
  public HangmanController(int screen_x, int screen_y) {
    super(screen_x, screen_y);
    judgingWords = new JudgingWords();
    scoreIncrement = 10;
  }

  /**
   * get the remaining attempts
   *
   * @return remaining attempts
   */
  int getErrorLeft() {
    return errorLeft;
  }

  /**
   * the user guesses one letter
   *
   * @param c the letter user guessed
   * @param hangmanPresenter a hangman presenter
   */
  void guess(char c, HangmanPresenter hangmanPresenter) {
    hangmanPresenter.setHint(updateHint());
    if (ifWin()) {
      updateScore(scoreIncrement * errorLeft);
      updateGold(scoreIncrement * errorLeft);
      hangmanPresenter.wins();
      hangmanPresenter.setScoreGold(getGold(), getScore());
      hangmanPresenter.setNewGoldToUser(scoreIncrement * errorLeft);
      return;
    }
    if (ifHaveGuessed(c)) {
      hangmanPresenter.setAlert("You have guessed this letter!!");
    }

    if (ifRightCharcter(c)) {
      hangmanPresenter.setHint(updateHint());
      updateScore(scoreIncrement * errorLeft);
      if (ifWin()) {
        updateGold(scoreIncrement * errorLeft);
        hangmanPresenter.wins();
      } else {
        hangmanPresenter.setAlert("You guess a right letter, go on!!");
      }
    } else {
      wrongguess();
      hangmanPresenter.setImageView(getErrorLeft());
      hangmanPresenter.setText(getErrorLeft());
      if (errorLeft <= 0) {
        hangmanPresenter.setAlert("You lose!!");
        hangmanPresenter.invisibleGuess();
        hangmanPresenter.setAlert("The answer is " + judgingWords.getSecret());
      }
    }

    hangmanPresenter.setScoreGold(getGold(), getScore());
    hangmanPresenter.setNewGoldToUser(getGold());
  }

  /**
   * the user needs help
   *
   * @param hangmanPresenter the hangman presenter
   */
  void help(HangmanPresenter hangmanPresenter) {
    useResurrectionKey();
    updateScore(scoreIncrement * errorLeft);
    hangmanPresenter.setAlert("You have used one resurrection key!!");
    hangmanPresenter.setHint(updateHint());
  }

  /** one wrong guess */
  private void wrongguess() {
    errorLeft -= 1;
  }

  /**
   * Check if the letter user guessed is right
   *
   * @param c the letter that the user guessed
   * @return if the user is right
   */
  private boolean ifRightCharcter(char c) {
    return judgingWords.ifCorrectCharacter(c);
  }

  /**
   * Check if the letter user has guessed before
   *
   * @param c the letter that the user guessed
   * @return if the user guessed it before
   */
  private boolean ifHaveGuessed(char c) {
    return judgingWords.ifHaveGuessed(c);
  }

  /**
   * Check if the user guesses the whole word
   *
   * @return if the user wins
   */
  private boolean ifWin() {
    return judgingWords.ifWin();
  }

  /**
   * Display the hint
   *
   * @return the hint
   */
  String setInitialHint() {
    return judgingWords.setInitialHint();
  }

  /**
   * Display new hints
   *
   * @return the hint to be displayed
   */
  String updateHint() {
    return judgingWords.updateHint();
  }

  /**
   * implement the abstract method
   *
   * @param canvas the canvas to draw on
   */
  @Override
  public void draw(Canvas canvas) {}

  /** using one resurrention key */
  @Override
  public void useResurrectionKey() {
    judgingWords.correctAtOnce();
  }

  /**
   * the level the user chooses
   *
   * @param level integer value representing the difficulty of the game
   */
  @Override
  protected void setLevel(int level) {
    scoreIncrement = 10 * level;
    judgingWords.setLevel(level);
  }
}
