package com.example.gamedesign.hangman;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.gameactivity.GameHangManActivity;

/** a class of HangmanPresenter */
public class HangmanPresenter implements HangmanListener {

  /** the hangman game activity */
  private GameHangManActivity gameHangManActivity;

  /** the hangman game controller */
  private HangmanController hangmanController;

  /** the currently logged in user */
  private User loggedUser;

  /**
   * the constructor of the presenter
   *
   * @param gameHangManActivity the hangman game activity
   * @param hangmanController the hangman game controller
   * @param user the logged in user
   */
  public HangmanPresenter(
      GameHangManActivity gameHangManActivity, HangmanController hangmanController, User user) {
    this.hangmanController = hangmanController;
    this.gameHangManActivity = gameHangManActivity;
    loggedUser = user;
  }

  /**
   * the user make one guess
   *
   * @param c the letter guessed
   */
  @Override
  public void guess(char c) {
    hangmanController.guess(c, this);
  }

  /**
   * display the hint
   *
   * @return the hint
   */
  @Override
  public String setInitialHint() {
    return hangmanController.setInitialHint();
  }

  /** using one resurrention key */
  @Override
  public void help() {

    int resurrectionKey = loggedUser.getResurrectionKey();
    if (resurrectionKey > 0) {
      hangmanController.useResurrectionKey();
      loggedUser.setResurrectionKey(resurrectionKey - 1);
      hangmanController.help(this);
    } else {
      gameHangManActivity.setAlert("You don't have enought resurrection key.");
    }
  }

  /**
   * send a dialogue
   *
   * @param s what the dialogue says
   */
  @Override
  public void setAlert(String s) {
    gameHangManActivity.setAlert(s);
  }

  /** if the user wins */
  @Override
  public void wins() {
    gameHangManActivity.wins();
  }

  /**
   * set the image
   *
   * @param errorLeft how many attempts letf
   */
  @Override
  public void setImageView(int errorLeft) {
    gameHangManActivity.setImageView(errorLeft);
    setText(errorLeft);
  }

  /**
   * Remind user how many times remains
   *
   * @param errorLeft attempts left
   */
  @Override
  public void setText(int errorLeft) {
    gameHangManActivity.setText(errorLeft);
  }

  /**
   * set the hint
   *
   * @param s what the hint is
   */
  @Override
  public void setHint(String s) {
    gameHangManActivity.setHint(s);
  }
  /** set the guessBtn invisible */
  @Override
  public void invisibleGuess() {
    gameHangManActivity.setInvisibleGuess();
  }
  /**
   * setting the gold and score
   *
   * @param gold the gold to be setted
   * @param score the score to be setted
   */
  @Override
  public void setScoreGold(int gold, int score) {
    gameHangManActivity.updateGoldAndScore(gold, score);
  }

  /**
   * get the score
   *
   * @return the score
   */
  @Override
  public int getScore() {
    return hangmanController.getScore();
  }

  /** begin a new game */
  @Override
  public void resetGame() {
    loggedUser.resetHangman(1);
  }

  /**
   * how many attempts left
   *
   * @return the number of attempts remains
   */
  @Override
  public int getErrorLeft() {
    return hangmanController.getErrorLeft();
  }

  /**
   * update the hint
   *
   * @return the new hint
   */
  @Override
  public String newHint() {
    return hangmanController.updateHint();
  }

  /**
   * update gold to user
   *
   * @param new_gold the number of gold to be added
   */
  @Override
  public void setNewGoldToUser(int new_gold) {
    gameHangManActivity.setNewGoldToUser(new_gold);
  }
}
