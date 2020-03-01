package com.example.gamedesign.gamestatusmanager;

import java.io.Serializable;

/** a status manager class for managing gold and score data for user */
public class StatusManager implements Serializable {

  /** used to calculate score */
  private ScoreCalc scoreCalc;
  /** used to calculate gold */
  private GoldCalc goldCalc;

  /** create a new StatusManager with new scoreCalc and new goldCalc */
  public StatusManager() {
    scoreCalc = new ScoreCalc();
    goldCalc = new GoldCalc();
  }

  /** @return number of gold had by user in current game */
  public int getGold() {
    return goldCalc.getGold();
  }

  /** @return number of score earned by user in current game */
  public int getScore() {
    return scoreCalc.getScore();
  }

  /**
   * add i amount of score to current score
   *
   * @param i amount of score
   */
  public void updateScore(int i) {
    scoreCalc.updateScore(i);
  }
  /**
   * add i amount of gold to current score
   *
   * @param i amount of gold
   */
  public void updateGold(int i) {
    goldCalc.updateGold(i);
  }
}
