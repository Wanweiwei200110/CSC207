package com.example.gamedesign.scoringsystem;

import com.example.gamedesign.accountmanager.User;

import java.io.Serializable;
import java.util.List;

/** A strategy for returning the highest rank of current user on the scoreboard */
class RankStrategy implements Serializable {

  /**
   * returning the highest rank of current user on the scoreboard
   *
   * @param scoreList the list of GameScore
   * @param currentUser the user who currently logged in
   * @return returning the highest rank of current user on the scoreboard
   */
  int getUserRank(List<GameScore> scoreList, User currentUser) {
    if (scoreList.size() == 0) {
      return -1;
    }
    int size = scoreList.size();
    for (int i = 0; i < size; i++) {
      if (scoreList.get(i).getUser().getUsername().equals(currentUser.getUsername())) {
        return i + 1;
      }
    }
    return size + 1;
  }
}
