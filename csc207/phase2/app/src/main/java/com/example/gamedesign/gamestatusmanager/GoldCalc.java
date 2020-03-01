package com.example.gamedesign.gamestatusmanager;

import java.io.Serializable;

/** a gold calculation class */
class GoldCalc implements Serializable {

  /** total number of gold you earn in this game */
  private int gold;

  /** start with 0 gold */
  GoldCalc() {
    gold = 0;
  }

  /** @return number of gold have by now */
  int getGold() {
    return gold;
  }

  /**
   * add i gold
   *
   * @param i gold
   */
  void updateGold(int i) {
    gold += i;
  }
}
