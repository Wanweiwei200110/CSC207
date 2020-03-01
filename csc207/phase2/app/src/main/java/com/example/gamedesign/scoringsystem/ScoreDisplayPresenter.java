package com.example.gamedesign.scoringsystem;

import com.example.gamedesign.scoringsysteminterface.OnDisplayListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;

import java.util.List;

/** a ScoreDisplayPresenter */
public class ScoreDisplayPresenter implements OnDisplayListener {

  /** a FileSaverLoader */
  private FileSaverLoader fileSaverLoader;

  /** a OnDisplayListener */
  private OnDisplayListener onDisplayListener;

  /**
   * construct a ScoreDisplayPresenter with onDisplayListener and fileSaverLoader
   *
   * @param onDisplayListener a OnDisplayListener
   * @param fileSaverLoader a FileSaverLoader
   */
  public ScoreDisplayPresenter(
      OnDisplayListener onDisplayListener, FileSaverLoader fileSaverLoader) {
    this.fileSaverLoader = fileSaverLoader;
    this.onDisplayListener = onDisplayListener;
  }

  /**
   * load game score ranking
   *
   * @param scoreBoardName name for the score board
   */
  @Override
  public void loadGameRank(String scoreBoardName) {
    Object scoreBoard = fileSaverLoader.load(scoreBoardName);
    ((ScoreBoard) scoreBoard).loadGameRank(onDisplayListener);
  }

  /**
   * set score board ranking
   *
   * @param leaderBoard a GameScore list
   */
  @Override
  public void setScoreBoard(List<GameScore> leaderBoard) {
    onDisplayListener.setScoreBoard(leaderBoard);
  }

  /**
   * set user field
   *
   * @param rank rank of the score board
   * @param score game score
   */
  @Override
  public void setUserField(int rank, int score) {
    onDisplayListener.setUserField(rank, score);
  }
}
