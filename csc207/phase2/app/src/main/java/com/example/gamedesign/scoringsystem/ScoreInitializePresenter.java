package com.example.gamedesign.scoringsystem;

import com.example.gamedesign.scoringsysteminterface.OnInitializeListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;

import java.util.ArrayList;

/** a ScoreInitializePresenter */
public class ScoreInitializePresenter implements OnInitializeListener {

  /** a FileSaverLoader */
  private FileSaverLoader fileSaverLoader;

  /**
   * construct a ScoreInitializePresenter with fileSaverLoader
   *
   * @param fileSaverLoader a FileSaverLoader
   */
  public ScoreInitializePresenter(FileSaverLoader fileSaverLoader) {
    this.fileSaverLoader = fileSaverLoader;
  }

  /**
   * initialize score board
   *
   * @param scoreBoardName name for score board
   */
  @Override
  public void initScoreBoard(String scoreBoardName) {
    Object scoreBoard = fileSaverLoader.load(scoreBoardName);
    if (scoreBoard == null) {
      ScoreBoard emptyScoreBoard = new ScoreBoard(new ArrayList<GameScore>());
      fileSaverLoader.save(emptyScoreBoard, scoreBoardName);
    }
  }
}
