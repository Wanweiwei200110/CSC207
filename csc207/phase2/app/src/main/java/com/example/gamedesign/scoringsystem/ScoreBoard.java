package com.example.gamedesign.scoringsystem;

import com.example.gamedesign.scoringsysteminterface.OnDisplayListener;
import com.example.gamedesign.systemmanager.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** a ScoreBoard for ranking user's game score */
class ScoreBoard implements Serializable {

  /** a list of gameScore */
  private List<GameScore> scoreList;

  /** a RankStrategy for game player's game score ranking */
  private RankStrategy rankStrategy;

  /**
   * constructing a new ScoreBoard
   *
   * @param scoreList game score list
   */
  ScoreBoard(List<GameScore> scoreList) {
    rankStrategy = new RankStrategy();
    this.scoreList = scoreList;
  }

  /**
   * load game score ranking
   *
   * @param onDisplayListener a OnDisplayListener
   */
  void loadGameRank(OnDisplayListener onDisplayListener) {
    onDisplayListener.setScoreBoard(getLeaderBoard());
    onDisplayListener.setUserField(getUserRank(), getUserHighestScore());
  }

  /**
   * record game score
   *
   * @param score GameScore
   */
  void recordScore(GameScore score) {
    scoreList.add(score);
    Collections.sort(scoreList, Collections.reverseOrder());
  }

  /**
   * get leader board
   *
   * @return list of GameScore
   */
  private List<GameScore> getLeaderBoard() {
    Collections.sort(scoreList, Collections.reverseOrder());
    if (scoreList.size() >= 10) {
      return new ArrayList<GameScore>(scoreList.subList(0, 10));
    } else {
      return scoreList;
    }
  }

  /**
   * get user's game score rank
   *
   * @return user's game score rank
   */
  private int getUserRank() {
    return rankStrategy.getUserRank(scoreList, UserManager.getInstance().getUser());
  }

  /**
   * get user's highest game score
   *
   * @return user's highest score
   */
  private int getUserHighestScore() {
    int rank = getUserRank();
    if (rank == -1) {
      return 0;
    }
    if (rank > scoreList.size()) {
      return 0;
    } else {
      return scoreList.get(rank - 1).getScore();
    }
  }
}
