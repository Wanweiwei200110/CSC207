package com.example.gamedesign.scoringsystem;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.systemmanager.UserManager;

import java.io.Serializable;

/** a GameScore class */
public class GameScore implements Comparable<GameScore>, Serializable {

  /** score earned by the user in this game */
  private int score;

  /** the user who earned the score */
  private User user;

  /** a customizable name chosen by user */
  private String name;

  /**
   * constructing a gamescore with given name
   *
   * @param score the score earned by the user
   * @param name a name chosen by user to be displayed on scoreboard
   */
  GameScore(int score, String name) {
    this.score = score;
    this.name = name;
    user = UserManager.getInstance().getUser();
  }

  /** @return the score recorded in this GameScore */
  public int getScore() {
    return score;
  }

  /** @return the user who earned the score */
  public User getUser() {
    return user;
  }

  /** @return the name to be displayed on the scoreboard */
  public String getName() {
    return name;
  }

  /**
   * making GameScore object comparable to other GameScore object so that it can be sorted
   *
   * @param other GameScore
   */
  @Override
  public int compareTo(GameScore other) {
    return Integer.compare(score, other.getScore());
  }
}
