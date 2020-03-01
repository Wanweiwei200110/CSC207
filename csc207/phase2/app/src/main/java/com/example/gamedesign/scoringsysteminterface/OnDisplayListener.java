package com.example.gamedesign.scoringsysteminterface;

import com.example.gamedesign.scoringsystem.GameScore;

import java.util.List;

/** a OnDisplayListener */
public interface OnDisplayListener {

  /**
   * load game score ranking
   *
   * @param scoreBoardName name for the score board
   */
  void loadGameRank(String scoreBoardName);

  /**
   * set score board ranking
   *
   * @param leaderBoard a GameScore list
   */
  void setScoreBoard(List<GameScore> leaderBoard);

  /**
   * set user field
   *
   * @param rank rank of the score board
   * @param score game score
   */
  void setUserField(int rank, int score);
}
