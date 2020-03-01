package com.example.gamedesign.gamestatusmanager;

import java.io.Serializable;

/** a score calculation class */
class ScoreCalc implements Serializable {

  /** total number of score you have in this game */
  private int score;

  /** start with 0 score */
  ScoreCalc() {
    score = 0;
  }

  /** @return total number of score you have in this game */
  int getScore() {
    return score;
  }

  /**
   * add i score
   *
   * @param i score
   */
  void updateScore(int i) {
    score += i;
  }
}
