package com.example.gamedesign.scoringsysteminterface;

/** a OnRecordListener interface */
public interface OnRecordListener {

  /**
   * record game score with name and score
   *
   * @param name a name
   * @param score game score
   */
  void gi(String name, int score);

  /** @return name of the score board */
  String getScoreBoardName();
}
