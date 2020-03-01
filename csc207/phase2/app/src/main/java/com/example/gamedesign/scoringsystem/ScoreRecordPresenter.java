package com.example.gamedesign.scoringsystem;

import com.example.gamedesign.scoringsysteminterface.OnRecordListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;

/** a ScoreRecordPresenter */
public class ScoreRecordPresenter implements OnRecordListener {

  /** a OnRecordListener */
  private OnRecordListener onRecordListener;

  /** a FileSaverLoader */
  private FileSaverLoader fileSaverLoader;

  /**
   * construct a ScoreRecordPresenter with onRecordListener, fileSaverLoader
   *
   * @param onRecordListener a OnRecordListener
   * @param fileSaverLoader a FileSaverLoader
   */
  public ScoreRecordPresenter(OnRecordListener onRecordListener, FileSaverLoader fileSaverLoader) {
    this.onRecordListener = onRecordListener;
    this.fileSaverLoader = fileSaverLoader;
  }

  /**
   * record game score with name and score
   *
   * @param name a name
   * @param score game score
   */
  @Override
  public void recordGameScore(String name, int score) {
    GameScore gameScore = new GameScore(score, name);
    String gameName = getScoreBoardName();
    ScoreBoard scoreBoard = fileSaverLoader.load(gameName);
    scoreBoard.recordScore(gameScore);
    fileSaverLoader.save(scoreBoard, gameName);
  }

  /** @return name of the score board */
  @Override
  public String getScoreBoardName() {
    return onRecordListener.getScoreBoardName();
  }
}
