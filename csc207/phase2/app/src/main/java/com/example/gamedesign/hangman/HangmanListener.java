package com.example.gamedesign.hangman;

public interface HangmanListener {

  /**
   * the user make one guess
   *
   * @param c the letter guessed
   */
  void guess(char c);

  /**
   * display the hint
   *
   * @return the hint
   */
  String setInitialHint();

  /** using one resurrention key */
  void help();

  /**
   * send a dialogue
   *
   * @param s what the dialogue says
   */
  void setAlert(String s);

  /** if the user wins */
  void wins();

  /**
   * set the image
   *
   * @param errorLeft how many attempts letf
   */
  void setImageView(int errorLeft);

  /**
   * Remind user how many times remains
   *
   * @param errorLeft attempts left
   */
  void setText(int errorLeft);

  /**
   * set the hint
   *
   * @param s what the hint is
   */
  void setHint(String s);

  /** set the guessBtn invisible */
  void invisibleGuess();

  /**
   * setting the gold and score
   *
   * @param gold the gold to be setted
   * @param score the score to be setted
   */
  void setScoreGold(int gold, int score);

  /**
   * get the score
   *
   * @return the score
   */
  int getScore();

  /** restart the game */
  void resetGame();

  /**
   * how many attempts left
   *
   * @return the number of attempts remains
   */
  int getErrorLeft();

  /**
   * update the hint
   *
   * @return the new hint
   */
  String newHint();

  /**
   * update gold to user
   *
   * @param i the number of gold to be added
   */
  void setNewGoldToUser(int i);
}
